package org.aptech.on_Tap.DAOPattern;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lab1 {
    public static List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        Connection conn = DataSource.getConn();
        try {
            String url = "select * from user_table";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(url);
            while (rs.next()){
                User u = rowMapper(rs);
                if (!Objects.isNull(u)){
                    userList.add(u);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return userList;

    }

    private static User rowMapper(ResultSet rs) {
        User u = null;
        int id = 0;
        try {
            id =rs.getInt("id");
            String usernam = rs.getString("username");
            String password = rs.getString("password");
            u = User.builder()
                    .id(id)
                    .username(usernam)
                    .password(password)
                    .build();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return u;
    }
    public static boolean validateUser(String username, String password){
        boolean result = false;
        if (StringUtils.isEmpty(username)|| StringUtils.isEmpty(password)){
            return result;
        }
        String sql = "select * from user_table where username ="+"'"+username+"'"+"and password="+"'"+password+"';";
        System.err.println(sql);
        List<User> userList = new ArrayList<>();
        Connection conn = DataSource.getConn();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                User u = rowMapper(rs);
                if (Objects.isNull(u)){
                    userList.add(u);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        for (User u:userList) {
            System.out.println(u);
        }
        if (userList != null && userList.size()>0){
            result = true;
        }
        return result;

    }
    public static boolean validateUser1(String username, String password){
        boolean result = false;
        String sql = "select * from user_table where username = ? and password = ? ;";
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return result;
        }
        Connection conn = DataSource.getConn();
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement prest = conn.prepareStatement(sql);
            prest.setString(1,"'"+username+"'");
            prest.setString(2,"'"+password+"'");
            ResultSet rs = prest.executeQuery();
            while (rs.next()){
                User u = rowMapper(rs);
                if (Objects.isNull(u)){
                    userList.add(u);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        for (User u:userList) {
            System.out.println(u);
        }
        if (userList!=null && userList.size()>0){
            result = true;
        }
        return result;
    }

}
