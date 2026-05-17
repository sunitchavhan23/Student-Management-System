package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterForm extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField tf1, tf2;
    JPasswordField pf;
    JButton btnRegister, btnLogin;

    public RegisterForm() {

        setTitle("User Registration");
        setSize(400, 300);
        setLayout(null);

        l1 = new JLabel("Username");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150, 50, 150, 30);
        add(tf1);

        l2 = new JLabel("Email");
        l2.setBounds(50, 100, 100, 30);
        add(l2);

        tf2 = new JTextField();
        tf2.setBounds(150, 100, 150, 30);
        add(tf2);

        l3 = new JLabel("Password");
        l3.setBounds(50, 150, 100, 30);
        add(l3);

        pf = new JPasswordField();
        pf.setBounds(150, 150, 150, 30);
        add(pf);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50, 220, 100, 30);
        add(btnRegister);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(200, 220, 100, 30);
        add(btnLogin);

        btnRegister.addActionListener(this);
        btnLogin.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnRegister) {

            String username = tf1.getText();
            String email = tf2.getText();
            String password = pf.getText();

            try {

                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO users(username,email,password) VALUES(?,?,?)";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);

                int i = ps.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(this, "Registration Successful");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnLogin) {
            dispose();
            new LoginForm();
        }
    }

    public static void main(String[] args) {
        new RegisterForm();
    }
}