package gui.models;

import models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.ShowCollectionRequestSender;
import responseLogic.ApplicationResponseProvider;
import responses.ShowCollectionResponse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashSet;

public class RouteTableModel extends AbstractTableModel implements ApplicationResponseProvider<ShowCollectionResponse> {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");

    private final ArrayList<ArrayList<Object>> model = new ArrayList<>();

    private final String[] columnNames = {
            "id",
            "name",
            "coordinates.x",
            "coordinates.y",
            "creation_date",
            "from.x",
            "from.y",
            "from.z",
            "from.name",
            "to.x",
            "to.y",
            "to.z",
            "to.name",
            "distance"
    };

    public RouteTableModel() {
        new ShowCollectionRequestSender().sendCollectionRequest(this);
    }


    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return model.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return model.get(rowIndex).get(columnIndex);
    }

    @Override
    public void acceptResponse(ShowCollectionResponse response) {
        HashSet<Route> collection = response.getCollection();

        for (var element : collection) {
            ArrayList<Object> line = new ArrayList<>(getColumnCount());
            line.add(element.getId());
            line.add(element.getName());
            line.add(element.getCoordinates().getX());
            line.add(element.getCoordinates().getY());
            line.add(element.getCreationDate());
            // nullable zone
            if (element.getFrom() != null) {
                line.add(element.getFrom().getX());
                line.add(element.getFrom().getY());
                line.add(element.getFrom().getZ());
                line.add(element.getFrom().getName());
            } else {
                line.add(null);
                line.add(null);
                line.add(null);
                line.add(null);
            }
            if (element.getTo() != null) {
                line.add(element.getTo().getX());
                line.add(element.getTo().getY());
                line.add(element.getTo().getZ());
                line.add(element.getTo().getName());
                line.add(element.getDistance());
            } else {
                line.add(null);
                line.add(null);
                line.add(null);
                line.add(null);
            }
            model.add(line);
        }

        fireTableRowsUpdated(0, getRowCount());
    }

    @Override
    public void acceptException(Exception e) {
        logger.error(e);
    }
}
