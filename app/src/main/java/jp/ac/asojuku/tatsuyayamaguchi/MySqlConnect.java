package jp.ac.asojuku.tatsuyayamaguchi;

import java.sql.*;

public class MySqlConnect {
    static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "http://jousen5.aso-abcc.com/phpMyAdmin/";
        String user = "matsuo_user";
        String pass = "1111";
        Connection con = DriverManager.getConnection(url,user,pass);
        return con;
    }
}
