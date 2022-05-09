package categoryForm;

import categoryForm.channelCategories.ChannelCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCategory extends JDialog {
    private JTextField typeCategoryField;
    private JTextField priceField;
    private JButton backButton;
    private JButton OKButton;
    private JPanel AddCategoryPanel;

    public AddCategory(Frame parent) {
        super(parent);
        setTitle("Add Category");
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
                addChannel();
            }
        });
        setVisible(true);
    }

    private void addChannel() {
        String name = typeCategoryField.getText();
        String priceInput = priceField.getText();
        if (priceInput.equals("")){
            JOptionPane.showMessageDialog(this, "Enter price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            double price = Double.parseDouble(priceInput);

            ChannelCategory category = addCategoryToDB(name, price);
            if (category != null) {
                JOptionPane.showMessageDialog(this, "Successfully added category", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {

                JOptionPane.showMessageDialog(this, "Fail to register new category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private ChannelCategory addCategoryToDB(String name, double price) {
        ChannelCategory category = null;
        boolean isRight = true;
        if (price <= 0) {
            JOptionPane.showMessageDialog(this, "Please, enter valid price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid category name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO categories(typeCategory, price)" + "values(?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, String.valueOf(price));

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 && isRight) {
                category = new ChannelCategory(name, price);
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return category;
    }

}
