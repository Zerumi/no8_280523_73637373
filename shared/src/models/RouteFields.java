package models;

import java.io.Serializable;

public enum RouteFields implements Serializable {
    ID(0, "id"),
    NAME(1, "name"),
    COORDINATES_X(2, "coordinates.x"),
    COORDINATES_Y(3, "coordinates.y"),
    CREATION_DATE(4, "creation_date"),
    FROM_X(5, "from.x"),
    FROM_Y(6, "from.y"),
    FROM_Z(7, "from.z"),
    FROM_NAME(8, "from.name"),
    TO_X(9, "to.x"),
    TO_Y(10, "to.y"),
    TO_Z(11, "to.z"),
    TO_NAME(12, "to.name"),
    DISTANCE(13, "distance");

    private final int index;
    private final String name;

    RouteFields(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
