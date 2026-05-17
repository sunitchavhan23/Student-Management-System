package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteStudent extends JFrame implements ActionListener {

    JLabel l1;

    JTextField t1;

    JButton btnDelete;

    public DeleteStudent() {

        setTitle("Delete Student");

        setSize(350, 250);

        setLayout(null);

        l1 = new JLabel("Student ID");

        l1.setBounds(50, 50, 100, 30);

        add(l1);

        t1 = new JTextField();

        t1.setBounds(150, 50, 120, 30);

        add(t1);

        btnDelete = new JButton("Delete");

        btnDelete.setBounds(100, 130, 100, 30);

        add(btnDelete);

        btnDelete.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure?"
            );

            if (confirm == 0) {

                Connection con = DBConnection.getConnection();

                String query = "DELETE FROM students WHERE student_id=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(t1.getText()));

                int i = ps.executeUpdate();

                if (i > 0) {

                    JOptionPane.showMessageDialog(this, "Student Deleted");

                } else {

                    JOptionPane.showMessageDialog(this, "Student Not Found");
                }
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}