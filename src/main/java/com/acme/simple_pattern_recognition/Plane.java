package com.acme.simple_pattern_recognition;

import javax.swing.text.Segment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Plane {

    // Instanza unica della classe, inizializzata durante il caricamento della classe
    private static final Plane INSTANCE = new Plane();

    private static final HashSet<Point> points = new HashSet<>();
    private static final HashSet<Line> lines = new HashSet<>();
    // private static final HashSet<Segment> segments = new HashSet<>();
    private int lastPointId = 0;
    // private int lastLineId = 0;
    // private int lastSegmentId = 0;

    // Costruttore privato per evitare l'istanziazione esterna
    private Plane() {
        // Inizializzazione della risorsa se necessario
    }

    // Metodo per ottenere l'istanza unica della classe
    public static Plane getInstance() {
        return INSTANCE;
    }


    public static void main(String[] args) {
        Plane plane = Plane.getInstance();
    }

    public void addPoint(Point newPoint) {
        points.add(newPoint);
        for (Point point : points) {
            if (point.getId() != newPoint.getId()) {

                point.connectTo(newPoint);
                newPoint.connectTo(point); // Bidirezionale
                // lines.add(new Line(point, newPoint));
                // segments.add(new Segment(point, newPoint)); // Aggiungi ricorsivamente segmenti
            }
        }
    }

    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    public Point getPoint(int id) {
        for (Point point : points) {
            if (point.getId() == id) {
                return point;
            }
        }
        return null;
    }

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

    public int getPointId() {
        return this.lastPointId++;
    }

    public List<Line> getLines() {
        return new ArrayList<>(lines);
    }

    public void clearAll() {
        points.clear();
        lines.clear();
        lastPointId = 0;
    }
}