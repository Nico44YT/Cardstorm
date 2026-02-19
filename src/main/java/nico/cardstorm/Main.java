package nico.cardstorm;

import nico.cardstorm.fx.BlocklyView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cardstorm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);

            BlocklyView blocklyView = new BlocklyView();
            frame.add(blocklyView);

            // Listen for JSON updates
            blocklyView.setOnJsonReceived(json -> {
                System.out.println("Blockly JSON received:\n" + json);
            });

            frame.setVisible(true);
        });
    }
}
