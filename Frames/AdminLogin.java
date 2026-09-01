package Frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entities.*;

public class AdminLogin extends JFrame implements ActionListener
{
    private JLabel titleLabel,idLabel,passLabel;
    private JTextField idField;
    private JPasswordField passField;
    private JButton loginButton,backButton;
    private JPanel panel;
    private Color redColor;

    public AdminLogin()
    {
        super("Blood Bank Management System - Admin Login");
        this.setSize(420,320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redColor=new Color(190,30,45);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        titleLabel=new JLabel("ADMIN LOGIN");
        titleLabel.setBounds(40,25,340,35);
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        titleLabel.setForeground(redColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        idLabel=new JLabel("Admin ID");
        idLabel.setBounds(60,95,100,25);
        panel.add(idLabel);

        idField=new JTextField();
        idField.setBounds(170,95,180,30);
        panel.add(idField);

        passLabel=new JLabel("Password");
        passLabel.setBounds(60,140,100,25);
        panel.add(passLabel);

        passField=new JPasswordField();
        passField.setBounds(170,140,180,30);
        panel.add(passField);

        loginButton=new JButton("Login");
        loginButton.setBounds(90,195,220,38);
        loginButton.setBackground(redColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        backButton=new JButton("Back");
        backButton.setBounds(90,242,220,32);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(redColor);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        panel.add(backButton);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==loginButton)
        {
            String id=idField.getText();
            String password=new String(passField.getPassword());

            if(id.isEmpty()||password.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please enter an Admin ID and Password.");
            }
            else
            {
                this.setVisible(false);
                AdminHome a1=new AdminHome();
                a1.setVisible(true);
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
