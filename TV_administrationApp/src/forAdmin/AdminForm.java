package forAdmin;

import UserForm.UserForm;
import categoryForm.CategoryForm;
import channelForm.ChannelForm;
import contractForm.ContractForm;
import supplierForm.SupplierForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminForm extends JDialog {
    private JTextField operationTF;
    private JLabel Administration;
    private JButton SupplierButton;
    private JPanel AdminPanel;
    private JButton UserButton;
    private JButton channelButton;
    private JButton categoryButton;
    private JButton contractButton;

    public AdminForm(Frame parent) {
        super(parent);
        setTitle("Administration");
        setContentPane(AdminPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        SupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierForm supplierForm = new SupplierForm(null);
            }
        });


        UserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserForm userForm = new UserForm(null);
            }
        });

        channelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChannelForm channelForm = new ChannelForm(null);
            }
        });

        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryForm categoryForm = new CategoryForm(null);
            }
        });

        contractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContractForm contractForm = new ContractForm(null);
            }
        });
        setVisible(true);
    }


}
