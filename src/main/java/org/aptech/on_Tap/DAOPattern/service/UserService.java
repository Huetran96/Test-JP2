package org.aptech.on_Tap.DAOPattern.service;

import org.aptech.on_Tap.DAOPattern.User;

import java.util.List;

public interface UserService {
    // service: xu ly nghiep vu
    boolean login(String usernam, String password);
    void insertFile(List<User> userList,String fileName);
}
