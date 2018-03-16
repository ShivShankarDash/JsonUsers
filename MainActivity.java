package com.example.shiv.jsonplaceholder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> usernames=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.listview);
        progressBar=findViewById(R.id.progressbar);

adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,usernames);
listView.setAdapter(adapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        fetchUsers();


            }
        });


   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        JsonplaceUsers users=new JsonplaceUsers((int)id,"","","");

        Intent intent=new Intent(MainActivity.this,PostActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("USERID",users.getId());
        intent.putExtras(bundle);
        startActivity(intent);

       }
   });







    }

    private void fetchUsers() {

        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        String urlString="https://jsonplaceholder.typicode.com/users";
        JsonAsyncTask jsonAsyncTask=new JsonAsyncTask(new JsonAsyncTask.UserDownloadListener() {
            @Override
            public void OnDownloadComplete(ArrayList<JsonplaceUsers> jsonplaceUsers) {
                if(jsonplaceUsers!=null){

                   for(int i=0;i<jsonplaceUsers.size();i++){

                       JsonplaceUsers jsonplaceUsers1=jsonplaceUsers.get(i);
                       usernames.add(jsonplaceUsers1.getUsername());

                   }

                    adapter.notifyDataSetChanged();

                }
                else{
                    Snackbar.make(listView,"Try Again.",Snackbar.LENGTH_LONG).show();

                }
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

            }
        });

jsonAsyncTask.execute(urlString);

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
