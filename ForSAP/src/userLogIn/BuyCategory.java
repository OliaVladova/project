package userLogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BuyCategory extends JDialog {
    private JPanel BuyCategoryPanel;
    private JTextField CategoryNameField;
    private JButton backButton;
    private JButton OKButton;

    public BuyCategory(Frame parent, int id) {
        super(parent);
        setTitle("BuyCategory");
        setContentPane(BuyCategoryPanel);
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
                addCategory(id);
            }
        });
        setVisible(true);

    }

    private void addCategory(int id) {
        String nameOfCategory = CategoryNameField.getText();
        int category_id = getCategoryId(nameOfCategory);
        if (category_id==0){
            JOptionPane.showMessageDialog(this, "Please, enter existing category", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO client_category(client_id, category_id)" + "values(?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, String.valueOf(category_id));

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                JOptionPane.showMessageDialog(this, "Successfully bought category", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {

                JOptionPane.showMessageDialog(this, "Fail to buy category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to buy category", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getCategoryId(String nameOfChannel) {
        int channel_id = 0;
        if (nameOfChannel == null || nameOfChannel.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from categories";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String nameCategory = rs.getString("typeCategory");

                    if (nameOfChannel.equals(nameCategory)) {
                        channel_id = rs.getInt("id");
                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Fail to buy category", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return channel_id;
    }
}
