package supplierForm;

import supplierForm.suppliers.BaseSupplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddSupplier extends JDialog{
    private JPanel AddSupplier;
    private JTextField nameField;
    private JTextField priceField;
    private JButton OKButton;
    private JButton backButton;

    public AddSupplier(Frame parent) {
        super(parent);
        setTitle("Add Supplier");
        setContentPane(AddSupplier);
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
             addSupplier();
            }
        });
        setVisible(true);
    }

    private void addSupplier() {
        String name = nameField.getText();
        String price = priceField.getText();

        BaseSupplier supplier = addSupplierToDB(name, price);
        if (supplier != null) {
            JOptionPane.showMessageDialog(this, "Successfully added supplier", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private BaseSupplier addSupplierToDB(String name, String price) {
        BaseSupplier supplier = null;
        double priceToAdd = Double.parseDouble(price);
        boolean isRight = true;
        if (priceToAdd <= 0) {
            JOptionPane.showMessageDialog(this, "Please, enter valid price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        if (name==null||name.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please, enter valid name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO suppliers(name, price)" + "values(?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, price);

           int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 && isRight) {
                supplier = new BaseSupplier(name, priceToAdd);
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        return supplier;
    }
}
