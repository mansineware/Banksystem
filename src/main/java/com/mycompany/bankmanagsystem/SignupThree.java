
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {

    String formno;

    JRadioButton savings, fixed, current, recurring;
    JCheckBox atm, internet, mobile, email, cheque, estatement;

    JButton submit;

    SignupThree(String formno) {

        this.formno = formno;

        setTitle("Account Details");
        setLayout(null);

        JLabel heading = new JLabel("Page 3 : Account Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(220, 30, 300, 30);
        add(heading);

        JLabel type = new JLabel("Account Type:");
        type.setBounds(100, 100, 150, 30);
        add(type);

        savings = new JRadioButton("Savings Account");
        fixed = new JRadioButton("Fixed Deposit Account");
        current = new JRadioButton("Current Account");
        recurring = new JRadioButton("Recurring Deposit Account");

        savings.setBounds(250, 100, 200, 30);
        fixed.setBounds(250, 140, 200, 30);
        current.setBounds(250, 180, 200, 30);
        recurring.setBounds(250, 220, 250, 30);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savings);
        accountGroup.add(fixed);
        accountGroup.add(current);
        accountGroup.add(recurring);

        add(savings);
        add(fixed);
        add(current);
        add(recurring);

        JLabel service = new JLabel("Services Required:");
        service.setBounds(100, 300, 150, 30);
        add(service);

        atm = new JCheckBox("ATM Card");
        internet = new JCheckBox("Internet Banking");
        mobile = new JCheckBox("Mobile Banking");
        email = new JCheckBox("Email Alerts");
        cheque = new JCheckBox("Cheque Book");
        estatement = new JCheckBox("E-Statement");

        atm.setBounds(250, 300, 200, 30);
        internet.setBounds(250, 340, 200, 30);
        mobile.setBounds(250, 380, 200, 30);
        email.setBounds(250, 420, 200, 30);
        cheque.setBounds(250, 460, 200, 30);
        estatement.setBounds(250, 500, 200, 30);

        add(atm);
        add(internet);
        add(mobile);
        add(email);
        add(cheque);
        add(estatement);

        submit = new JButton("Submit");
        submit.setBounds(520, 600, 100, 35);
        submit.addActionListener(this);
        add(submit);

        getContentPane().setBackground(Color.WHITE);

        setSize(750, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String accountType = "";

        if (savings.isSelected())
            accountType = "Savings Account";
        else if (fixed.isSelected())
            accountType = "Fixed Deposit Account";
        else if (current.isSelected())
            accountType = "Current Account";
        else if (recurring.isSelected())
            accountType = "Recurring Deposit Account";

        Random random = new Random();

        String cardNumber =
                "5040936" +
                Math.abs(random.nextLong() % 100000000L);

        String pin =
                String.valueOf(
                        Math.abs(random.nextInt() % 9000) + 1000
                );

        String services = "";

        if (atm.isSelected())
            services += "ATM Card, ";

        if (internet.isSelected())
            services += "Internet Banking, ";

        if (mobile.isSelected())
            services += "Mobile Banking, ";

        if (email.isSelected())
            services += "Email Alerts, ";

        if (cheque.isSelected())
            services += "Cheque Book, ";

        if (estatement.isSelected())
            services += "E-Statement";

        try {

            Conn conn = new Conn();

            // Save account details
            String query1 =
                    "INSERT INTO signupthree VALUES(?,?,?,?,?)";

            conn.pst = conn.c.prepareStatement(query1);

            conn.pst.setString(1, formno);
            conn.pst.setString(2, accountType);
            conn.pst.setString(3, cardNumber);
            conn.pst.setString(4, pin);
            conn.pst.setString(5, services);

            conn.pst.executeUpdate();

            // Save login details
            String query2 =
                    "INSERT INTO login(formno, cardnumber, pin) VALUES(?,?,?)";

            conn.pst = conn.c.prepareStatement(query2);

            conn.pst.setString(1, formno);
            conn.pst.setString(2, cardNumber);
            conn.pst.setString(3, pin);

            conn.pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Account Created Successfully\n\n" +
                    "Card Number : " + cardNumber +
                    "\nPIN : " + pin
            );

            setVisible(false);

            new Deposit(formno);

        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Error : " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        new SignupThree("1234");
    }
}