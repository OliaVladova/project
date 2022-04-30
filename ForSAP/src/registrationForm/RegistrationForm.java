package registrationForm;

import users.UserOfTV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JDialog {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton createAccountButton;
    private JButton cancelButton;
    private JPanel registrationPanel;
    public UserOfTV user;


    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Register new user");
        setContentPane(registrationPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void createUser() {
        String name = nameField.getText();
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
            dispose();
        } else {

            JOptionPane.showMessageDialog(this, "Fail to register new user", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }
    }


    private UserOfTV addUserToDB(String name, String email, String password) {
        UserOfTV user = null;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO clients (name, password, email,role_id)" + "values(?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, "2");
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new UserOfTV(name, password, email);
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm(null);
        UserOfTV user = registrationForm.user;
        if (user != null) {
            System.out.println("Successfully registration of user:" + user.getName());
        } else {
            System.out.println("Registration canceled!");
        }
    }
}
