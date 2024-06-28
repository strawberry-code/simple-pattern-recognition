package com.acme.simple_pattern_recognition;

import java.util.HashSet;

public class Point {

    int id;

    // private HashSet<Point> connectedPoints;
    private HashSet<Integer> connectedPoints;

    private float x;
    private float y;

    public Point() {
        this.connectedPoints = new HashSet<Integer>();
    }

    // Constructor, getters and setters
    public Point(float x, float y) {
        this.connectedPoints = new HashSet<Integer>();
        this.id = Plane.getInstance().getPointId();
        this.x = x;
        this.y = y;
    }

    public HashSet<Integer> getConnectedPoints() {
        return connectedPoints;
    }

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

}