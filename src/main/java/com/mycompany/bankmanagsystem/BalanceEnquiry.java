
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String formno;

    JLabel balanceLabel;

    JButton back;

    BalanceEnquiry(String formno) {

        this.formno = formno;

        setTitle("Balance Enquiry");
        setLayout(null);

        JLabel heading = new JLabel("Your Current Balance");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(180, 50, 300, 30);
        add(heading);

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceLabel.setBounds(180, 120, 300, 30);
        add(balanceLabel);

        back = new JButton("Back");
        back.setBounds(230, 220, 100, 35);
        back.addActionListener(this);
        add(back);

        getBalance();

        getContentPane().setBackground(Color.WHITE);

        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void getBalance() {

        int balance = 0;

        try {

            Conn conn = new Conn();

            String query =
                    "SELECT * FROM bank WHERE formno=?";

            conn.pst = conn.c.prepareStatement(query);
            conn.pst.setString(1, formno);

            ResultSet rs = conn.pst.executeQuery();

            while (rs.next()) {

                String type = rs.getString("type");
                int amount =
                        Integer.parseInt(rs.getString("amount"));

                if (type.equals("Deposit")) {

                    balance += amount;

                } else if (type.equals("Withdraw")) {

                    balance -= amount;
                }
            }

            balanceLabel.setText("Rs. " + balance);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        setVisible(false);

        new Transactions(formno);
    }

    public static void main(String[] args) {

        new BalanceEnquiry("1001");
    }
}