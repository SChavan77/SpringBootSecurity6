package com.spring.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class User {

    @Id
    private Integer Id;

    private String userName;

    private String password;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
