
package com.mycompany.bankmanagsystem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {

    String formno;

    JPasswordField pin1Field, pin2Field;

    JButton change, back;

    PinChange(String formno) {

        this.formno = formno;

        setTitle("PIN Change");
        setLayout(null);

        JLabel heading = new JLabel("CHANGE YOUR PIN");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(180, 50, 300, 30);
        add(heading);

        JLabel pin1 = new JLabel("New PIN:");
        pin1.setBounds(100, 150, 150, 30);
        add(pin1);

        pin1Field = new JPasswordField();
        pin1Field.setBounds(250, 150, 200, 30);
        add(pin1Field);

        JLabel pin2 = new JLabel("Re-Enter PIN:");
        pin2.setBounds(100, 220, 150, 30);
        add(pin2);

        pin2Field = new JPasswordField();
        pin2Field.setBounds(250, 220, 200, 30);
        add(pin2Field);

        change = new JButton("Change");
        change.setBounds(150, 320, 120, 35);
        change.addActionListener(this);
        add(change);

        back = new JButton("Back");
        back.setBounds(320, 320, 120, 35);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);

        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {

            setVisible(false);
            new Transactions(formno);
            return;
        }

        String pin1 = pin1Field.getText();
        String pin2 = pin2Field.getText();

        if (pin1.equals("")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Enter New PIN"
            );
            return;
        }

        if (!pin1.equals(pin2)) {

            JOptionPane.showMessageDialog(
                    null,
                    "PIN does not match"
            );
            return;
        }

        try {

            Conn conn = new Conn();

            // Update login table
            String query1 =
                    "UPDATE login SET pin=? WHERE formno=?";

            conn.pst = conn.c.prepareStatement(query1);

            conn.pst.setString(1, pin1);
            conn.pst.setString(2, formno);

            conn.pst.executeUpdate();

            // Update signupthree table
            String query2 =
                    "UPDATE signupthree SET pin=? WHERE formno=?";

            conn.pst = conn.c.prepareStatement(query2);

            conn.pst.setString(1, pin1);
            conn.pst.setString(2, formno);

            conn.pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "PIN Changed Successfully"
            );

            setVisible(false);

            new Transactions(formno);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Error : " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        new PinChange("1234");
    }
}