package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudent extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4,t5;
    JButton btn;

    public AddStudent() {

        setTitle("Add Student");
        setSize(400,400);
        setLayout(null);

        l1 = new JLabel("Name");
        l1.setBounds(50,50,100,30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(150,50,150,30);
        add(t1);

        l2 = new JLabel("Email");
        l2.setBounds(50,100,100,30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(150,100,150,30);
        add(t2);

        l3 = new JLabel("Course");
        l3.setBounds(50,150,100,30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(150,150,150,30);
        add(t3);

        l4 = new JLabel("Phone");
        l4.setBounds(50,200,100,30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(150,200,150,30);
        add(t4);

        l5 = new JLabel("Date");
        l5.setBounds(50,250,100,30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(150,250,150,30);
        add(t5);

        btn = new JButton("Save");
        btn.setBounds(120,320,100,30);
        add(btn);

        btn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO students(name,email,course,phone,enrollment_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, t1.getText());
            ps.setString(2, t2.getText());
            ps.setString(3, t3.getText());
            ps.setString(4, t4.getText());
            ps.setString(5, t5.getText());

            int i = ps.executeUpdate();

            if(i>0) {
                JOptionPane.showMessageDialog(this,"Student Added");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}