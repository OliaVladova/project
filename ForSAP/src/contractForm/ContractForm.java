package contractForm;

import supplierForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractForm extends JDialog {
    private JPanel ContractPanel;
    private JButton createContractButton;
    private JButton deleteContractButton;
    private JButton addCategoriesButton;
    private JButton addChannelsButton;
    private JButton backButton;

    public ContractForm(Frame parent) {
        super(parent);
        setTitle("Contract");
        setContentPane(ContractPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        createContractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateContract createContract = new CreateContract(null);
            }
        });
        deleteContractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteContract deleteContract = new DeleteContract(null);
            }
        });

        addCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCategoryToContract addCategoryToContract = new AddCategoryToContract(null);
            }
        });


        addChannelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddChannelToContract addChannelToContract = new AddChannelToContract(null);
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
