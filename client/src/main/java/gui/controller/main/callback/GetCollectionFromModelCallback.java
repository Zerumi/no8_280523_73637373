package gui.controller.main.callback;

import model.Route;

import java.util.HashMap;
import java.util.HashSet;

public interface GetCollectionFromModelCallback {
    HashSet<Route> getCollection();

    HashMap<Long, Long> getOwnership();
}
