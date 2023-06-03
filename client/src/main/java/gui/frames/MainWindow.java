package gui.frames;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        JLabel label = new JLabel("Lorem ipsum doro sit amet");

        JPanel northPanel = new JPanel();
        northPanel.add(label);

        this.add(northPanel, BorderLayout.NORTH);
        this.pack();
    }
}
