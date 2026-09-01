package Frames;

import java.lang.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import Entities.*;

public class UserHome extends JFrame implements ActionListener
{
    private JTable stockTable;
    private JButton requestButton,logoutButton;
    private JPanel panel;
    private Color redColor;
    private String username;

    public UserHome(String username)
    {
        super("Blood Bank Management System - Home");
        this.username=username;

        this.setSize(500,430);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redColor=new Color(190,30,45);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel=new JLabel("Welcome, "+username);
        titleLabel.setBounds(40,20,400,35);
        titleLabel.setFont(new Font("Arial",Font.BOLD,20));
        titleLabel.setForeground(redColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        JLabel subLabel=new JLabel("Available Blood Stock");
        subLabel.setBounds(40,65,400,25);
        subLabel.setFont(new Font("Arial",Font.BOLD,14));
        panel.add(subLabel);

        String[] columns={"Blood Group","Bags Available"};
        DefaultTableModel model=new DefaultTableModel(columns,0);
        model.addRow(new Object[]{"A+",12});
        model.addRow(new Object[]{"A-",5});
        model.addRow(new Object[]{"B+",9});
        model.addRow(new Object[]{"B-",3});
        model.addRow(new Object[]{"AB+",4});
        model.addRow(new Object[]{"AB-",2});
        model.addRow(new Object[]{"O+",15});
        model.addRow(new Object[]{"O-",6});

        stockTable=new JTable(model);
        stockTable.setRowHeight(26);

        JScrollPane scrollPane=new JScrollPane(stockTable);
        scrollPane.setBounds(60,95,370,200);
        panel.add(scrollPane);

        requestButton=new JButton("Request Blood");
        requestButton.setBounds(90,315,300,38);
        requestButton.setBackground(redColor);
        requestButton.setForeground(Color.WHITE);
        requestButton.setFocusPainted(false);
        requestButton.addActionListener(this);
        panel.add(requestButton);

        logoutButton=new JButton("Logout");
        logoutButton.setBounds(90,360,300,32);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(redColor);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==requestButton)
        {
            int selectedRow=stockTable.getSelectedRow();

            if(selectedRow==-1)
            {
                JOptionPane.showMessageDialog(this,"Please select a blood group from the table first.");
            }
            else
            {
                String bloodGroup=stockTable.getValueAt(selectedRow,0).toString();
                String bags=JOptionPane.showInputDialog(this,"How many bags of "+bloodGroup+" do you need?");

                if(bags!=null&&!bags.trim().isEmpty())
                {
                    JOptionPane.showMessageDialog(this,
                        "Request submitted!\nBlood Group: "+bloodGroup+"\nBags: "+bags+
                        "\nOur team will contact you soon.");
                }
            }
        }

        else if(ae.getSource()==logoutButton)
        {
            this.setVisible(false);
            Login l1=new Login();
            l1.setVisible(true);
        }
    }
}
