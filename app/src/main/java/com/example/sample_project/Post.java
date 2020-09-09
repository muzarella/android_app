package com.example.sample_project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("firstName")
    //@Expose
    public String firstName ;
    @SerializedName("lastName")
    //@Expose
    public String lastName ;
   @SerializedName("email")
   // @Expose
    public String  email ;
    @SerializedName("github")
    //@Expose
    public String  github ;
    @SerializedName("id")
    public  Integer id ;

    public Post(String email, String firstName, String lastName,  String github) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.github = github;
    }

    public Integer getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
