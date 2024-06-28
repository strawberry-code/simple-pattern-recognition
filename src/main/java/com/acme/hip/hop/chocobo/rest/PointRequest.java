package com.acme.hip.hop.chocobo.rest;

/**
 * Represents a request to create or modify a point in a geometric space,
 * encapsulating the x and y coordinates.
 */
public class PointRequest {
    private float x;
    private float y;

    /**
     * Gets the x-coordinate of the point.
     * @return the x-coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     * @param x the x-coordinate to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     * @return the y-coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     * @param y the y-coordinate to set
     */
    public void setY(float y) {
        this.y = y;
    }
}