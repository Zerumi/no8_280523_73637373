package gui.controller.filter;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public interface ModelConnector {
    TableRowSorter<TableModel> getModelSorter();
}
