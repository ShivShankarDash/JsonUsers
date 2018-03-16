package com.example.shiv.jsonplaceholder;

/**
 * Created by shiv on 3/11/2018.
 */

public class JsonplaceUsers
{
int id;
String name;
String username;
String phone;

    public JsonplaceUsers(int id, String name, String username, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
