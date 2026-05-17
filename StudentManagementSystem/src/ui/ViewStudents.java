package ui;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewStudents extends JFrame {

    JTable table;
    JScrollPane sp;

    public ViewStudents() {

        setTitle("View Students");
        setSize(700,400);

        String columns[] = {
                "ID","Name","Email","Course","Phone","Date"
        };

        DefaultTableModel model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM students";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {

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

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        sp = new JScrollPane(table);

        add(sp);

        setVisible(true);
    }
}
