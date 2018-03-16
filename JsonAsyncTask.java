package com.example.shiv.jsonplaceholder;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shiv on 3/11/2018.
 */

public class JsonAsyncTask  extends AsyncTask<String,Void,ArrayList<JsonplaceUsers>>{

public interface UserDownloadListener{


     void OnDownloadComplete(ArrayList<JsonplaceUsers> jsonplaceUsers) ;


}
private UserDownloadListener mListener;
JsonAsyncTask(UserDownloadListener listener){
    
    mListener=listener;

}


    @Override
    protected ArrayList<JsonplaceUsers> doInBackground(String... strings) {

    String urlString=strings[0];

        try {
            URL url=new URL(urlString);

            HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");

            httpsURLConnection.connect();
            InputStream inputStream=httpsURLConnection.getInputStream();
            String result="";
            Scanner scanner=new Scanner(inputStream);
            while(scanner.hasNext()){

                result=result.concat(scanner.next());

            }
           ArrayList<JsonplaceUsers> jsonplaceUsers=parseUsers(result);
            Log.d("Response",result);
            httpsURLConnection.disconnect();

            return jsonplaceUsers;



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;

    }

    private ArrayList<JsonplaceUsers> parseUsers(String result) throws JSONException{

    ArrayList<JsonplaceUsers> jsonplaceUsers=new ArrayList<>();

        JSONArray rootJSONArray= new JSONArray(result);

        for(int i=0;i<rootJSONArray.length();i++){

            JSONObject userObject= rootJSONArray.getJSONObject(i);
            int id=userObject.getInt("id");
            String name=userObject.getString("name");
            String username=userObject.getString("username");
            String phone=userObject.getString("phone");
            JsonplaceUsers jsonplaceUsers1=new JsonplaceUsers(id,name,username,phone);
            jsonplaceUsers.add(jsonplaceUsers1);


        }

       return jsonplaceUsers;


            }









    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<JsonplaceUsers> jsonplaceUsers) {
        super.onPostExecute(jsonplaceUsers);
        mListener.OnDownloadComplete(jsonplaceUsers);
    }
}







