package com.example.shiv.jsonplaceholder;

import android.os.AsyncTask;
import android.util.Log;

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
 * Created by shiv on 3/12/2018.
 */

public class JsonPostAsyncTask extends AsyncTask<String, Void, ArrayList<JsonPlacePosts>> {


    public interface OnPostDownloadListener{


    void OnPostDownloadComplete(ArrayList<JsonPlacePosts> jsonPlacePosts);

    }

private OnPostDownloadListener pListener;

JsonPostAsyncTask(OnPostDownloadListener listener){

    pListener=listener;

}

    @Override
    protected ArrayList<JsonPlacePosts> doInBackground(String... strings) {

    String urlString=strings[0];

        try {
            URL url =new URL(urlString);

            HttpsURLConnection httpsURLConnection=(HttpsURLConnection)url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            InputStream inputStream=httpsURLConnection.getInputStream();

            String resultpost="";
            Scanner scanner=new Scanner(inputStream);
            while(scanner.hasNext()){

                resultpost=resultpost.concat(scanner.next());

            }

            ArrayList<JsonPlacePosts> jsonPlacePosts=parsePosts(resultpost);
            Log.d("RESPONSE",resultpost);
            httpsURLConnection.disconnect();

            return jsonPlacePosts;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    private ArrayList<JsonPlacePosts> parsePosts(String resultpost) throws JSONException {

    ArrayList<JsonPlacePosts> jsonPlacePosts= new ArrayList<>();

        JSONArray jsonPostArray=new JSONArray(resultpost);

        for(int i=0;i<jsonPostArray.length();i++){

            JSONObject postobjects=jsonPostArray.getJSONObject(i);
            int userid=postobjects.getInt("userid");
            String posTitle=postobjects.getString("posTitle");
            String content=postobjects.getString("content");

            JsonPlacePosts posts=new JsonPlacePosts(userid,posTitle,content);
            jsonPlacePosts.add(posts);



        }

        if(jsonPlacePosts==null){

            Log.d("NOT WORKING","XYZ");
        }

     return jsonPlacePosts;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onPostExecute(ArrayList<JsonPlacePosts> jsonPlacePosts) {
        super.onPostExecute(jsonPlacePosts);
        pListener.OnPostDownloadComplete(jsonPlacePosts);
    }



}
