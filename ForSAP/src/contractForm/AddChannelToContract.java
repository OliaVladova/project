package contractForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddChannelToContract extends JDialog {
    private JPanel AddChannelToContractPanel;
    private JTextField contractIdField;
    private JTextField channelNameField;
    private JButton backButton;
    private JButton OKButton;

    public AddChannelToContract(Frame parent) {
        super(parent);
        setTitle("Add Channel To Contract");
        setContentPane(AddChannelToContractPanel);
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
                addChannelToContract();
            }
        });
        setVisible(true);
    }

    private void addChannelToContract() {
        String nameOfChannel = channelNameField.getText();
        String contractId = contractIdField.getText();
        int channel_id = getChannelId(nameOfChannel);
        int contract_id = getContractId(contractId);
        if (channel_id==0){
            JOptionPane.showMessageDialog(this, "Unknown channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }else if (contract_id==0){
            JOptionPane.showMessageDialog(this, "Unknown contract!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                Statement statement = connect.createStatement();
                String query = "INSERT INTO contract_channel(contract_id, channel_id)" + "values(?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, contractId);
                preparedStatement.setString(2, String.valueOf(channel_id));

                int addedRows = preparedStatement.executeUpdate();
                if (addedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully added channel", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Fail to add new channel!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
                statement.close();
                connect.close();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, "Fail to add new channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private int getChannelId(String nameOfChannel) {
        int channel_id = 0;
        if (nameOfChannel == null || nameOfChannel.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter channel!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
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
                JOptionPane.showMessageDialog(this, "Fail to add new channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return channel_id;
    }

    private int getContractId(String contractId) {
        int contract_id = 0;
        if (contractId == null || contractId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter contract id!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int contract = Integer.parseInt(contractId);
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from contracts";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    int idOfContract = rs.getInt("id");
                    if (contract == idOfContract) {
                        contract_id = idOfContract;

                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Fail to add new channel!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return contract_id;
    }


}
