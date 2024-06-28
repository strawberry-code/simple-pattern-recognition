package com.acme.simple_pattern_recognition;

public class Line {
    private Point from;
    private Point to;

    public Line(Point from, Point to) {
        if(from.getId() > to.getId()) {
            this.from = to;
            this.to = from;
        } else {
            this.from = from;
            this.to = to;
        }
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }
}