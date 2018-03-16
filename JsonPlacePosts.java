package com.example.shiv.jsonplaceholder;

/**
 * Created by shiv on 3/12/2018.
 */

public class JsonPlacePosts {

 int userId;
 String posTitle;
 String postContent;

    public JsonPlacePosts(int userId, String posTitle, String postContent) {
        this.userId = userId;
        this.posTitle = posTitle;
        this.postContent = postContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPosTitle() {
        return posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
