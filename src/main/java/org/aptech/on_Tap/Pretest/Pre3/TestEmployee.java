package org.aptech.on_Tap.Pretest.Pre3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestEmployee {
    Connection conn = null;
    List<Employee> employeeList = new ArrayList<>();

    public void getAll(){
        Employee em;
        conn = DataSource.getConn();
        String sql = "select * from tbEmployee";
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                em = rowMapper(rs);
                employeeList.add(em);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if (employeeList.size()>0){
            for (Employee e:employeeList) {
                System.out.println(e);
            }
        }else {
            System.out.println("System is empty. ");
        }


    }
    public void getById(int id){
        List<Employee> getIdList = new ArrayList<>();
        Employee em=null;
        conn = DataSource.getConn();
        String sql = "select * from tbEmployee where id = ?";
        PreparedStatement preSt = null;
        ResultSet rs = null;
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setInt(1,id);
            rs = preSt.executeQuery();
            while (rs.next()){
                em = rowMapper(rs);
                getIdList.add(em);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }if (getIdList.size()>0){
            for (Employee e:getIdList) {
                System.out.println(e);
            }
        }else {
            System.out.println(" not found.");
        }


    }
    public void delete( int id){
        Employee em=null;
        String sql = "delete from tbEmployee where id = ?";
        conn = DataSource.getConn();
        try {
            PreparedStatement preSt = conn.prepareStatement(sql);
            preSt.setInt(1,id);
            preSt.executeUpdate();
            int cnt = 0;
            for (int i = 0; i < employeeList.size(); i++) {
                if (employeeList.get(i).getId()==id){
                    employeeList.remove(employeeList.get(i));
                    System.out.println("Delete successed.");
                    cnt ++;
                }
            }
            if (cnt>0){
                System.out.println("After delete: ");
                for (Employee o:employeeList) {
                    System.out.println(o);
                }
            }
            else {
                System.out.println("Not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void insert(Employee em){
        conn = DataSource.getConn();
        String sql = "insert into tbEmployee " +
                "values (?,?,?)";
        PreparedStatement preSt = null;
        try {
            preSt = conn.prepareStatement(sql);
            preSt.setString(1, em.getName());
            preSt.setString(2,em.getGender());
            preSt.setInt(3,em.getSalary());
            preSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static Employee rowMapper(ResultSet rs){
        Employee e = null;
        try {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String gender = rs.getString("GENDER");
            int salary = rs.getInt("SALARY");
            e = Employee.builder()
                    .id(id)
                    .name(name)
                    .gender(gender)
                    .salary(salary)
                    .build();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return e;
    }

    public static void main(String[] args) {
        TestEmployee te = new TestEmployee();
    }
}
