package ui;

import db.DBConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf;
    JButton btnLogin, btnRegister;

    public LoginForm() {

        setTitle("Login");
        setSize(400,300);
        setLayout(null);

        l1 = new JLabel("Username");
        l1.setBounds(50,50,100,30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150,50,150,30);
        add(tf1);

        l2 = new JLabel("Password");
        l2.setBounds(50,100,100,30);
        add(l2);

        pf = new JPasswordField();
        pf.setBounds(150,100,150,30);
        add(pf);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50,180,100,30);
        add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200,180,100,30);
        add(btnRegister);

        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnLogin) {

            String username = tf1.getText();
            String password = pf.getText();

            try {

                Connection con = DBConnection.getConnection();

                String query = "SELECT * FROM users WHERE username=? AND password=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {

                    JOptionPane.showMessageDialog(this, "Login Successful");

                    dispose();

                    new Dashboard();

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials");
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==btnRegister) {
            dispose();
            new RegisterForm();
        }
    }
}
