package supplierForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCategoryToSupplier extends JDialog {
    private JPanel AddCategoryPanel;
    private JTextField nameField;
    private JTextField categoryNameField;
    private JButton OKButton;
    private JButton backButton;

    public AddCategoryToSupplier(Frame parent) {
        super(parent);
        setTitle("Add Category To Supplier");
        setContentPane(AddCategoryPanel);
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

                addCategoryToSupplier();
            }
        });
        setVisible(true);
    }

    private void addCategoryToSupplier() {
        String name = nameField.getText();
        String category = categoryNameField.getText();
        int supplier_id = retrieveSupplierId(name);
        int category_id = retrieveCategoryId(category);
        boolean isRight = true;
        if (supplier_id == 0 || category_id == 0) {
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO supplier_category(supplier_id, category_id)" + "values(?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(supplier_id));
            preparedStatement.setString(2, String.valueOf(category_id));

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 && isRight) {
                JOptionPane.showMessageDialog(this, "Successfully added category to supplier", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to add new category to supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int retrieveCategoryId(String category) {
        int category_id = 0;
        boolean isRight = true;
        if (category == null || category.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid category name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from categories";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()&& isRight) {
                String categoryName = rs.getString("typeCategory");

                if (category.equals(categoryName) ) {
                    category_id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please, enter existing category name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        return category_id;
    }

    private int retrieveSupplierId(String name) {
        int supplier_id = 0;
        boolean isRight = true;
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid supplier name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from suppliers";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()&& isRight) {
                String nameOfSupplier = rs.getString("name");

                if (name.equals(nameOfSupplier)) {
                    supplier_id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Please, enter valid supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        return supplier_id;
    }
}
