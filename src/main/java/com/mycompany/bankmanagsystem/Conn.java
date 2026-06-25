package com.mycompany.bankmanagsystem;

import java.sql.*;

public class Conn {

    Connection c;
    PreparedStatement pst;

    public Conn() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankmanagementsystem",
                    "root",
                    "Mansi@2006"
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
