package UserForm;

import forAdmin.AdminForm;
import supplierForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JDialog {
    private JPanel UserAdministrationPanel;
    private JButton addAdminButton;
    private JButton deleteUserButton;
    private JButton backButton;

    public UserForm(Frame parent) {
        super(parent);
        setTitle("User");
        setContentPane(UserAdministrationPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        addAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAdmin addAdmin = new AddAdmin(null);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUser deleteUser = new DeleteUser(null);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
        setVisible(true);
    }
}
