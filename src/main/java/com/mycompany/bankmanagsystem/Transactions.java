
package com.mycompany.bankmanagsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    String formno;

    JButton deposit,  pinChange;
    JButton cashWithdrawal, miniStatement;
    JButton balanceEnquiry, exit;

    Transactions(String formno) {

        this.formno = formno;

        setTitle("ATM Transactions");
        setLayout(null);

        JLabel heading = new JLabel("Please Select Your Transaction");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(120, 50, 350, 30);
        add(heading);

        deposit = new JButton("Deposit");
        deposit.setBounds(70, 130, 180, 40);
        deposit.addActionListener(this);
        add(deposit);

        cashWithdrawal = new JButton("Cash Withdrawal");
        cashWithdrawal.setBounds(300, 130, 180, 40);
        cashWithdrawal.addActionListener(this);
        add(cashWithdrawal);

        

        miniStatement = new JButton("Mini Statement");
        miniStatement.setBounds(300, 210, 180, 40);
        miniStatement.addActionListener(this);
        add(miniStatement);

        pinChange = new JButton("PIN Change");
        pinChange.setBounds(70, 290, 180, 40);
        pinChange.addActionListener(this);
        add(pinChange);

        balanceEnquiry = new JButton("Balance Enquiry");
        balanceEnquiry.setBounds(300, 290, 180, 40);
        balanceEnquiry.addActionListener(this);
        add(balanceEnquiry);

        exit = new JButton("Exit");
        exit.setBounds(180, 380, 180, 40);
        exit.addActionListener(this);
        add(exit);

        getContentPane().setBackground(Color.WHITE);

        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {

            setVisible(false);
            new Deposit(formno);

        } else if (ae.getSource() == cashWithdrawal) {

             setVisible(false);

    new Withdraw(formno);

        } else if (ae.getSource() == miniStatement) {

            new MiniStatement(formno);

        } else if (ae.getSource() == pinChange) {

             setVisible(false);
    new PinChange(formno);

        } else if (ae.getSource() == balanceEnquiry) {

            setVisible(false);

    new BalanceEnquiry(formno);

        } else if (ae.getSource() == exit) {

            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("1234");
    }
}