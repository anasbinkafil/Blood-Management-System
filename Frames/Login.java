package Frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entities.*;

public class Login extends JFrame implements ActionListener
{
    private JLabel titleLabel,userLabel,passLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton,registerButton,adminButton;
    private JPanel panel;
    private Color redColor;

    public Login()
    {
        super("Blood Bank Management System - Login");
        this.setSize(420,380);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redColor=new Color(190,30,45);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        titleLabel=new JLabel("BLOOD BANK LOGIN");
        titleLabel.setBounds(40,25,340,35);
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        titleLabel.setForeground(redColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        userLabel=new JLabel("Username");
        userLabel.setBounds(60,90,100,25);
        panel.add(userLabel);

        usernameField=new JTextField();
        usernameField.setBounds(170,90,180,30);
        panel.add(usernameField);

        passLabel=new JLabel("Password");
        passLabel.setBounds(60,135,100,25);
        panel.add(passLabel);

        passwordField=new JPasswordField();
        passwordField.setBounds(170,135,180,30);
        panel.add(passwordField);

        loginButton=new JButton("Login");
        loginButton.setBounds(90,190,220,38);
        loginButton.setBackground(redColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        registerButton=new JButton("New User? Register");
        registerButton.setBounds(90,240,220,32);
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(redColor);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        adminButton=new JButton("Admin Login");
        adminButton.setBounds(90,282,220,32);
        adminButton.setBackground(Color.WHITE);
        adminButton.setForeground(redColor);
        adminButton.setFocusPainted(false);
        adminButton.addActionListener(this);
        panel.add(adminButton);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==loginButton)
        {
            String username=usernameField.getText();
            String password=new String(passwordField.getPassword());

            if(username.isEmpty()||password.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please enter username and password.");
            }
            else
            {
                Account a1=new Account();

                if(a1.getAccount(username,password)==true)
                {
                    this.setVisible(false);
                    UserHome h1=new UserHome(username);
                    h1.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Wrong username or password.");
                }
            }
        }

        else if(ae.getSource()==registerButton)
        {
            this.setVisible(false);
            Register r1=new Register();
            r1.setVisible(true);
        }

        else if(ae.getSource()==adminButton)
        {
            this.setVisible(false);
            AdminLogin a1=new AdminLogin();
            a1.setVisible(true);
        }
    }
}
