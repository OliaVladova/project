package logInForm;

import users.UserOfTV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn extends JDialog{
    private JTextField tfName;
    private JPasswordField tfPassword;
    private JButton logInButton;
    private JPanel LoginPanel;
    public UserOfTV user;

    public LogIn(JFrame parent) {
        super(parent);
        setTitle("Log in account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(430,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfName.getText();
                String password = String.valueOf(tfPassword.getPassword());
                user = getAuthenticalUser(username,password);
                if (user != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LogIn.this, "Username or password is invalid!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        setVisible(true);
    }

    private UserOfTV getAuthenticalUser(String username, String password) {
        UserOfTV user = null;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration" , "root" ,"OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "select * from clients where name =? and password=?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result  = preparedStatement.executeQuery();
            if (result.next()){
                user = new UserOfTV(result.getString("name"),result.getString("password"),result.getString("email"));
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        LogIn logIn = new LogIn(null);
        UserOfTV user = logIn.user;
        if (user!= null){
            System.out.println("Successfully entry for user:" + user.getName());
        }else {
            System.out.println("LogIn failed!");
        }
    }
}
