package gui.frames;

import authorization.AuthorizedUserData;
import gui.models.RouteTableModel;
import models.RouteFields;
import utils.RouteFieldComparators;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    public MainWindow(AuthorizedUserData profile) {
        JLabel label = new JLabel("Authorized as " + profile.login() + ". Welcome back, " + profile.name());

        JPanel northPanel = new JPanel();
        northPanel.add(label);

        JTable table = new JTable(new RouteTableModel());

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        // setup sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        /*for (var field : RouteFields.values()) {
            sorter.setComparator(field.getIndex(), RouteFieldComparators.getByField(field));
        }*/ // todo: all comparators
        sorter.setComparator(0, RouteFieldComparators.getByField(RouteFields.ID));
        table.setRowSorter(sorter);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
    }
}
