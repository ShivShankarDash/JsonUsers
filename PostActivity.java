package com.example.shiv.jsonplaceholder;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBarpost;
    ArrayAdapter<String> adapterpost;
    ArrayList<String> posts=new ArrayList<>();
    int itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        listView=findViewById(R.id.listviewpost);
        progressBarpost=findViewById(R.id.progressbarpost);


        adapterpost=new ArrayAdapter<String>(PostActivity.this,android.R.layout.simple_expandable_list_item_1,posts);
        listView.setAdapter(adapterpost);

        Bundle bundle=getIntent().getExtras();
        itemid=bundle.getInt("USERID");
        Intent intent=getIntent();

            fetchposts();


    }

    private void fetchposts() {

        progressBarpost.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        String urlStringpost="https://jsonplaceholder.typicode.com/posts";

        JsonPostAsyncTask jsonPostAsyncTask=new JsonPostAsyncTask(new JsonPostAsyncTask.OnPostDownloadListener() {
            @Override
            public void OnPostDownloadComplete(ArrayList<JsonPlacePosts> jsonPlacePosts) {

                if(jsonPlacePosts!=null){

                  for(int i=0;i<jsonPlacePosts.size();i++){


                      JsonPlacePosts jsonPlacePosts1=jsonPlacePosts.get(i);
                      if(jsonPlacePosts1.getUserId()==itemid) {
                          posts.add(jsonPlacePosts1.getPostContent());
                      }

                  }
                    adapterpost.notifyDataSetChanged();

                }
                else {

                    Snackbar.make(listView,"Try Again.",Snackbar.LENGTH_LONG).show();

                }
                progressBarpost.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

            }
        });
jsonPostAsyncTask.execute(urlStringpost);

    }
}
