package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.User;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> getUsers();

    User getUser(int id);
    User tryConnect(String email, String password);

    User createAccount(String email, String password, String name, String firstname);
    void deleteAccount(String userEmail);

    void changeAddress(String userEmail, String address);
    void changePhone(String userEmail, String phone);
}
