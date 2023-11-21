package org.aptech.on_Tap.DAOPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String url = "jdbc:mysql://103.110.85.97:3306/T2303E_SEM2";
    private static final String username ="root";
    private static final String password ="admintascSecretPassword!123";
    private static Connection conn;
    public static synchronized Connection getConn(){
        if (conn == null){
            init();
        }
        return conn;

    }

    private static void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            System.err.println(conn != null ? "connect ok" : "connect error.");
        }catch (ClassNotFoundException e){
throw new RuntimeException(e);
        }catch (SQLException e){
throw new RuntimeException(e);
        }
    }

}
