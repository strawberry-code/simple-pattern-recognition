package com.acme.simple_pattern_recognition;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
public class Point {

    @Id
    private String id;

    @Relationship(type = "CONNECTS")
    private List<Point> points;

    private float x;
    private float y;

    public Point() {
        this.points = new ArrayList<>();
    }

    // Constructor, getters and setters
    public Point(float x, float y) {
        this.points = new ArrayList<Point>();
        this.id = generateHash(x + "," + y);
        this.x = x;
        this.y = y;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void connectTo(Point point) {
        this.points.add(point);
    }

    public String getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private String generateHash(String message) {
        return DigestUtils.sha256Hex(message);
    }
}