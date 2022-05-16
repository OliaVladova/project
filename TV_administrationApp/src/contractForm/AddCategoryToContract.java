package contractForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCategoryToContract extends JDialog{
    private JPanel AddCategoryToContract;
    private JTextField contractIdField;
    private JTextField CategoryNameField;
    private JButton backButton;
    private JButton OKButton;

    public AddCategoryToContract(Frame parent) {
        super(parent);
        setTitle("Add Category To Contract");
        setContentPane(AddCategoryToContract);
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
                addCategoryToContract();
            }
        });
        setVisible(true);
    }

    private void addCategoryToContract() {
        String nameOfCategory = CategoryNameField.getText();
        String contractId = contractIdField.getText();
        int category_id = getCategoryId(nameOfCategory);
        int contract_id= getContractId(contractId);
        if (category_id==0){
            JOptionPane.showMessageDialog(this, "Unknown category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);

        }else if (contract_id==0){
            JOptionPane.showMessageDialog(this, "Unknown contract!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else
        {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                Statement statement = connect.createStatement();
                String query = "INSERT INTO contract_category(contract_id, category_id)" + "values(?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, contractId);
                preparedStatement.setString(2, String.valueOf(category_id));

                int addedRows = preparedStatement.executeUpdate();
                if (addedRows > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully added category", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(this, "Fail to add new category!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
                statement.close();
                connect.close();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, "Fail to add new category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private int getCategoryId(String nameOfCategory) {
        int category_id = 0;
        if (nameOfCategory==null||nameOfCategory.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter category!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                String query = "select * from categories";
                Statement statement = connect.prepareStatement(query);
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String nameCategory = rs.getString("typeCategory");

                    if (nameOfCategory.equals(nameCategory)) {
                        category_id = rs.getInt("id");
                    }
                }
                rs.close();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Fail to add new category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return category_id;
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
                JOptionPane.showMessageDialog(this, "Fail to add new category!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return contract_id;
    }
}
