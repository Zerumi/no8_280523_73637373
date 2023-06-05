package gui.frames;

import authorization.AuthorizedUserData;
import gui.models.RouteTableModel;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(AuthorizedUserData profile) {
        JLabel label = new JLabel("Lorem ipsum dolor sit amet");

        JPanel northPanel = new JPanel();
        northPanel.add(label);

        JTable table = new JTable(new RouteTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
    }
}
