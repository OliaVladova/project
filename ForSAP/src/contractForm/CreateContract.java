package contractForm;

import contractForm.Contracts.BaseContract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateContract extends JDialog {
    private JPanel CreateContractPanel;
    private JTextField userField;
    private JTextField supplierField;
    private JTextField dateField;
    private JTextField deadlineField;
    private JButton backButton;
    private JButton OKButton;
    private BaseContract contract;

    public CreateContract(Frame parent) {
        super(parent);
        setTitle("Create Contract");
        setContentPane(CreateContractPanel);
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

                createContract();
            }
        });
        setVisible(true);
    }

    private void createContract() {
        String user = userField.getText();
        String supplier = supplierField.getText();
        String date = dateField.getText();
        String deadline = deadlineField.getText();
        int supplier_id = retrieveSupplierId(supplier);
        int user_id = retrieveUserId(user);
        if (date == null || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter date!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (deadline == null || deadline.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter deadline!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (supplier_id != 0 && user_id != 0) {
                    contract = addContractToDB(date, deadline, supplier_id, user_id);
                    if (contract != null) {
                        JOptionPane.showMessageDialog(this, "Successfully sign contract", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Fail to sign contract!", "Try again",
                                JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        }


    }

    private BaseContract addContractToDB(String date, String deadline, int supplier_id, int user_id) {
        BaseContract contractNew = null;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
            Statement statement = connect.createStatement();
            String query = "INSERT INTO contracts(supplier_id, client_id,dateSigned, deadline)" + "values(?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(supplier_id));
            preparedStatement.setString(2, String.valueOf(user_id));
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, deadline);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                contractNew = new BaseContract(user_id, supplier_id, date, deadline);
            }
            statement.close();
            connect.close();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(this, "Fail to sign contract!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
        return contractNew;
    }

    private int retrieveUserId(String user) {
        int user_id = 0;
        if (user == null || user.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter user!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from clients";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String userName = rs.getString("name");

                    if (user.equals(userName)) {
                        user_id = rs.getInt("id");
                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Please, enter existing user!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return user_id;
    }

    private int retrieveSupplierId(String name) {
        int supplier_id = 0;
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please, enter supplier!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from suppliers";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String nameOfSupplier = rs.getString("name");

                    if (name.equals(nameOfSupplier)) {
                        supplier_id = rs.getInt("id");
                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Please, enter existing supplier!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return supplier_id;
    }
}
