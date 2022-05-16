package supplierForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddChannelToSupplier extends JDialog {
    private JPanel AddChannelToSupplierPanel;
    private JTextField supplierNameField;
    private JTextField channelNameField;
    private JButton backButton;
    private JButton OKButton;

    public AddChannelToSupplier(Frame parent) {
        super(parent);
        setTitle("Add Channel To Supplier");
        setContentPane(AddChannelToSupplierPanel);
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

                addChannelToSupplier();
            }
        });
        setVisible(true);
    }

    private void addChannelToSupplier() {
        String supplierName = supplierNameField.getText();
        String channel = channelNameField.getText();
        int supplier_id = retrieveSupplierId(supplierName);
        int channel_id = retrieveChannelId(channel);
        boolean isRight = true;
        if (supplier_id == 0 || channel_id == 0) {
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO channel_supplier(supplier_id, channel_id)" + "values(?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(supplier_id));
            preparedStatement.setString(2, String.valueOf(channel_id));

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0 && isRight) {
                JOptionPane.showMessageDialog(this, "Successfully added channel to supplier", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to add new channel to supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int retrieveChannelId(String channel) {
        int channel_id = 0;
        boolean isRight = true;
        if (channel == null || channel.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid channel name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from channels";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next() && isRight) {
                String categoryName = rs.getString("name");

                if (channel.equals(categoryName)) {
                    channel_id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Please, enter existing channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        return channel_id;
    }

    private int retrieveSupplierId(String supplierName) {
        int supplier_id = 0;
        boolean isRight = true;
        if (supplierName == null || supplierName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid supplier name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from suppliers";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next() && isRight) {
                String nameOfSupplier = rs.getString("name");

                if (supplierName.equals(nameOfSupplier)) {
                    supplier_id = rs.getInt("id");
                }
            }

            rs.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Please, enter existing supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        return supplier_id;
    }
}
