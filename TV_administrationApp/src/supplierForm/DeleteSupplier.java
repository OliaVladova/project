package supplierForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteSupplier extends JDialog {
    private JPanel DeleteSupplierPannel;
    private JTextField nameField;
    private JButton backButton;
    private JButton OKButton;

    public DeleteSupplier(Frame parent) {
        super(parent);
        setTitle("Delete Supplier");
        setContentPane(DeleteSupplierPannel);
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
                deleteSupplier();
            }
        });
        setVisible(true);
    }

    private void deleteSupplier() {
        String name = nameField.getText();

        if (name==null||name.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please, enter valid name!", "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }else {
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_administration", "root", "OliaVladova2303");
                Statement statement = connect.createStatement();
                String query = "delete from suppliers where suppliers.name = ?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, name);


                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0 ) {
                    JOptionPane.showMessageDialog(this, "Successfully deleted supplier", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Fail to delete supplier!", "Try again",
                            JOptionPane.ERROR_MESSAGE);

                }
                statement.close();
                connect.close();
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(this, "Please, enter valid supplier name!", "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
