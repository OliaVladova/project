package channelForm;

import channelForm.channels.Channel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddChannel extends JDialog {
    private JPanel AddChannelPanel;
    private JTextField channelNameField;
    private JTextField priceField;
    private JTextField categoryNameField;
    private JButton backButton;
    private JButton OKButton;

    public AddChannel(Frame parent) {
        super(parent);
        setTitle("Add Channel");
        setContentPane(AddChannelPanel);
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
        String name = channelNameField.getText();
        String price = priceField.getText();
        String categoryName = categoryNameField.getText();
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter channel name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            if (price==null||price.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please, enter price!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                Channel channel = addChannelToDB(name, price, categoryName);
                if (channel != null) {
                    JOptionPane.showMessageDialog(this, "Successfully added channel", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        }
    }

    private Channel addChannelToDB(String name, String price, String categoryName) {
        Channel channel = null;
        int category_id = getCategoryId(categoryName);
        if (category_id == 0) {
            JOptionPane.showMessageDialog(this, "Please, enter existing category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }else {
            double newPrice = Double.parseDouble(price);
            if (newPrice <= 0) {
                JOptionPane.showMessageDialog(this, "Please, enter valid price!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                try {
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                    Statement statement = connect.createStatement();
                    String query = "INSERT INTO channels(name, price, category_id)" + "values(?,?,?)";
                    PreparedStatement preparedStatement = connect.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, price);
                    preparedStatement.setString(3, String.valueOf(category_id));

                    int addedRows = preparedStatement.executeUpdate();
                    if (addedRows > 0 ) {
                        channel = new Channel(name, Double.parseDouble(price));
                    }
                    statement.close();
                    connect.close();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(this, "Fail to register new channel!", "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return channel;
    }

    private int getCategoryId(String categoryName) {
        int category_id = 0;
        boolean isRight = true;
        if (categoryName == null || categoryName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter valid category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;

        }
        try {

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "select * from categories";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next() && isRight) {
                String category = rs.getString("typeCategory");

                if (category.equals(categoryName)) {
                    category_id = rs.getInt("id");
                }
            }
            rs.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Fail to get category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

        return category_id;
    }
}
