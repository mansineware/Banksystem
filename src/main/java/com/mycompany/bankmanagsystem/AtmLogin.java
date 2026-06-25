
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AtmLogin extends JFrame implements ActionListener {

    JLabel heading, cardLabel, pinLabel;

    JTextField cardField;
    JPasswordField pinField;

    JButton signIn, clear, signUp;

    AtmLogin() {

        setTitle("ATM LOGIN");

        setLayout(null);

        heading = new JLabel("WELCOME TO ATM");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(120, 30, 300, 30);
        add(heading);

        cardLabel = new JLabel("Card Number :");
        cardLabel.setBounds(50, 100, 120, 30);
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(180, 100, 220, 30);
        add(cardField);

        pinLabel = new JLabel("PIN :");
        pinLabel.setBounds(50, 160, 120, 30);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(180, 160, 220, 30);
        add(pinField);

        signIn = new JButton("SIGN IN");
        signIn.setBounds(30, 250, 110, 35);
        signIn.addActionListener(this);
        add(signIn);

        clear = new JButton("CLEAR");
        clear.setBounds(170, 250, 110, 35);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(310, 250, 110, 35);
        signUp.addActionListener(this);
        add(signUp);

        getContentPane().setBackground(Color.WHITE);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        // CLEAR BUTTON
        if(ae.getSource() == clear){

            cardField.setText("");
            pinField.setText("");
        }

        // SIGNUP BUTTON
        else if(ae.getSource() == signUp){

            setVisible(false);
            new Signupone();
        }

        // SIGNIN BUTTON
        else if(ae.getSource() == signIn){

            String cardNo = cardField.getText();
            String pin = pinField.getText();

            try{

                Conn conn = new Conn();

                String query =
                "SELECT * FROM login WHERE cardnumber=? AND pin=?";

                PreparedStatement pst =
                        conn.c.prepareStatement(query);

                pst.setString(1, cardNo);
                pst.setString(2, pin);

                ResultSet rs = pst.executeQuery();

                if(rs.next()){

                    JOptionPane.showMessageDialog(
                            null,
                            "Login Successful"
                    );

                    setVisible(false);

                    String formno =
                            rs.getString("formno");

                    new Transactions(formno);

                }else{

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Card Number or PIN"
                    );
                }

            }catch(Exception e){

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage()
                );
            }
        }
    }

    public static void main(String[] args) {

        new AtmLogin();
    }
}