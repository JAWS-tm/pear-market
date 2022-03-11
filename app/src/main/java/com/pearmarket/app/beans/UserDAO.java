package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.User;

public interface UserDAO {
    User getUser(int id);
    User tryConnect(String email, String password);

    User createAccount(String email, String password, String name, String firstname);
}
