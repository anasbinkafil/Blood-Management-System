package Frames;

import java.lang.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import Entities.*;

public class AdminHome extends JFrame implements ActionListener,ListSelectionListener
{
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField nameField,usernameField,passwordField,phoneField;
    private JComboBox<String> bloodCombo;
    private JButton addButton,updateButton,deleteButton,clearButton,logoutButton;
    private JPanel panel;
    private Color redColor;
    private int selectedRow=-1;

    public AdminHome()
    {
        super("Blood Bank Management System - Admin");
        this.setSize(650,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redColor=new Color(190,30,45);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel=new JLabel("MANAGE USERS");
        titleLabel.setBounds(80,15,490,35);
        titleLabel.setFont(new Font("Arial",Font.BOLD,22));
        titleLabel.setForeground(redColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel);

        String[] columns={"Name","Username","Password","Phone","Blood Group"};
        tableModel=new DefaultTableModel(columns,0);

        userTable=new JTable(tableModel);
        userTable.setRowHeight(28);
        userTable.getSelectionModel().addListSelectionListener(this);

        JScrollPane scrollPane=new JScrollPane(userTable);
        scrollPane.setBounds(50,60,550,180);
        panel.add(scrollPane);

        loadTableFromFile();

        JLabel nameLabel=new JLabel("Name");
        nameLabel.setBounds(90,255,110,25);
        panel.add(nameLabel);

        nameField=new JTextField();
        nameField.setBounds(220,255,250,30);
        panel.add(nameField);

        JLabel userLabel=new JLabel("Username");
        userLabel.setBounds(90,295,110,25);
        panel.add(userLabel);

        usernameField=new JTextField();
        usernameField.setBounds(220,295,250,30);
        panel.add(usernameField);

        JLabel passLabel=new JLabel("Password");
        passLabel.setBounds(90,335,110,25);
        panel.add(passLabel);

        passwordField=new JTextField();
        passwordField.setBounds(220,335,250,30);
        panel.add(passwordField);

        JLabel phoneLabel=new JLabel("Phone");
        phoneLabel.setBounds(90,375,110,25);
        panel.add(phoneLabel);

        phoneField=new JTextField();
        phoneField.setBounds(220,375,250,30);
        panel.add(phoneField);

        JLabel bloodLabel=new JLabel("Blood Group");
        bloodLabel.setBounds(90,415,110,25);
        panel.add(bloodLabel);

        String[] bloodGroups={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
        bloodCombo=new JComboBox<String>(bloodGroups);
        bloodCombo.setBounds(220,415,250,30);
        panel.add(bloodCombo);

        addButton=new JButton("Add");
        addButton.setBounds(90,465,110,38);
        addButton.setBackground(redColor);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(this);
        panel.add(addButton);

        updateButton=new JButton("Update");
        updateButton.setBounds(210,465,110,38);
        updateButton.setBackground(redColor);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        deleteButton=new JButton("Delete");
        deleteButton.setBounds(330,465,110,38);
        deleteButton.setBackground(redColor);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        clearButton=new JButton("Clear");
        clearButton.setBounds(450,465,110,38);
        clearButton.setBackground(Color.WHITE);
        clearButton.setForeground(redColor);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        logoutButton=new JButton("Logout");
        logoutButton.setBounds(210,515,220,34);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(redColor);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);
        panel.add(logoutButton);

        this.add(panel);
    }

    public void loadTableFromFile()
    {
        tableModel.setRowCount(0);

        for(String[] row:Account.loadAllAccounts())
        {
            tableModel.addRow(row);
        }
    }

    public void saveTableToFile()
    {
        List<String[]> list=new ArrayList<String[]>();

        for(int i=0;i<tableModel.getRowCount();i++)
        {
            String[] row=new String[5];

            for(int col=0;col<5;col++)
            {
                row[col]=tableModel.getValueAt(i,col).toString();
            }

            list.add(row);
        }

        Account.saveAllAccounts(list);
    }

    public void clearFields()
    {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        phoneField.setText("");
        bloodCombo.setSelectedIndex(0);
        selectedRow=-1;
        userTable.clearSelection();
    }

    public void valueChanged(ListSelectionEvent le)
    {
        if(!le.getValueIsAdjusting()&&userTable.getSelectedRow()!=-1)
        {
            selectedRow=userTable.getSelectedRow();

            nameField.setText(tableModel.getValueAt(selectedRow,0).toString());
            usernameField.setText(tableModel.getValueAt(selectedRow,1).toString());
            passwordField.setText(tableModel.getValueAt(selectedRow,2).toString());
            phoneField.setText(tableModel.getValueAt(selectedRow,3).toString());
            bloodCombo.setSelectedItem(tableModel.getValueAt(selectedRow,4).toString());
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        String name=nameField.getText();
        String username=usernameField.getText();
        String password=passwordField.getText();
        String phone=phoneField.getText();
        String blood=(String)bloodCombo.getSelectedItem();

        if(ae.getSource()==addButton)
        {
            if(name.isEmpty()||username.isEmpty()||password.isEmpty()||phone.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please fill in all the fields.");
            }
            else if(Account.usernameExists(username))
            {
                JOptionPane.showMessageDialog(this,"Username already exists. Use Update instead.");
            }
            else
            {
                tableModel.addRow(new Object[]{name,username,password,phone,blood});
                saveTableToFile();
                clearFields();
                JOptionPane.showMessageDialog(this,"User added successfully.");
            }
        }

        else if(ae.getSource()==updateButton)
        {
            if(selectedRow==-1)
            {
                JOptionPane.showMessageDialog(this,"Please select a user from the table to update.");
            }
            else if(name.isEmpty()||username.isEmpty()||password.isEmpty()||phone.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please fill in all the fields.");
            }
            else
            {
                tableModel.setValueAt(name,selectedRow,0);
                tableModel.setValueAt(username,selectedRow,1);
                tableModel.setValueAt(password,selectedRow,2);
                tableModel.setValueAt(phone,selectedRow,3);
                tableModel.setValueAt(blood,selectedRow,4);

                saveTableToFile();
                clearFields();
                JOptionPane.showMessageDialog(this,"User updated successfully.");
            }
        }

        else if(ae.getSource()==deleteButton)
        {
            if(selectedRow==-1)
            {
                JOptionPane.showMessageDialog(this,"Please select a user from the table to delete.");
            }
            else
            {
                int confirm=JOptionPane.showConfirmDialog(this,"Delete this user?","Confirm",JOptionPane.YES_NO_OPTION);

                if(confirm==JOptionPane.YES_OPTION)
                {
                    tableModel.removeRow(selectedRow);
                    saveTableToFile();
                    clearFields();
                }
            }
        }

        else if(ae.getSource()==clearButton)
        {
            clearFields();
        }

        else if(ae.getSource()==logoutButton)
        {
            this.setVisible(false);
            Login l1=new Login();
            l1.setVisible(true);
        }
    }
}
