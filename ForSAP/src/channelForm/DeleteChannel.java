package channelForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteChannel extends JDialog {
    private JPanel DeleteChannelPanel;
    private JTextField channelNameField;
    private JButton backButton;
    private JButton OKButton;

    public DeleteChannel(Frame parent) {
        super(parent);
        setTitle("Delete Channel");
        setContentPane(DeleteChannelPanel);
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
                deleteChannel();
            }
        });
        setVisible(true);
    }

    private void deleteChannel() {
        String name = channelNameField.getText();

        if (name == null || name.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Enter channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "delete from channels where channels.name = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0 ) {
                JOptionPane.showMessageDialog(this, "Successfully deleted channel", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {

                JOptionPane.showMessageDialog(this, "Unknown channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);

            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to delete channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
