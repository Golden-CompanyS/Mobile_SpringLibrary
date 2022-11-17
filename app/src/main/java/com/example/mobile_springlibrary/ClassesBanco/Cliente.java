package com.example.mobile_springlibrary.ClassesBanco;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String UserCode, UserName, UserEmail, UserPassword;

    public Cliente(){ }

    public Cliente(String userCode, String userName, String userEmail, String userPassword){
        UserCode = userCode;
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public Cliente(String userEmail, String userPassword){
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

}
