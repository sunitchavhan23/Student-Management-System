package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4, l5, l6;

    JTextField t1, t2, t3, t4, t5, t6;

    JButton btnSearch, btnUpdate;

    public UpdateStudent() {

        setTitle("Update Student");
        setSize(400, 500);
        setLayout(null);

        l1 = new JLabel("Student ID");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        t1 = new JTextField();
        t1.setBounds(180, 50, 150, 30);
        add(t1);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(120, 100, 100, 30);
        add(btnSearch);

        l2 = new JLabel("Name");
        l2.setBounds(50, 150, 100, 30);
        add(l2);

        t2 = new JTextField();
        t2.setBounds(180, 150, 150, 30);
        add(t2);

        l3 = new JLabel("Email");
        l3.setBounds(50, 200, 100, 30);
        add(l3);

        t3 = new JTextField();
        t3.setBounds(180, 200, 150, 30);
        add(t3);

        l4 = new JLabel("Course");
        l4.setBounds(50, 250, 100, 30);
        add(l4);

        t4 = new JTextField();
        t4.setBounds(180, 250, 150, 30);
        add(t4);

        l5 = new JLabel("Phone");
        l5.setBounds(50, 300, 100, 30);
        add(l5);

        t5 = new JTextField();
        t5.setBounds(180, 300, 150, 30);
        add(t5);

        l6 = new JLabel("Date");
        l6.setBounds(50, 350, 100, 30);
        add(l6);

        t6 = new JTextField();
        t6.setBounds(180, 350, 150, 30);
        add(t6);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(130, 410, 100, 30);
        add(btnUpdate);

        btnSearch.addActionListener(this);
        btnUpdate.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {

            try {

                Connection con = DBConnection.getConnection();

                String query = "SELECT * FROM students WHERE student_id=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(t1.getText()));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("email"));
                    t4.setText(rs.getString("course"));
                    t5.setText(rs.getString("phone"));
                    t6.setText(rs.getString("enrollment_date"));

                } else {

                    JOptionPane.showMessageDialog(this, "Student Not Found");
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnUpdate) {

            try {

                Connection con = DBConnection.getConnection();

                String query = "UPDATE students SET name=?,email=?,course=?,phone=?,enrollment_date=? WHERE student_id=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, t2.getText());
                ps.setString(2, t3.getText());
                ps.setString(3, t4.getText());
                ps.setString(4, t5.getText());
                ps.setString(5, t6.getText());

                ps.setInt(6, Integer.parseInt(t1.getText()));

                int i = ps.executeUpdate();

                if (i > 0) {

                    JOptionPane.showMessageDialog(this, "Student Updated");
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}