package nico.cardstorm.forms;

import nico.cardstorm.fx.BlocklyView;

import javax.swing.*;

public class MainForm extends JFrame {
    private JPanel contentPanel;
    private BlocklyView blocklyView;

    public MainForm() {
        super("Card Storm");
        this.blocklyView = new BlocklyView();
        this.setContentPane(contentPanel);

        this.setSize(512, 512);
        this.setLocationRelativeTo(null);

        this.contentPanel.add(blocklyView);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
