
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String formno;

    JTextField amountField;

    JButton deposit, back;

    Deposit(String formno) {

        this.formno = formno;

        setTitle("Deposit");

        setLayout(null);

        JLabel heading = new JLabel("Deposit Money");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(180, 50, 250, 30);
        add(heading);

        JLabel amount = new JLabel("Enter Amount:");
        amount.setFont(new Font("Arial", Font.BOLD, 16));
        amount.setBounds(80, 130, 150, 30);
        add(amount);

        amountField = new JTextField();
        amountField.setBounds(230, 130, 200, 30);
        add(amountField);

        deposit = new JButton("Deposit");
        deposit.setBounds(120, 220, 120, 35);
        deposit.addActionListener(this);
        add(deposit);

        back = new JButton("Back");
        back.setBounds(280, 220, 120, 35);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        setSize(550, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {

            String amount = amountField.getText();

            if (amount.equals("")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please Enter Amount"
                );
                return;
            }

            try {

                Date date = new Date();

                Conn conn = new Conn();

                String query =
                        "INSERT INTO bank VALUES(?,?,?,?)";

                conn.pst = conn.c.prepareStatement(query);

                conn.pst.setString(1, formno);
                conn.pst.setString(2, date.toString());
                conn.pst.setString(3, "Deposit");
                conn.pst.setString(4, amount);

                conn.pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Rs. " + amount +
                        " Deposited Successfully"
                );

                amountField.setText("");

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {

            setVisible(false);
             new Transactions(formno);

            // Open Transaction Page
            // new Transactions(formno);
        }
    }

    public static void main(String[] args) {

        new Deposit("1234");
    }
}