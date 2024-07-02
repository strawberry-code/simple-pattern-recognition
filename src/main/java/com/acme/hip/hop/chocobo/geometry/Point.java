package com.acme.hip.hop.chocobo.geometry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

/**
 * Represents a point in a geometric space, with methods to manage connections
 * to other points based on their IDs.
 */
public class Point {

    private static final Logger logger = LogManager.getLogger(Point.class);

    private int id;
    private HashSet<Integer> connectedPoints;
    private boolean visited;
    private float x;
    private float y;

    /**
     * Default constructor that initializes the connections set.
     */
    public Point() {
        this.connectedPoints = new HashSet<>();
    }

    /**
     * Constructs a point with specified coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(float x, float y) {
        this();
        this.id = Space.getInstance().getPointId();
        this.x = x;
        this.y = y;
    }

    public HashSet<Integer> getConnectedPoints() {
        return connectedPoints;
    }

    /**
     * Connects this point to another point.
     *
     * @param point the point to connect to
     */
    public void connectTo(Point point) {
        this.connectedPoints.add(point.id);
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setUnvisited() {
        this.visited = false;
    }
}