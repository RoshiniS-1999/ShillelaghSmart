package com.example.shillelagh;

public class User {
    public String name,email,contact,emerg_contact;

    public User()
    {

    }
    public User(String name, String email, String contact, String emerg_contact){
       this.name = name;
       this.email = email;
       this.contact = contact;
       this.emerg_contact = emerg_contact;
    }
}
