package channelForm;

import supplierForm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChannelForm extends JDialog {
    private JButton addChannelButton;
    private JButton deleteChannelButton;
    private JPanel ChannelPanel;
    private JButton updatePriceButton;
    private JButton backButton;

    public ChannelForm(Frame parent) {
        super(parent);
        setTitle("Channel");
        setContentPane(ChannelPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        addChannelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddChannel addChannel = new AddChannel(null);
            }
        });
        deleteChannelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteChannel deleteChannel = new DeleteChannel(null);
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
