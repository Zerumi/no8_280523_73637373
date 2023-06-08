package gui.frames;

import authorization.AuthorizedUserData;
import gui.models.RouteTableModel;
import models.RouteFields;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    public MainWindow(AuthorizedUserData profile) {
        JLabel label = new JLabel("Lorem ipsum dolor sit amet");

        JPanel northPanel = new JPanel();
        northPanel.add(label);

        JTable table = new JTable(new RouteTableModel());

        // setup sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        for (RouteFields field : RouteFields.values()) {
            sortKeys.add(new RowSorter.SortKey(field.getIndex(), SortOrder.UNSORTED));
            sortKeys.add(new RowSorter.SortKey(field.getIndex(), SortOrder.ASCENDING));
            sortKeys.add(new RowSorter.SortKey(field.getIndex(), SortOrder.DESCENDING));
        }
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
    }
}
