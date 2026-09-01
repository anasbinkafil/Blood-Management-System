package Frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entities.*;

public class Register extends JFrame implements ActionListener
{
    private JLabel titleLabel,nameLabel,userLabel,passLabel,phoneLabel,bloodLabel;
    private JTextField nameField,usernameField,phoneField;
    private JPasswordField passwordField;
    private JComboBox<String> bloodCombo;
    private JButton registerButton,backButton;
    private JPanel panel;
    private Color redColor;

    public Register()
    {
        super("Blood Bank Management System - Register");
        this.setSize(450,460);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redColor=new Color(190,30,45);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        titleLabel=new JLabel("REGISTER NEW USER");
        titleLabel.setBounds(40,20,370,35);
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        titleLabel.setForeground(redColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        nameLabel=new JLabel("Full Name");
        nameLabel.setBounds(50,80,120,25);
        panel.add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(190,80,200,30);
        panel.add(nameField);

        userLabel=new JLabel("Username");
        userLabel.setBounds(50,122,120,25);
        panel.add(userLabel);

        usernameField=new JTextField();
        usernameField.setBounds(190,122,200,30);
        panel.add(usernameField);

        passLabel=new JLabel("Password");
        passLabel.setBounds(50,164,120,25);
        panel.add(passLabel);

        passwordField=new JPasswordField();
        passwordField.setBounds(190,164,200,30);
        panel.add(passwordField);

        phoneLabel=new JLabel("Phone Number");
        phoneLabel.setBounds(50,206,120,25);
        panel.add(phoneLabel);

        phoneField=new JTextField();
        phoneField.setBounds(190,206,200,30);
        panel.add(phoneField);

        bloodLabel=new JLabel("Blood Group");
        bloodLabel.setBounds(50,248,120,25);
        panel.add(bloodLabel);

        String[] bloodGroups={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodCombo=new JComboBox<String>(bloodGroups);
        bloodCombo.setBounds(190,248,200,30);
        panel.add(bloodCombo);

        registerButton=new JButton("Register");
        registerButton.setBounds(90,310,250,38);
        registerButton.setBackground(redColor);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        backButton=new JButton("Back to Login");
        backButton.setBounds(90,358,250,32);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(redColor);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        panel.add(backButton);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==registerButton)
        {
            String name=nameField.getText();
            String username=usernameField.getText();
            String password=new String(passwordField.getPassword());
            String phone=phoneField.getText();
            String blood=(String)bloodCombo.getSelectedItem();

            if(name.isEmpty()||username.isEmpty()||password.isEmpty()||phone.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please fill in all the fields.");
            }
            else if(Account.usernameExists(username))
            {
                JOptionPane.showMessageDialog(this,"This username is already taken.");
            }
            else
            {
                Account a1=new Account(name,username,password,phone,blood);
                a1.addAccount();

                JOptionPane.showMessageDialog(this,"Registration successful! You can login now.");

                this.setVisible(false);
                Login l1=new Login();
                l1.setVisible(true);
            }
        }

        else if(ae.getSource()==backButton)
        {
            this.setVisible(false);
            Login l1=new Login();
            l1.setVisible(true);
        }
    }
}
