package contractForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteContract extends JDialog{
    private JTextField contractIdField;
    private JButton backButton;
    private JButton OKButton;
    private JPanel DeleteContractPanel;

    public DeleteContract(Frame parent) {
        super(parent);
        setTitle("Delete Contract");
        setContentPane(DeleteContractPanel);
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
                deleteContract();

            }
        });
        setVisible(true);
    }

    private void deleteContract() {
        String id = contractIdField.getText();
        boolean isRight = true;
        if (id==null||id.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter id!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
            isRight = false;

        }else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                Statement statement = connect.createStatement();
                String query = "delete from contracts where contracts.id = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, id);


                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully deleted contract", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Unknown id!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
                statement.close();
                connect.close();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, "Fail to delete contract!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
