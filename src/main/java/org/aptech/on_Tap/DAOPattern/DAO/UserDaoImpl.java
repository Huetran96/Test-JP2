package org.aptech.on_Tap.DAOPattern.DAO;

import org.aptech.on_Tap.DAOPattern.DataSource;
import org.aptech.on_Tap.DAOPattern.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserDaoImpl implements UserDao{
    @Override
    public boolean validateUser(String username, String password) {
        boolean result = false;
        PreparedStatement preSt;
        String sql = "select * from user_table where usernam= ? and password = ? ";
        List<User> userList = new ArrayList<>();
        Connection conn = DataSource.getConn();
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setString(1,username);
            preSt.setString(2,password);
            System.out.println(preSt);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()){
                User u = rowMapper(rs);
                if (Objects.isNull(u)){
                    userList.add(u);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (userList != null && userList.size()>0){
            result =true;
        }

        return result;
    }

    @Override
    public User getbyUsername(String username) {
        PreparedStatement preSt;
        String sql = "select * from user_table where username = ?";
        List<User> userList = new ArrayList<>();
        Connection conn = DataSource.getConn();
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setString(1,username);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()){
                User u = rowMapper(rs);
                if (Objects.isNull(u)){
                    userList.add(u);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (userList != null && userList.size()>0){
            return userList.get(0);
        }else return null;
    }

    @Override
    public Boolean insert(User u) {
        PreparedStatement preSt;
        String sql = "insert into user_table(username, password, create_at, update_at)"+"values(?,?,?,?)";
        Connection conn = DataSource.getConn();
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, u.getUsername());
            preSt.setString(2, u.getPassword());
            preSt.setDate(3, new Date(u.getCreateAt().getTime()));
            preSt.setDate(4,new Date(u.getUpdateAt().getTime()));
            preSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(User u) {
        Connection conn = DataSource.getConn();
        PreparedStatement preSt;
        String sql = "update user_table"+
                "set password = ?,login_fail = ?, status = ? " +
                "where username = ?;";
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setString(1,u.getPassword());
            preSt.setInt(2,u.getLoginFail());
            preSt.setInt(3,u.getStatus());
            preSt.setString(4,u.getUsername());
            preSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> finAll(int limit, int offset) {
        long startTime = System.currentTimeMillis();
        PreparedStatement preSt;
        String sql = "select * from user_table order by limit ? offset ?";
        List<User> userList = new ArrayList<>();
        Connection conn = DataSource.getConn();
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setInt(1,limit);
            preSt.setInt(2,offset);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()){
                User u = rowMapper(rs);
                if (Objects.isNull(u)){
                    userList.add(u);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public Integer count(String sql) {
        Connection conn = DataSource.getConn();
        PreparedStatement preSt = null;
        try {
            preSt = conn.prepareStatement(sql);
            ResultSet rs = preSt.executeQuery();
            while (rs.next()){
                rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    private static User rowMapper(ResultSet rs){
        User u = null;
        int id = 0;

        try {
            id = rs.getInt("id");
            String name = rs.getString("username");
            String pass = rs.getString("password");
            int loginFail = rs.getInt("login_fail");
            int status = rs.getInt("status");
            String createBy = rs.getString("create_by");
            String updateBy = rs.getString("update_by");
            Date createTime = rs.getDate("create_at");
            Date updateTime = rs.getDate("update_at");
            u = User.builder()
                    .id(id)
                    .username(name)
                    .password(pass)
                    .loginFail(loginFail)
                    .status(status)
                    .createBy(createBy)
                    .createAt(createTime)
                    .updateBy(updateBy)
                    .updateAt(updateTime)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;

    }
}
