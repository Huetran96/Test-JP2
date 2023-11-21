package org.aptech.on_Tap.DAOPattern.DAO;

import org.aptech.on_Tap.DAOPattern.User;

import java.util.List;

public interface UserDao {
    //dao: tuong tac voi database
    boolean validateUser(String username, String password);
    User getbyUsername(String usernam);
    Boolean insert(User u);
    Boolean update(User u);
    List<User> finAll(int limit, int offset);
    Integer count(String sql);
}
