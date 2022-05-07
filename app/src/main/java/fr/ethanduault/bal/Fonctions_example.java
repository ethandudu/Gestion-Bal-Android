package fr.ethanduault.bal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Fonctions_example {

    public static String url = "jdbc:mysql://domain.tld:3306/database";
    public final static String user = "user";
    public final static String pass = "password";

    public static Statement connexionSQLBDD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn.createStatement();

        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
