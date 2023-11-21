package org.aptech.on_Tap.Pretest.Pre3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DataSource {
    private static final String url = "jdbc:sqlserver://localhost:1433; database= dbPretest3;encrypt=true;trustServerCertificate=true";
    private static final String username = "sa";
    private static final String password = "123456";
    private static Connection conn;
    public static synchronized Connection getConn(){
        if (conn == null){
            init();
            
        }
        return conn;

    }

    private static void init() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url,username,password);
            System.out.println(conn != null? "Connect ok": "Connect error");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
