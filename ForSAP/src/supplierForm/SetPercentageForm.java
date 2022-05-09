package supplierForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SetPercentageForm extends JDialog {
    private JPanel PercentagePanel;
    private JTextField nameField;
    private JTextField percentageField;
    private JButton OKButton;
    private JButton backButton;

    public SetPercentageForm(Frame parent) {
        super(parent);
        setTitle("Set Percentage To Supplier");
        setContentPane(PercentagePanel);
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
                setPercentage();

            }
        });
        setVisible(true);
    }

    private void setPercentage() {
        String name = nameField.getText();
        double percentageInput = Double.parseDouble(percentageField.getText());
        double percentage;
        if (percentageInput > 0) {
            percentage = percentageInput * 0.01;
        } else {
            JOptionPane.showMessageDialog(this, "Please, enter valid percentage!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            percentage = 0;
        }
        double price = retrievePrice(name);
        double newPrice = price + price * percentage;
        updatePrice(name, newPrice);
    }

    private void updatePrice(String name, double newPrice) {
        double oldPrice = retrievePrice(name);
        if (oldPrice == 0) {
            JOptionPane.showMessageDialog(this, "Please, enter existing supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "update suppliers set suppliers.price = ? where suppliers.name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(newPrice));
            preparedStatement.setString(2, name);


            int affectedRows = preparedStatement.executeUpdate();
            double updatedPrice = retrievePrice(name);
            if (affectedRows > 0 && updatedPrice != oldPrice) {
                JOptionPane.showMessageDialog(this, "Successfully added percentage", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to add percentage!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private double retrievePrice(String name) {
        double price = 0;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from suppliers";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String nameOfSupplier = rs.getString("name");

                if (name.equals(nameOfSupplier)) {
                    price = rs.getInt("price");
                    break;
                }
            }

            rs.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Please, enter existing supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        return price;
    }
}
