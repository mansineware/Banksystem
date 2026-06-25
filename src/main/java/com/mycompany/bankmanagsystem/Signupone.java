
package com.mycompany.bankmanagsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signupone extends JFrame implements ActionListener {

    String formno;

    JTextField nameField, fatherField, emailField;
    JTextField addressField, cityField, pinField, stateField;

    JComboBox<String> dayBox, monthBox, yearBox;

    JRadioButton male, female, other;
    JRadioButton married, unmarried;

    ButtonGroup genderGroup, maritalGroup;

    JButton next;

    public Signupone() {

        setTitle("Application Form");
        setLayout(null);

        // Random Form Number
        long random = (long) (Math.random() * 900000) + 100000;
        formno = String.valueOf(random);

        // Heading
        JLabel formNoLabel = new JLabel("APPLICATION FORM NO. " + formno);
        formNoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        formNoLabel.setBounds(180, 20, 500, 30);
        add(formNoLabel);

        JLabel page = new JLabel("Page 1 : Personal Details");
        page.setFont(new Font("Arial", Font.BOLD, 18));
        page.setBounds(220, 55, 300, 30);
        add(page);

        // Name
        JLabel name = new JLabel("Name:");
        name.setBounds(100, 110, 150, 30);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(250, 110, 300, 30);
        add(nameField);

        // Father's Name
        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(100, 160, 150, 30);
        add(fname);

        fatherField = new JTextField();
        fatherField.setBounds(250, 160, 300, 30);
        add(fatherField);

        // DOB
        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(100, 210, 150, 30);
        add(dob);

        String days[] = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }

        String months[] = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };

        String years[] = new String[60];
        int year = 2025;

        for (int i = 0; i < 60; i++) {
            years[i] = String.valueOf(year - i);
        }

        dayBox = new JComboBox<>(days);
        monthBox = new JComboBox<>(months);
        yearBox = new JComboBox<>(years);

        dayBox.setBounds(250, 210, 70, 30);
        monthBox.setBounds(330, 210, 100, 30);
        yearBox.setBounds(440, 210, 100, 30);

        add(dayBox);
        add(monthBox);
        add(yearBox);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setBounds(100, 260, 150, 30);
        add(gender);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        other = new JRadioButton("Other");

        male.setBounds(250, 260, 80, 30);
        female.setBounds(340, 260, 100, 30);
        other.setBounds(450, 260, 80, 30);

        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        add(male);
        add(female);
        add(other);

        // Email
        JLabel email = new JLabel("Email Address:");
        email.setBounds(100, 310, 150, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(250, 310, 300, 30);
        add(emailField);

        // Marital Status
        JLabel marital = new JLabel("Marital Status:");
        marital.setBounds(100, 360, 150, 30);
        add(marital);

        married = new JRadioButton("Married");
        unmarried = new JRadioButton("Unmarried");

        married.setBounds(250, 360, 100, 30);
        unmarried.setBounds(360, 360, 120, 30);

        maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);

        add(married);
        add(unmarried);

        // Address
        JLabel address = new JLabel("Address:");
        address.setBounds(100, 410, 150, 30);
        add(address);

        addressField = new JTextField();
        addressField.setBounds(250, 410, 300, 30);
        add(addressField);

        // City
        JLabel city = new JLabel("City:");
        city.setBounds(100, 460, 150, 30);
        add(city);

        cityField = new JTextField();
        cityField.setBounds(250, 460, 300, 30);
        add(cityField);

        // Pin Code
        JLabel pin = new JLabel("Pin Code:");
        pin.setBounds(100, 510, 150, 30);
        add(pin);

        pinField = new JTextField();
        pinField.setBounds(250, 510, 300, 30);
        add(pinField);

        // State
        JLabel state = new JLabel("State:");
        state.setBounds(100, 560, 150, 30);
        add(state);

        stateField = new JTextField();
        stateField.setBounds(250, 560, 300, 30);
        add(stateField);

        // Next Button
        next = new JButton("Next");
        next.setBounds(560, 620, 100, 35);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(750, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String name = nameField.getText();
        String father = fatherField.getText();

        String dob = dayBox.getSelectedItem() + "-"
                + monthBox.getSelectedItem() + "-"
                + yearBox.getSelectedItem();

        String gender = "";

        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        } else if (other.isSelected()) {
            gender = "Other";
        }

        String email = emailField.getText();

        String marital = "";

        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        }

        String address = addressField.getText();
        String city = cityField.getText();
        String pincode = pinField.getText();
        String state = stateField.getText();

        if (name.isEmpty() || father.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields"
            );
            return;
        }

        try {

            Conn conn = new Conn();

            String query =
                    "INSERT INTO signupone VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            conn.pst = conn.c.prepareStatement(query);

            conn.pst.setString(1, formno);
            conn.pst.setString(2, name);
            conn.pst.setString(3, father);
            conn.pst.setString(4, dob);
            conn.pst.setString(5, gender);
            conn.pst.setString(6, email);
            conn.pst.setString(7, marital);
            conn.pst.setString(8, address);
            conn.pst.setString(9, city);
            conn.pst.setString(10, pincode);
            conn.pst.setString(11, state);

            conn.pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Personal Details Saved Successfully!"
            );

            setVisible(false);

            new SignupTwo(formno);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error : " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {
        new Signupone();
    }
}