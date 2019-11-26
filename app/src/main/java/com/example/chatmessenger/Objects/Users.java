package com.example.chatmessenger.Objects;

public class Users {
    private int Id;
    private String UserName;
    private String PassWord;
    private String DateJoin;



    public Users(int id, String userName, String passWord) {
        Id = id;
        UserName = userName;
        PassWord = passWord;
    }


    public int getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getDateJoin() {
        return DateJoin;
    }
}
