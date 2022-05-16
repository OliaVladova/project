package userLogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BuyChannel extends JDialog {
    private JPanel BuyChannel;
    private JTextField ChannelNameField;
    private JButton backButton;
    private JButton OKButton;

    public BuyChannel(Frame parent, int id) {
        super(parent);
        setTitle("Buy Channel");
        setContentPane(BuyChannel);
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
                addChannel(id);
            }
        });
        setVisible(true);

    }

    private void addChannel(int id) {
        String nameOfChannel = ChannelNameField.getText();
        int channel_id = getChannelId(nameOfChannel);
        if (channel_id == 0) {
            JOptionPane.showMessageDialog(this, "Please, enter existing channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                Statement statement = connect.createStatement();
                String query = "INSERT INTO client_channel(client_id, channel_id)" + "values(?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, String.valueOf(id));
                preparedStatement.setString(2, String.valueOf(channel_id));

                int addedRows = preparedStatement.executeUpdate();
                if (addedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully bought channel", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Fail to buy channel!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
                statement.close();
                connect.close();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, "Fail to buy channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private int getChannelId(String nameOfChannel) {
        int channel_id = 0;
        if (nameOfChannel==null||nameOfChannel.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please, enter channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from channels";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String nameCategory = rs.getString("name");

                    if (nameOfChannel.equals(nameCategory)) {
                        channel_id = rs.getInt("id");
                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Unknown channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
        return channel_id;
    }
}
