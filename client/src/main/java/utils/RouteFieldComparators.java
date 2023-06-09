package utils;

import models.RouteFields;

import java.util.ArrayList;
import java.util.Comparator;

public class RouteFieldComparators {
    private static final ArrayList<Comparator<?>> comparators;

    static {
        comparators = new ArrayList<>() {{
            add((Comparator<Long>) (o1, o2) -> {
                if (o1 - o2 > 0) return 1;
                else if (o1.equals(o2)) return 0;
                else return -1;
            });
        }};
    }


    public static Comparator<?> getByField(RouteFields field) {
        return comparators.get(field.getIndex());
    }
}
