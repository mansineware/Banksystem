
package com.mycompany.bankmanagsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String formno;

    MiniStatement(String formno) {

        this.formno = formno;

        setTitle("Mini Statement");
        setLayout(null);

        JLabel heading = new JLabel("Mini Statement");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(150, 20, 200, 30);
        add(heading);

        JTextArea statementArea = new JTextArea();
        statementArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(statementArea);

        scrollPane.setBounds(20, 70, 450, 300);
        add(scrollPane);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 380, 300, 30);
        balanceLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );
        add(balanceLabel);

        int balance = 0;

        try {

            Conn conn = new Conn();

            String query =
                    "SELECT * FROM bank WHERE formno=?";

            conn.pst =
                    conn.c.prepareStatement(query);

            conn.pst.setString(1, formno);

            ResultSet rs =
                    conn.pst.executeQuery();

            statementArea.append(
                    "Date\t\tType\tAmount\n"
            );
            statementArea.append(
                    "--------------------------------------\n"
            );

            while (rs.next()) {

                String date =
                        rs.getString("date");

                String type =
                        rs.getString("type");

                String amount =
                        rs.getString("amount");

                statementArea.append(
                        date + "\t" +
                        type + "\t" +
                        amount + "\n"
                );

                if (type.equals("Deposit")) {

                    balance +=
                            Integer.parseInt(amount);

                } else if (type.equals("Withdraw")) {

                    balance -=
                            Integer.parseInt(amount);
                }
            }

            balanceLabel.setText(
                    "Current Balance : Rs. " + balance
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        getContentPane().setBackground(Color.WHITE);

        setSize(520, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        new MiniStatement("1001");
    }
}