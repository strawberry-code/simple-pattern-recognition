package com.acme.hip.hop.chocobo.geometry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Manages a geometric plane with a unique instance. Supports operations to add points,
 * retrieve points, and generate combinations of points.
 */
public class Plane {

    private static final Plane INSTANCE = new Plane();
    private static final HashSet<Point> points = new HashSet<>();
    private int lastPointId = 0;

    private Plane() {
        // Private constructor to prevent external instantiation.
    }

    /**
     * Returns the single instance of this class.
     */
    public static Plane getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new point to the plane and connects it bidirectionally with all existing points.
     * @param newPoint the new point to add
     */
    public void addPoint(Point newPoint) {
        points.add(newPoint);
        for (Point point : points) {
            if (point.getId() != newPoint.getId()) {
                point.connectTo(newPoint);
                newPoint.connectTo(point); // Ensure bidirectional connection
            }
        }
    }

    /**
     * Retrieves a list of all points on the plane.
     * @return a list of all points
     */
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    /**
     * Returns the point with the specified ID, if it exists.
     * @param id the ID of the point to retrieve
     * @return the point with the specified ID, or null if not found
     */
    public Point getPoint(int id) {
        for (Point point : points) {
            if (point.getId() == id) {
                return point;
            }
        }
        return null;
    }

    /**
     * Generates all possible combinations of points of a given size.
     * @param n the number of points in each combination
     * @return a list of point combinations
     */
    public List<List<Point>> getSegments(int n) {
        List<List<Point>> result = new ArrayList<>();
        ArrayList<Point> data = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            data.add(null);
        }
        ArrayList<Point> elements = new ArrayList<>(points);
        combinations(elements, data, 0, elements.size() - 1, 0, n, result);
        return result;
    }

    private static void combinations(ArrayList<Point> arr, ArrayList<Point> data, int start, int end, int index, int r, List<List<Point>> result) {
        if (index == r) {
            List<Point> combination = new ArrayList<>(data.subList(0, r));
            result.add(combination);
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data.set(index, arr.get(i));
            combinations(arr, data, i + 1, end, index + 1, r, result);
        }
    }

    /**
     * Provides a unique ID for a new point and increments the internal counter.
     * @return a unique point ID
     */
    public int getPointId() {
        return this.lastPointId++;
    }

    /**
     * Clears all points from the plane and resets the ID counter.
     */
    public void clearAll() {
        points.clear();
        lastPointId = 0;
    }
}