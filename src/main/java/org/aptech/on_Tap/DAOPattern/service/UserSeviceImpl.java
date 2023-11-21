package org.aptech.on_Tap.DAOPattern.service;

import org.apache.commons.lang3.StringUtils;
import org.aptech.on_Tap.DAOPattern.Const.UserStatus;
import org.aptech.on_Tap.DAOPattern.DAO.UserDao;
import org.aptech.on_Tap.DAOPattern.DAO.UserDaoImpl;
import org.aptech.on_Tap.DAOPattern.User;
import org.aptech.on_Tap.DAOPattern.ultis.AESUtils;

import java.io.*;
import java.util.List;

public class UserSeviceImpl implements UserService{
    private static final int MAX_LOGIN_FAIL = 5;
    UserDao userDao = new UserDaoImpl();
    @Override
    public boolean login(String username, String password) {
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return false;
        }
        //1. get user by username (username unique)
        //2. tính từ lần đăng nhập mới nhất , tối đa 5 lần đăng nhập thất bại
        //3. bổ sung trường login_fail , status (active , inactive)
        // 4. compare password -> true : login ok / not ok
        User u = userDao.getbyUsername(username);
        //compare password
        String encryptedPass = AESUtils.encrypt(password);
        if (u.getPassword().equals(encryptedPass)){
            if (u.getStatus() != UserStatus.ACTIVE.status){
                return false;
            }return u.getLoginFail()<MAX_LOGIN_FAIL;

        }else {
            // login fail ->  todo update login_fail+1
            if (u.getLoginFail()+1>=MAX_LOGIN_FAIL){
                u.setLoginFail(u.getLoginFail()+1);
                u.setStatus(UserStatus.BLOCKED.status);
            }else {
                u.setLoginFail(u.getLoginFail() + 1);
            }
            userDao.update(u);
        }
        return false;
    }

    @Override
    public void insertFile(List<User> userList, String fileName) {
        try {
            FileWriter file = new FileWriter(fileName,true);
            PrintWriter out = new PrintWriter(new BufferedWriter(file));
            for (User u:userList) {
                StringBuffer sb = new StringBuffer();
                sb.append(u.getUsername()).append("|")
                        .append(u.getStatus()).append("|")
                        .append(u.getCreateAt()).append("|")
                        .append(u.getCreateBy()).append("|")
                        .append(u.getUpdateAt()).append("|")
                        .append(u.getUpdateBy());
                out.println(sb.toString());
            }
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {

    }
}
