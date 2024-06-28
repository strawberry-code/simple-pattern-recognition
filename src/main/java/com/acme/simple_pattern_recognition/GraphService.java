package com.acme.simple_pattern_recognition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    @Autowired
    private PointRepository pointRepository;

    public void savePoint(Point point) {
        pointRepository.save(point);
        List<Point> points = pointRepository.findAll();
        for (Point p : points) {
            if (!p.getId().equals(point.getId())) {
                point.connectTo(p);
                p.connectTo(point); // Bidirectional connection
                pointRepository.save(p);
            }
        }
        pointRepository.save(point);
    }

    public List<Line> getAllLines() {
        List<Point> points = pointRepository.findAll();
        List<Line> Lines = new ArrayList<>();
        for (Point from : points) {
            for (Point to : from.getPoints()) {
                Lines.add(new Line(from, to));
            }
        }
        return Lines;
    }


    public Point getPoint(String id) {
        return pointRepository.findById(id).orElse(null);
    }

    public List<Point> getPoints() {
        return pointRepository.findAll();
    }

    public List<Line> getLinesWithAtLeastNPoints(int n) {
        List<Point> points = getPoints();
        List<Line> result = new ArrayList<>();

        // Trovare tutti i segmenti di linea che passano attraverso almeno N punti
        // Esempio di logica di base; migliorare l'algoritmo secondo necessità
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                List<Point> linePoints = new ArrayList<>();
                linePoints.add(points.get(i));
                linePoints.add(points.get(j));

                // Verifica quanti punti sono su questa linea
                int finalI = i;
                int finalJ = j;
                long count = points.stream().filter(p -> isPointOnLine(p, points.get(finalI), points.get(finalJ))).count();

                if (count >= n) {
                    //result.add(new Line(linePoints));
                }
            }
        }

        return result;
    }

    private boolean isPointOnLine(Point p, Point p1, Point p2) {
        // Logica per verificare se il punto p è sulla linea definita dai punti p1 e p2
        return (p2.getY() - p1.getY()) * (p.getX() - p1.getX()) == (p.getY() - p1.getY()) * (p2.getX() - p1.getX());
    }

    public void clearAll() {
        pointRepository.deleteAll();
    }

    public void clearPoints() {
        pointRepository.deleteAll();
    }
    
}