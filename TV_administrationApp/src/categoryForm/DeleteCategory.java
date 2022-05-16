package categoryForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteCategory extends JDialog {
    private JPanel DeleteCategoryPanel;
    private JTextField categoryNameField;
    private JButton backButton;
    private JButton OKButton;

    public DeleteCategory(Frame parent) {
        super(parent);
        setTitle("Delete Category");
        setContentPane(DeleteCategoryPanel);
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
                deleteCategory();
            }
        });
        setVisible(true);
    }

    private void deleteCategory() {
        String name = categoryNameField.getText();
        boolean isRight = true;
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid category name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "delete from categories where categories.typeCategory = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 && isRight) {
                JOptionPane.showMessageDialog(this, "Successfully deleted category", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }else if (isRight){
                JOptionPane.showMessageDialog(this, "Unknown category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to delete category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
