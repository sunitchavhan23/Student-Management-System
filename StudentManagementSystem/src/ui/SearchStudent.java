package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class SearchStudent extends JFrame implements ActionListener {

    JLabel l1;

    JTextField t1;

    JButton btnSearch;

    JTable table;

    DefaultTableModel model;

    public SearchStudent() {

        setTitle("Search Student");

        setSize(700, 400);

        setLayout(null);

        l1 = new JLabel("Enter Name");

        l1.setBounds(50, 30, 100, 30);

        add(l1);

        t1 = new JTextField();

        t1.setBounds(150, 30, 150, 30);

        add(t1);

        btnSearch = new JButton("Search");

        btnSearch.setBounds(350, 30, 100, 30);

        add(btnSearch);

        btnSearch.addActionListener(this);

        String columns[] = {
                "ID",
                "Name",
                "Email",
                "Course",
                "Phone",
                "Date"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        sp.setBounds(20, 100, 640, 220);

        add(sp);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        model.setRowCount(0);

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students WHERE name LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "%" + t1.getText() + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                Object row[] = {

                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("phone"),
                        rs.getDate("enrollment_date")
                };

                model.addRow(row);
            }

            if (!found) {

                JOptionPane.showMessageDialog(this, "No Record Found");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}
