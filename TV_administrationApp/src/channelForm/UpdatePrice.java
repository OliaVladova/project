package channelForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdatePrice extends JDialog {
    private JPanel UpdatePricePanel;
    private JTextField channelNameField;
    private JTextField newPriceField;
    private JButton backButton;
    private JButton OKButton;

    public UpdatePrice(Frame parent) {
        super(parent);
        setTitle("Update Channel Price");
        setContentPane(UpdatePricePanel);
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
                setNewPrice();
            }
        });
        setVisible(true);
    }

    private void setNewPrice() {
        String name = channelNameField.getText();
        String price = newPriceField.getText();

        if (price.equals("") ||price.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            double newPrice = Double.parseDouble(price);

            if (isExisting(name)) {
                updatePrice(name, newPrice);
            }else {
                JOptionPane.showMessageDialog(this, "Please, enter existing channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updatePrice(String name, double newPrice) {
        String price = "" + newPrice;
        boolean isRight = true;
        if (newPrice <= 0) {
            JOptionPane.showMessageDialog(this, "Please, enter valid price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "update channels set channels.price = ? where channels.name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, price);
            preparedStatement.setString(2, name);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 && isRight) {
                JOptionPane.showMessageDialog(this, "Successfully updated price", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {

                JOptionPane.showMessageDialog(this, "Fail to updated price!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to updated price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean isExisting(String channelName){
        boolean isRight = true;
        if (channelName==null||channelName.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please, enter channel name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            String query = "Select * from channels";
            Statement statement = connect.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next() && isRight) {
                String nameOfChannel = rs.getString("name");
                if (nameOfChannel.equals(channelName)){
                    return true;
                }

            }

            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to update price!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
