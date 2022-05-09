package UserForm;

import UserForm.users.UserOfTV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddAdmin extends JDialog {
    private JPanel AddAdminPanel;
    private JTextField adminNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton backButton;
    private JButton OKButton;
    private UserOfTV user;

    public AddAdmin(JFrame parent) {
        super(parent);
        setTitle("Register new admin");
        setContentPane(AddAdminPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void createUser() {
        String name = adminNameField.getText();
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Different passwords", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDB(name, email, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Successfully register new admin", "Success",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Fail to register new admin", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private UserOfTV addUserToDB(String name, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter email!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (password == null || password.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter password", "Try again",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    UserOfTV user = null;
                    try {
                        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                        Statement statement = connect.createStatement();
                        String query = "INSERT INTO clients (name, password, email,role_id)" + "values(?,?,?,?)";
                        PreparedStatement preparedStatement = connect.prepareStatement(query);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, "1");
                        int addedRows = preparedStatement.executeUpdate();
                        if (addedRows > 0) {
                            user = new UserOfTV(name, password, email);
                        }
                        statement.close();
                        connect.close();
                    } catch (SQLException exception) {
                        JOptionPane.showMessageDialog(this, "Fail to register new admin", "Try again",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }
        return user;
    }


}
