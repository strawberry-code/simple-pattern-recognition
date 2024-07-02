package com.acme.hip.hop.chocobo.geometry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

/**
 * Represents a point in a geometric space. Provides functionality to manage connections
 * to other points based on their unique IDs.
 */
public class Point {

    private static final Logger logger = LogManager.getLogger(Point.class);

    private int id;
    private float x;
    private float y;

    /**
     * Default constructor for creating a point with uninitialized coordinates.
     * The point created is not connected to any other points initially.
     */
    public Point() {
        // Intentionally empty
    }

    /**
     * Constructs a point with specified x and y coordinates.
     * Automatically assigns a unique ID to the point from the Space singleton.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(float x, float y) {
        this(); // Initialize with the default constructor
        this.id = Space.getInstance().getPointId();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the unique ID of the point.
     * @return the ID of the point
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the x-coordinate of the point.
     * @return the x-coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the point.
     * @return the y-coordinate
     */
    public float getY() {
        return y;
    }
}