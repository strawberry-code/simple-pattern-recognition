package com.acme.hip.hop.chocobo.geometry;

import com.acme.hip.hop.chocobo.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Manages a geometric space with a unique instance. Supports operations to add points,
 * retrieve points, and generate combinations of points.
 */
@Service
public class Space {

    private static final Logger logger = LogManager.getLogger(Space.class);

    private static final Space INSTANCE = new Space();
    private static final HashSet<Point> points = new HashSet<>();
    private static final List<List<Point>> segments = new ArrayList<>();
    private int lastPointId = 0;

    private Space() {
        // Private constructor to prevent external instantiation.
    }

    /**
     * Returns the single instance of this class.
     */
    public static Space getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new point to the space and connects it bidirectionally with all existing points.
     *
     * @param newPoint the new point to add
     */
    public void addPoint(Point newPoint) {
        points.add(newPoint);
        logger.info("Added point with ID {}", newPoint.getId());
    }

    /**
     * Retrieves a list of all points on the space.
     *
     * @return a list of all points
     */
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    /**
     * Returns the point with the specified ID, if it exists.
     *
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

    public static List<List<Point>> combinaRicorsivamente(ArrayList<Point> input, int k) {
        List<List<Point>> risultati = new ArrayList<>();
        combina(input, 0, k, new ArrayList<>(), risultati);
        return risultati;
    }

    private static void combina(ArrayList<Point> input, int start, int k, List<Point> attuale, List<List<Point>> risultati) {
        // Se la combinazione ha raggiunto la dimensione desiderata
        if (k == 0) {
            risultati.add(new ArrayList<>(attuale));
            return;
        }

        // Genera le combinazioni aggiungendo i successivi elementi
        for (int i = start; i <= input.size() - k; i++) {
            attuale.add(input.get(i));
            combina(input, i + 1, k - 1, attuale, risultati);
            attuale.remove(attuale.size() - 1);  // Rimuovi l'ultimo elemento per fare spazio al prossimo
        }
    }

    public static List<List<Point>> combinaIterativamente(List<Point> input, int k) {
        List<List<Point>> risultati = new ArrayList<>();
        int n = input.size();

        // Inizio dal primo valore possibile e vado fino all'ultimo valore possibile
        int combination = (1 << k) - 1;  // k bit impostati a 1, il resto a 0
        while (combination < (1 << n)) {
            List<Point> current = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((combination & (1 << i)) != 0) {
                    current.add(input.get(i));
                }
            }
            if (current.size() == k) {
                risultati.add(current);
            }
            // Genera la prossima combinazione
            int x = combination & -combination;
            int y = combination + x;
            int z = (combination & ~y);
            combination = z / x >> 1 | y;
        }

        return risultati;
    }

    /**
     * Generates all possible combinations of points of a given size.
     *
     * @param n the number of points in each combination
     * @return a list of point combinations
     */
    public List<List<Point>> getSegments(int n, String combinationMode) {
        logger.info("Generating segments with at least {} points", n);
        logger.info("Combination mode: {}", combinationMode);
        List<List<Point>> segments = new ArrayList<List<Point>>();
        if (n < 2) {
            logger.warn("Cannot generate segments with less than 2 points");
            return segments;
        } else if (combinationMode.equals("recursive")) {
            segments = combinaRicorsivamente(new ArrayList<>(points), n);
            logger.info("Generated {} segments recursively", segments.size());
        } else if (combinationMode.equals("iterative")) {
            segments = combinaIterativamente(new ArrayList<>(points), n);
            logger.info("Generated {} segments iteratively", segments.size());
        } else {
            logger.error("Invalid combination mode: {}", combinationMode);
            throw new IllegalArgumentException("Invalid combination mode: " + combinationMode + ". Supported modes are 'recursive' and 'iterative'.");
        }

        return segments;
    }


    /**
     * Clears all points from the space and resets the ID counter.
     */
    public void clearAll() {
        points.clear();
        lastPointId = 0;
    }

    public int getPointId() {
        return lastPointId++;
    }
}