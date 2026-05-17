package ui;

import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    JMenuBar mb;

    JMenu fileMenu, studentMenu, searchMenu, helpMenu;

    JMenuItem logout, exit;
    JMenuItem addStudent, viewStudents, updateStudent, deleteStudent;
    JMenuItem searchStudent;
    JMenuItem about;

    public Dashboard() {

        setTitle("Student Management Dashboard");
        setSize(700, 500);

        mb = new JMenuBar();

        // MENUS
        fileMenu = new JMenu("File");
        studentMenu = new JMenu("Student");
        searchMenu = new JMenu("Search");
        helpMenu = new JMenu("Help");

        // FILE MENU
        logout = new JMenuItem("Logout");
        exit = new JMenuItem("Exit");

        // STUDENT MENU
        addStudent = new JMenuItem("Add Student");
        viewStudents = new JMenuItem("View Students");
        updateStudent = new JMenuItem("Update Student");
        deleteStudent = new JMenuItem("Delete Student");

        // SEARCH MENU
        searchStudent = new JMenuItem("Search Student");

        // HELP MENU
        about = new JMenuItem("About");

        // ADD ITEMS
        fileMenu.add(logout);
        fileMenu.add(exit);

        studentMenu.add(addStudent);
        studentMenu.add(viewStudents);
        studentMenu.add(updateStudent);
        studentMenu.add(deleteStudent);

        searchMenu.add(searchStudent);

        helpMenu.add(about);

        // ADD MENUS
        mb.add(fileMenu);
        mb.add(studentMenu);
        mb.add(searchMenu);
        mb.add(helpMenu);

        setJMenuBar(mb);

        // ACTIONS
        logout.addActionListener(this);
        exit.addActionListener(this);

        addStudent.addActionListener(this);
        viewStudents.addActionListener(this);
        updateStudent.addActionListener(this);
        deleteStudent.addActionListener(this);

        searchStudent.addActionListener(this);

        about.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logout) {

            JOptionPane.showMessageDialog(this, "Logged Out");

            dispose();

            new LoginForm();
        }

        if (e.getSource() == exit) {

            System.exit(0);
        }

        if (e.getSource() == addStudent) {

            new AddStudent();
        }

        if (e.getSource() == viewStudents) {

            new ViewStudents();
        }

        if (e.getSource() == updateStudent) {

            new UpdateStudent();
        }

        if (e.getSource() == deleteStudent) {

            new DeleteStudent();
        }

        if (e.getSource() == searchStudent) {

            new SearchStudent();
        }

        if (e.getSource() == about) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Management System\nUsing Java Swing + JDBC"
            );
        }
    }
}