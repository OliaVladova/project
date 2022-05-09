package categoryForm;

import supplierForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryForm extends JDialog {
    private JPanel CategoryPanel;
    private JButton addCategoryButton;
    private JButton deleteCategoryButton;
    private JButton updatePriceButton;
    private JButton backButton;

    public CategoryForm(Frame parent) {
        super(parent);
        setTitle("Supplier");
        setContentPane(CategoryPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCategory addCategory = new AddCategory(null);
            }
        });
        deleteCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCategory deleteCategory = new DeleteCategory(null);
            }
        });

        updatePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePrice updatePrice = new UpdatePrice(null);
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
