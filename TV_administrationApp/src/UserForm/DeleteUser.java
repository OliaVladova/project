package UserForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteUser extends JDialog{
    private JPanel DeleteUserPanel;
    private JTextField nameField;
    private JButton backButton;
    private JButton OKButton;

    public DeleteUser(Frame parent) {
        super(parent);
        setTitle("Delete User");
        setContentPane(DeleteUserPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        setVisible(true);
    }

    private void deleteUser() {
        String name = nameField.getText();
        if (name==null||name.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter name! ", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "delete from clients where clients.name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Successfully deleted user", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {

                JOptionPane.showMessageDialog(this, "Fail to delete user!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
