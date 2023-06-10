package utils;

import models.RouteFields;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;

public class RouteFieldComparators {
    private static final LinkedHashMap<RouteFields, Comparator<?>> comparators;

    static {
        comparators = new LinkedHashMap<>();
        comparators.put(RouteFields.ID, Comparator.comparingLong((Long o) -> o));
        comparators.put(RouteFields.NAME, Comparator.naturalOrder());
        comparators.put(RouteFields.COORDINATES_X, Comparator.comparingDouble((Double o) -> o));
        comparators.put(RouteFields.COORDINATES_Y, Comparator.comparingDouble(Float::doubleValue));
        comparators.put(RouteFields.CREATION_DATE, (Comparator<Date>) Date::compareTo);
        comparators.put(RouteFields.TO_X, Comparator.comparingDouble(Float::doubleValue));
        comparators.put(RouteFields.TO_Y, Comparator.comparingLong((Long o) -> o));
        comparators.put(RouteFields.TO_Z, Comparator.comparingLong((Long o) -> o));
        comparators.put(RouteFields.TO_NAME, Comparator.naturalOrder());
        comparators.put(RouteFields.FROM_X, Comparator.comparingDouble(Float::doubleValue));
        comparators.put(RouteFields.FROM_Y, Comparator.comparingLong((Long o) -> o));
        comparators.put(RouteFields.FROM_Z, Comparator.comparingLong((Long o) -> o));
        comparators.put(RouteFields.FROM_NAME, Comparator.naturalOrder());
        comparators.put(RouteFields.DISTANCE, Comparator.comparingInt((Integer o) -> o));
    }


    public static Comparator<?> getByField(RouteFields field) {
        return comparators.get(field);
    }
}
