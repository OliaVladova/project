package userLogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogIn extends JDialog {
    private JButton buyChannelButton;
    private JButton buyCategoryButton;
    private JButton showCategoriesButton;
    private JButton showChannelsButton;
    private JButton showSuppliersButton;

    private JPanel UserLogIn;
    private JButton myTaxesButton;
    private JButton myContractsButton;

    public UserLogIn(Frame parent, int id) {
        super(parent);
        setTitle("User");
        setContentPane(UserLogIn);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        buyChannelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyChannel buyChannel = new BuyChannel(null, id);
            }
        });


        buyCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyCategory buyCategory = new BuyCategory(null, id);
            }
        });

        showCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCategories();
            }
        });

        showChannelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChannels();
            }
        });

        showSuppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSuppliers();
            }
        });

        myTaxesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sumTaxes(id);
            }
        });

        myContractsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyContracts(id);
            }
        });
        setVisible(true);
    }

    private void sumTaxes(int id) {
        double suppliersPrice = getSupplierPrice(id);
        double channelPrice = getChannelPrice(id);
        double categoriesPrice = getCategoriesPrice(id);
        double contractCategory = getContractPriceForCategory(id);
        double contractChannels = getContractChannelPrice(id);
        double all = suppliersPrice + channelPrice + categoriesPrice + contractCategory + contractChannels;
        String message = String.format("Your total tax is %.2f leva", all);
        JOptionPane.showMessageDialog(this, message, "Taxes",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private double getCategoriesPrice(int id) {
        double result = 0;

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select categories.price" +
                    " from categories join client_category" +
                    " on client_category.category_id = categories.id" +
                    " join clients" +
                    " on clients.id = client_category.client_id" +
                    " where clients.id = " + id;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result += rs.getDouble("price");
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Taxes",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private double getContractPriceForCategory(int id) {
        double result = 0;

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select categories.price" +
                    " from categories join contract_category" +
                    " on contract_category.category_id = categories.id" +
                    " join contracts" +
                    " on contracts.id = contract_category.contract_id" +
                    " join clients" +
                    " on clients.id = contracts.client_id" +
                    " where clients.id = " + id;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result += rs.getDouble("price");
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Taxes",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private double getContractChannelPrice(int id) {
        double result = 0;

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select channels.price" +
                    "        from channels join contract_channel" +
                    "        on contract_channel.channel_id = channels.id" +
                    "        join contracts" +
                    "        on contracts.id = contract_channel.contract_id" +
                    "        join clients" +
                    "        on clients.id = contracts.client_id" +
                    "        where clients.id = " + id;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result += rs.getDouble("price");
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Taxes",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private double getSupplierPrice(int id) {
        double result = 0;

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select suppliers.price" +
                    " from suppliers join contracts" +
                    " on suppliers.id = contracts.supplier_id" +
                    " join clients" +
                    " on clients.id = contracts.client_id" +
                    " where clients.id = " + id;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result += rs.getDouble("price");
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Taxes",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private double getChannelPrice(int id) {
        double result = 0;

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select channels.price" +
                    "        from channels join client_channel" +
                    "        on client_channel.channel_id = channels.id" +
                    "        join clients" +
                    "        on clients.id = client_channel.client_id" +
                    "        where clients.id = " + id;
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                result += rs.getDouble("price");
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "An error occurred!", "Taxes",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    private void showSuppliers() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = " select suppliers.name as supplier , channels.name as channel, channels.price as channelPrice, suppliers.price as supplierPrice" +
                    " from suppliers join channel_supplier" +
                    " on suppliers.id = channel_supplier.supplier_id" +
                    " join channels" +
                    " on channels.id = channel_supplier.channel_id;";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append(String.format("Supplier: %s; Channel: %s; Channel's price: %.2f; Supplier's price: %.2f;", rs.getString("supplier"), rs.getString("channel"), rs.getDouble("channelPrice"), rs.getDouble("supplierPrice")));
                builder.append(System.lineSeparator());
            }
            String message = builder.toString();
            if (message.equals("")) {
                JOptionPane.showMessageDialog(this, "No suppliers info available!", "Suppliers info", JOptionPane.INFORMATION_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(this, message, "Suppliers info", JOptionPane.INFORMATION_MESSAGE);
        }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No suppliers to show!", "Try again",
                  JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();

        }
    }

    private void showChannels() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from channels";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append(String.format("Channel: %s, price: %.2f", rs.getString("name"), rs.getDouble("price")));
                builder.append(System.lineSeparator());
            }
            String message = builder.toString().trim();
            JOptionPane.showMessageDialog(this, message, "Channels", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No channels to show!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showCategories() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from categories";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                builder.append(String.format("Category: %s, price: %.2f", rs.getString("typeCategory"), rs.getDouble("price")));
                builder.append(System.lineSeparator());
            }
            String message = builder.toString().trim();
            JOptionPane.showMessageDialog(this, message, "Categories", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No categories to show!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMyContracts(int id) {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from contracts ";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                if (rs.getInt("client_id") == id) {
                    builder.append(String.format("Contract dateSigned: %s, deadline: %s", rs.getString("dateSigned"), rs.getString("deadline")));
                    builder.append(System.lineSeparator());
                }
            }
            String message = builder.toString().trim();
            JOptionPane.showMessageDialog(this, message, "Contracts", JOptionPane.INFORMATION_MESSAGE);
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "No contracts to show!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
