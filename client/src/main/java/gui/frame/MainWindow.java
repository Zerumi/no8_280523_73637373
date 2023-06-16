package gui.frame;

import authorization.AuthorizedUserData;
import core.provider.ExceptionProvider;
import exception.DenyOperationException;
import gui.controller.main.CommandButtonFactory;
import gui.controller.main.LocalizationActionListener;
import gui.controller.main.action.OpenVisualizationAction;
import gui.controller.main.callback.LocalizationCallback;
import gui.model.RouteTableModel;
import gui.view.render.RouteTableRender;
import model.RouteFields;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.RouteFieldComparators;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainWindow extends JFrame implements ExceptionProvider, LocalizationCallback {

    private final JLabel availableCommandsLabel = new JLabel();
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");
    private static boolean isExist = false;
    private final JLabel authAsLabel = new JLabel();
    private final AuthorizedUserData profile;
    private final JMenuBar menuBar;
    private final JMenu firstMenu;
    private final JMenu secondMenu;
    private final JTable table;
    private final RouteTableModel model;
    private final CommandButtonFactory factory;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("gui.l10n.main.MainWindow");

    public MainWindow(AuthorizedUserData profile) throws DenyOperationException {

        if (isExist) throw new DenyOperationException("Main window had already created.");
        isExist = true;

        this.profile = profile;

        // add buttonpanel
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(0, 1));
        JScrollPane eastScrollPane = new JScrollPane(eastPanel);
        availableCommandsLabel.setText(resourceBundle.getString("available_commands"));
        eastPanel.add(availableCommandsLabel);
        factory = new CommandButtonFactory(eastPanel, this, this);
        factory.fillAsync();

        MessageFormat mf = new MessageFormat(resourceBundle.getString("user_show"));
        authAsLabel.setText(mf.format(new Object[]{profile.login(), profile.name()}));

        JPanel northPanel = new JPanel();
        northPanel.add(authAsLabel);

        model = new RouteTableModel();
        table = new JTable(model);

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

        //Create the menu bar.

        menuBar = new JMenuBar();

        //Build the first menu.
        firstMenu = new JMenu(resourceBundle.getString("visualisation"));
        firstMenu.setMnemonic(KeyEvent.VK_A);
        firstMenu.getAccessibleContext().setAccessibleDescription(resourceBundle.getString("ct_visualisation"));
        firstMenu.add(new OpenVisualizationAction(model));
        menuBar.add(firstMenu);

        //Build second menu in the menu bar.
        secondMenu = new JMenu(resourceBundle.getString("manage"));
        secondMenu.setMnemonic(KeyEvent.VK_N);
        secondMenu.getAccessibleContext().setAccessibleDescription(resourceBundle.getString("ct_manage"));
        menuBar.add(secondMenu);

        // Подменю "Language" в меню "Manage"
        JMenu languageSubMenu = new JMenu("Language");
        secondMenu.add(languageSubMenu);

        // Группа RadioButton в подменю "Language"
        ButtonGroup languageGroup = new ButtonGroup();

        var radioButton1 = new JRadioButtonMenuItem("en_US");
        radioButton1.addActionListener(new LocalizationActionListener("en-US", this));
        languageGroup.add(radioButton1);
        languageSubMenu.add(radioButton1);

        var radioButton2 = new JRadioButtonMenuItem("ru_RU");
        radioButton2.addActionListener(new LocalizationActionListener("ru-RU", this));
        languageGroup.add(radioButton2);
        languageSubMenu.add(radioButton2);

        var radioButton3 = new JRadioButtonMenuItem("pt_BR");
        radioButton3.addActionListener(new LocalizationActionListener("pt-BR", this));
        languageGroup.add(radioButton3);
        languageSubMenu.add(radioButton3);

        var radioButton4 = new JRadioButtonMenuItem("hu_HU");
        radioButton4.addActionListener(new LocalizationActionListener("hu-HU", this));
        languageGroup.add(radioButton4);
        languageSubMenu.add(radioButton4);

        var radioButton5 = new JRadioButtonMenuItem("en_IN");
        radioButton5.addActionListener(new LocalizationActionListener("en-IN", this));
        languageGroup.add(radioButton5);
        languageSubMenu.add(radioButton5);

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
    public void changeLocale(Locale locale) {
        logger.info("change locale...");
        ResourceBundle.clearCache();
        resourceBundle = ResourceBundle.getBundle("gui.l10n.main.MainWindow");

        availableCommandsLabel.setText(resourceBundle.getString("available_commands"));

        MessageFormat mf = new MessageFormat(resourceBundle.getString("user_show"));
        authAsLabel.setText(mf.format(new Object[]{profile.login(), profile.name()}));

        firstMenu.setText(resourceBundle.getString("visualisation"));
        firstMenu.getAccessibleContext().setAccessibleDescription(resourceBundle.getString("ct_visualisation"));

        secondMenu.setText(resourceBundle.getString("manage"));
        secondMenu.getAccessibleContext().setAccessibleDescription(resourceBundle.getString("ct_manage"));

        factory.changeLocale();
        model.changeLocale();

        // re-setup sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        for (var field : RouteFields.values()) {
            sorter.setComparator(field.getIndex(), RouteFieldComparators.getByField(field));
        }
        table.setRowSorter(sorter);
    }


    @Override
    public void callRepaint() {
        this.revalidate();
        this.repaint();
    }
}
