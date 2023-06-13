package gui.frame;

import authorization.AuthorizedUserData;
import core.providers.ExceptionProvider;
import exceptions.DenyOperationException;
import gui.controller.main.CommandButtonFactory;
import gui.controller.main.action.OpenVisualizationAction;
import gui.controller.main.callback.RepaintCallback;
import gui.models.RouteTableModel;
import gui.view.render.RouteTableRender;
import models.RouteFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.RouteFieldComparators;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements ExceptionProvider, RepaintCallback {

    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private static boolean isExist = false;

    public MainWindow(AuthorizedUserData profile) throws DenyOperationException {

        if (isExist) throw new DenyOperationException("Main window had already created.");

        isExist = true;

        // add buttonpanel
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(0, 1));
        JScrollPane eastScrollPane = new JScrollPane(eastPanel);
        eastPanel.add(new JLabel("Available commands:"));
        new CommandButtonFactory(eastPanel, this, this).fillAsync();

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Visualisation");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Show visualisation");
        menu.add(new OpenVisualizationAction());
        menuBar.add(menu);

        //Build second menu in the menu bar.
        menu = new JMenu("Manage");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "Manage application");
        menuBar.add(menu);

        JLabel label = new JLabel("Authorized as " + profile.login() + ". Welcome back, " + profile.name());

        JPanel northPanel = new JPanel();
        northPanel.add(label);

        JTable table = new JTable(new RouteTableModel());

        table.setDefaultRenderer(Object.class, new RouteTableRender());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        // setup sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        for (var field : RouteFields.values()) {
            sorter.setComparator(field.getIndex(), RouteFieldComparators.getByField(field));
        }
        table.setRowSorter(sorter);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(eastScrollPane, BorderLayout.EAST);
        this.setJMenuBar(menuBar);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocationByPlatform(true);
        this.setSize(11 * screenWidth / 18, 11 * screenHeight / 18);
    }

    @Override
    public void acceptException(Exception e) {
        logger.error("ex", e);
    }

    @Override
    public void callRepaint() {
        this.revalidate();
        this.repaint();
    }
}
