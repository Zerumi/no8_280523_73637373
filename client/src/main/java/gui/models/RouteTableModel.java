package gui.models;

import exceptions.UpdateModelException;
import gui.models.listeners.RouteTableModelChangeListener;
import gui.models.listeners.RouteTableModelUpdateFullCollectionListener;
import listenLogic.ServerListener;
import models.Route;
import models.RouteFields;
import models.collection.actions.AddCollectionAction;
import models.collection.actions.RemoveCollectionAction;
import models.collection.actions.UpdateCollectionAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import requestLogic.requestSenders.RequestSender;
import requestLogic.requestSenders.ShowCollectionRequestSender;
import requests.ListenCollectionActionsRequest;
import requests.UpdateSingleFieldRequest;
import responses.CollectionUpdatedResponse;
import responses.ShowCollectionResponse;
import serverLogic.ServerConnectionHandler;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RouteTableModel extends AbstractTableModel {
    private static final Logger logger = LogManager.getLogger("com.github.zerumi.lab8");

    private final ArrayList<ArrayList<Object>> model = new ArrayList<>();

    private final String[] columnNames = Arrays.stream(RouteFields.values())
            .map(RouteFields::getName)
            .toList()
            .toArray(new String[0]);

    public RouteTableModel() {
        new ShowCollectionRequestSender().sendCollectionRequest(
                new RouteTableModelUpdateFullCollectionListener(this));
        try {
            new RequestSender().sendRequest(
                    new ListenCollectionActionsRequest(),
                    ServerConnectionHandler.getCurrentConnection());
        } catch (IOException e) {
            acceptException(e);
        }
        new ServerListener<>(CollectionUpdatedResponse.class).addListeners(
                new RouteTableModelChangeListener(this)
        ).startListen();
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

    public void acceptFullCollectionResponse(ShowCollectionResponse response) {
        HashSet<Route> collection = response.getCollection();

        for (var element : collection) {
            addNewLine(element);
        }

        fireTableRowsUpdated(0, Integer.MAX_VALUE);
    }

    public void acceptCollectionUpdate(CollectionUpdatedResponse response) {
        switch (response.getAction().getAction()) {
            case ADD -> {
                AddCollectionAction action = ((AddCollectionAction) response.getAction());
                for (var element : action.getNewElements()) {
                    addNewLine(element);
                }

                fireTableRowsUpdated(0, Integer.MAX_VALUE);
            }
            case UPDATE -> {
                try {
                    UpdateCollectionAction action = ((UpdateCollectionAction) response.getAction());
                    updateCellInModel(action.getElementId(), action.getUpdatedFiled(), action.getUpdatedValue());
                } catch (UpdateModelException e) {
                    acceptException(e);
                }
            }
            case REMOVE -> {
                RemoveCollectionAction action = ((RemoveCollectionAction) response.getAction());
                for (long id : action.getRemoved_ids()) {
                    removeLineByRouteId(id);
                }

                fireTableRowsUpdated(0, Integer.MAX_VALUE);
            }
            default -> logger.warn("Something strange here...");
        }
    }

    private void removeLineByRouteId(long id) {
        model.removeIf(x -> x.get(0).equals(id));
    }

    private void updateCellInModel(long elementId, RouteFields updatedFiled, Object updatedValue) throws UpdateModelException {
        int row = model.indexOf(
                model.stream()
                        .filter(x -> x.get(0).equals(elementId))
                        .findFirst()
                        .orElseThrow(() ->
                                new UpdateModelException("Can't find object in model")));

        int col = updatedFiled.getIndex();
        model.get(row).set(updatedFiled.getIndex(), updatedValue);

        fireTableCellUpdated(row, col);
    }

    public void acceptException(Exception e) {
        logger.error("exception", e);
    }

    private void addNewLine(Route element) {
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
        } else {
            line.add(null);
            line.add(null);
            line.add(null);
            line.add(null);
        }
        line.add(element.getDistance());
        model.add(line);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        UpdateSingleFieldRequest request = new UpdateSingleFieldRequest(
                (Long) (model.get(rowIndex).get(RouteFields.ID.getIndex())),
                RouteFields.byId(columnIndex),
                aValue
        );
        try {
            new RequestSender().sendRequest(request, ServerConnectionHandler.getCurrentConnection());
        } catch (IOException e) {
            logger.error("ex ", e);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0 && columnIndex != 4;
    }
}
