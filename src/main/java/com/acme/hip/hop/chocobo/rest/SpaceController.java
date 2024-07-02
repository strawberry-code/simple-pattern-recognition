package com.acme.hip.hop.chocobo.rest;

import com.acme.hip.hop.chocobo.config.AppConfig;
import com.acme.hip.hop.chocobo.geometry.Space;
import com.acme.hip.hop.chocobo.geometry.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing points on a geometric space.
 * Provides endpoints to add points, retrieve points, and generate lines (segments).
 */
@RestController
public class SpaceController {

    private static final Logger logger = LogManager.getLogger(SpaceController.class);

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Space space;

    /**
     * Adds a point to the geometric space based on provided coordinates.
     * @param pointRequest the request body containing x and y coordinates of the point.
     * @return ResponseEntity containing the outcome of the operation.
     */
    @PostMapping("/point")
    public ResponseEntity<ApiResponse<String>> addPoint(@RequestBody PointRequest pointRequest) {
        try {
            logger.info(appConfig.getName());
            logger.info("Adding point at ({}, {})", pointRequest.getX(), pointRequest.getY());
            Point point = new Point(pointRequest.getX(), pointRequest.getY());
            space.addPoint(point);
            logger.info("Point added successfully");
            return ResponseEntity.ok(new ApiResponse<>("Point added successfully."));
        } catch (Exception e) {
            logger.error("Failed to add point: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to add point: " + e.getMessage()));
        }
    }

    /**
     * Retrieves combinations of points that form lines with at least 'n' points.
     * @param n the minimum number of points to form a line.
     * @return ResponseEntity containing lines or an error message.
     */
    @GetMapping("/lines/{n}")
    public ResponseEntity<ApiResponse<?>> getLinesWithAtLeastNPoints(@PathVariable int n) {
        try {
            logger.info("Getting lines with at least {} points", n);
            List<List<Point>> segments = Space.getInstance().getSegments(n, appConfig.getCombinationMode());
            logger.info("Retrieved {} lines", segments.size());
            return ResponseEntity.ok(new ApiResponse<>(segments));
        } catch (Exception e) {
            logger.error("Failed to get lines: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to get lines: " + e.getMessage()));
        }
    }

    /**
     * Retrieves a point by its ID.
     * @param id the ID of the point to retrieve.
     * @return ResponseEntity containing the point or an error message.
     */
    @GetMapping("/point")
    public ResponseEntity<ApiResponse<?>> getPointById(@RequestParam int id) {
        try {
            logger.info("Getting point with ID {}", id);
            Point point = space.getPoint(id);
            logger.info("Retrieved point: {}", point);
            return ResponseEntity.ok(new ApiResponse<>(point));
        } catch (Exception e) {
            logger.error("Failed to retrieve point: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to retrieve point: " + e.getMessage()));
        }
    }

    /**
     * Retrieves all points on the space.
     * @return ResponseEntity containing all points or an error message.
     */
    @GetMapping("/space")
    public ResponseEntity<ApiResponse<?>> getAllPoints() {
        try {
            logger.info("Getting all points");
            List<Point> points = space.getPoints();
            logger.info("Retrieved {} points", points.size());
            return ResponseEntity.ok(new ApiResponse<>(points));
        } catch (Exception e) {
            logger.error("Failed to retrieve points: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to retrieve points: " + e.getMessage()));
        }
    }

    /**
     * Clears all points from the geometric space.
     * @return ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/space")
    public ResponseEntity<ApiResponse<String>> clearAllPoints() {
        try {
            logger.info("Clearing all points");
            space.clearAll();
            logger.info("All data cleared successfully");
            return ResponseEntity.ok(new ApiResponse<>("All data cleared successfully."));
        } catch (Exception e) {
            logger.error("Failed to clear all data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to clear all data: " + e.getMessage()));
        }
    }
}