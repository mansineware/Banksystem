
package com.mycompany.bankmanagsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    String formno;

    JComboBox<String> religionBox, categoryBox, incomeBox,
            educationBox, occupationBox;

    JTextField panField, aadharField;

    JRadioButton seniorYes, seniorNo;
    JRadioButton accountYes, accountNo;

    ButtonGroup seniorGroup, accountGroup;

    JButton next;

    SignupTwo(String formno) {

        this.formno = formno;

        setTitle("Additional Details");
        setLayout(null);

        JLabel heading = new JLabel("Page 2 : Additional Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(180, 30, 350, 30);
        add(heading);

        // Religion
        JLabel religion = new JLabel("Religion:");
        religion.setBounds(100, 100, 150, 30);
        add(religion);

        String religions[] = {
                "Hindu", "Muslim", "Christian",
                "Sikh", "Buddhist", "Other"
        };

        religionBox = new JComboBox<>(religions);
        religionBox.setBounds(250, 100, 250, 30);
        add(religionBox);

        // Category
        JLabel category = new JLabel("Category:");
        category.setBounds(100, 150, 150, 30);
        add(category);

        String categories[] = {
                "General", "OBC", "SC", "ST", "Other"
        };

        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(250, 150, 250, 30);
        add(categoryBox);

        // Income
        JLabel income = new JLabel("Income:");
        income.setBounds(100, 200, 150, 30);
        add(income);

        String incomes[] = {
                "Below 1,50,000",
                "1,50,000 - 2,50,000",
                "2,50,000 - 5,00,000",
                "Above 5,00,000"
        };

        incomeBox = new JComboBox<>(incomes);
        incomeBox.setBounds(250, 200, 250, 30);
        add(incomeBox);

        // Education
        JLabel education = new JLabel("Education:");
        education.setBounds(100, 250, 150, 30);
        add(education);

        String educations[] = {
                "Non-Graduate",
                "Graduate",
                "Post-Graduate",
                "Doctorate",
                "Other"
        };

        educationBox = new JComboBox<>(educations);
        educationBox.setBounds(250, 250, 250, 30);
        add(educationBox);

        // Occupation
        JLabel occupation = new JLabel("Occupation:");
        occupation.setBounds(100, 300, 150, 30);
        add(occupation);

        String occupations[] = {
                "Salaried",
                "Self-Employed",
                "Business",
                "Student",
                "Retired",
                "Other"
        };

        occupationBox = new JComboBox<>(occupations);
        occupationBox.setBounds(250, 300, 250, 30);
        add(occupationBox);

        // PAN
        JLabel pan = new JLabel("PAN Number:");
        pan.setBounds(100, 350, 150, 30);
        add(pan);

        panField = new JTextField();
        panField.setBounds(250, 350, 250, 30);
        add(panField);

        // Aadhaar
        JLabel aadhar = new JLabel("Aadhaar Number:");
        aadhar.setBounds(100, 400, 150, 30);
        add(aadhar);

        aadharField = new JTextField();
        aadharField.setBounds(250, 400, 250, 30);
        add(aadharField);

        // Senior Citizen
        JLabel senior = new JLabel("Senior Citizen:");
        senior.setBounds(100, 450, 150, 30);
        add(senior);

        seniorYes = new JRadioButton("Yes");
        seniorNo = new JRadioButton("No");

        seniorYes.setBounds(250, 450, 80, 30);
        seniorNo.setBounds(350, 450, 80, 30);

        seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        add(seniorYes);
        add(seniorNo);

        // Existing Account
        JLabel account = new JLabel("Existing Account:");
        account.setBounds(100, 500, 150, 30);
        add(account);

        accountYes = new JRadioButton("Yes");
        accountNo = new JRadioButton("No");

        accountYes.setBounds(250, 500, 80, 30);
        accountNo.setBounds(350, 500, 80, 30);

        accountGroup = new ButtonGroup();
        accountGroup.add(accountYes);
        accountGroup.add(accountNo);

        add(accountYes);
        add(accountNo);

        // Next Button
        next = new JButton("Next");
        next.setBounds(550, 580, 100, 35);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String religion =
                (String) religionBox.getSelectedItem();

        String category =
                (String) categoryBox.getSelectedItem();

        String income =
                (String) incomeBox.getSelectedItem();

        String education =
                (String) educationBox.getSelectedItem();

        String occupation =
                (String) occupationBox.getSelectedItem();

        String pan = panField.getText();
        String aadhar = aadharField.getText();

        String seniorCitizen = "";

        if (seniorYes.isSelected())
            seniorCitizen = "Yes";
        else if (seniorNo.isSelected())
            seniorCitizen = "No";

        String existingAccount = "";

        if (accountYes.isSelected())
            existingAccount = "Yes";
        else if (accountNo.isSelected())
            existingAccount = "No";

        try {

            Conn conn = new Conn();

            String query =
                    "INSERT INTO signuptwo VALUES(?,?,?,?,?,?,?,?,?,?)";

            conn.pst = conn.c.prepareStatement(query);

            conn.pst.setString(1, formno);
            conn.pst.setString(2, religion);
            conn.pst.setString(3, category);
            conn.pst.setString(4, income);
            conn.pst.setString(5, education);
            conn.pst.setString(6, occupation);
            conn.pst.setString(7, pan);
            conn.pst.setString(8, aadhar);
            conn.pst.setString(9, seniorCitizen);
            conn.pst.setString(10, existingAccount);

            conn.pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Additional Details Saved Successfully!"
            );

            setVisible(false);
new SignupThree(formno);

            // Open Page 3
            // new SignupThree(formno);

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null,
                    "Error : " + e.getMessage()
            );
        }
    }
}