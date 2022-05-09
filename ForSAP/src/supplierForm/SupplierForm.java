package supplierForm;

import forAdmin.AdminForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierForm extends JDialog{
    private JPanel SupplierPanel;
    private JButton AddSupplierButton;
    private JButton deleteSupplierButton;
    private JButton setPercentageButton;
    private JButton addChannelToSupplierButton;
    private JButton addCategoryToSupplierButton;
    private JButton backButton;

    public SupplierForm(Frame parent) {
        super(parent);
        setTitle("Supplier");
        setContentPane(SupplierPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        AddSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSupplier addSupplier = new AddSupplier(null);
            }
        });
        deleteSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteSupplier deleteSupplier = new DeleteSupplier(null);
            }
        });

        setPercentageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetPercentageForm setPercentageForm = new SetPercentageForm(null);
            }
        });


        addCategoryToSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCategoryToSupplier addCategoryToSupplier = new AddCategoryToSupplier(null);
            }
        });

        addChannelToSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddChannelToSupplier addChannelToSupplier = new AddChannelToSupplier(null);
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
