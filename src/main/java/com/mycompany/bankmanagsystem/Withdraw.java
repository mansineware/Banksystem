
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {

    String formno;

    JTextField amountField;

    JButton withdraw, back;

    Withdraw(String formno) {

        this.formno = formno;

        setTitle("Cash Withdrawal");
        setLayout(null);

        JLabel heading = new JLabel("Enter Amount To Withdraw");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(120, 50, 300, 30);
        add(heading);

        amountField = new JTextField();
        amountField.setBounds(120, 120, 250, 35);
        add(amountField);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(80, 220, 120, 35);
        withdraw.addActionListener(this);
        add(withdraw);

        back = new JButton("Back");
        back.setBounds(260, 220, 120, 35);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == withdraw) {

            String amount = amountField.getText();

            if (amount.equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please enter amount"
                );
                return;
            }

            try {

                int balance = 0;

                Conn conn = new Conn();

                String query =
                        "SELECT * FROM bank WHERE formno=?";

                conn.pst = conn.c.prepareStatement(query);
                conn.pst.setString(1, formno);

                ResultSet rs = conn.pst.executeQuery();

                while (rs.next()) {

                    if (rs.getString("type").equals("Deposit")) {

                        balance += Integer.parseInt(
                                rs.getString("amount")
                        );

                    } else {

                        balance -= Integer.parseInt(
                                rs.getString("amount")
                        );
                    }
                }

                int withdrawAmount =
                        Integer.parseInt(amount);

                if (balance < withdrawAmount) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Insufficient Balance"
                    );

                    return;
                }

                Date date = new Date();

                String insertQuery =
                        "INSERT INTO bank VALUES(?,?,?,?)";

                conn.pst =
                        conn.c.prepareStatement(insertQuery);

                conn.pst.setString(1, formno);
                conn.pst.setString(2, date.toString());
                conn.pst.setString(3, "Withdraw");
                conn.pst.setString(4, amount);

                conn.pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Rs. " + amount +
                        " Withdrawn Successfully"
                );

                setVisible(false);

                new Transactions(formno);

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage()
                );
            }

        } else if (ae.getSource() == back) {

            setVisible(false);

            new Transactions(formno);
        }
    }

    public static void main(String[] args) {

        new Withdraw("1001");
    }
}