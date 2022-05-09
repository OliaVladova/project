package startUp;

import forAdmin.AdminForm;
import logInForm.LogIn;
import registrationForm.RegistrationForm;
import UserForm.users.UserOfTV;
import userLogIn.UserLogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StartUp extends JDialog {
    private JButton logInButton;
    private JButton Register;
    private JPanel startUp;
    private String userRole;

    public StartUp(JFrame parent) {
        super(parent);
        setTitle("Starting...");
        setContentPane(startUp);
        setMinimumSize(new Dimension(430, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn logIn = new LogIn(null);
                UserOfTV user = logIn.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(StartUp.this, "Successful logIn!", "Try again",
                            JOptionPane.INFORMATION_MESSAGE);
                    returnRole(user);
                    int id = returnId(user);
                   if (userRole.equals("admin")){
                       AdminForm adminForm = new AdminForm(null);
                   }else if (userRole.equals("user")){
                       UserLogIn userLogIn = new UserLogIn(null,id);
                   }
                } else {
                    JOptionPane.showMessageDialog(StartUp.this, "LogIn failed!", "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm(null);
                UserOfTV user = registrationForm.user;

                if (user != null) {
                    int id = returnId(user);
                    UserLogIn logIn = new UserLogIn(null,id);
                    JOptionPane.showMessageDialog(StartUp.this, "Successful registration!", "Try again",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();


                } else {
                    JOptionPane.showMessageDialog(StartUp.this, "Registration failed!", "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        setVisible(true);
    }

    public void returnRole(UserOfTV user) {
        int role_id = 0;
        String name = user.getName();
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from clients" ;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String nameOfUser = rs.getString("name");

                if (name.equals(nameOfUser)){
                    role_id = rs.getInt("role_id");
                }
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(StartUp.this, "An exception occurred!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }
        if (role_id == 1) {
            userRole = "admin";
        }else if (role_id == 2){
            userRole = "user";
        }
    }
    public int returnId(UserOfTV user) {
        int id = 0;
        String name = user.getName();
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from clients" ;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String nameOfUser = rs.getString("name");

                if (name.equals(nameOfUser)){
                    id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (Exception exception) {
            System.out.println("SQLException: " + exception.getMessage());
        }
       return id;
    }
}
