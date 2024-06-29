package com.acme.hip.hop.chocobo.rest;

import com.acme.hip.hop.chocobo.geometry.Space;
import com.acme.hip.hop.chocobo.geometry.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing points on a geometric space.
 */
@RestController
public class SpaceController {

    /**
     * Adds a point to the geometric space based on provided coordinates.
     * @param pointRequest the request body containing x and y coordinates of the point.
     * @return ResponseEntity containing the outcome of the operation.
     */
    @PostMapping("/point")
    public ResponseEntity<ApiResponse<String>> addPoint(@RequestBody PointRequest pointRequest) {
        try {
            Point point = new Point(pointRequest.getX(), pointRequest.getY());
            Space.getInstance().addPoint(point);
            return ResponseEntity.ok(new ApiResponse<>("Point added successfully."));
        } catch (Exception e) {
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
            List<List<Point>> nTuples = Space.getInstance().getSegments(n);
            return ResponseEntity.ok(new ApiResponse<>(nTuples));
        } catch (Exception e) {
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
            Point point = Space.getInstance().getPoint(id);
            return ResponseEntity.ok(new ApiResponse<>(point));
        } catch (Exception e) {
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
            List<Point> points = Space.getInstance().getPoints();
            return ResponseEntity.ok(new ApiResponse<>(points));
        } catch (Exception e) {
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
            Space.getInstance().clearAll();
            return ResponseEntity.ok(new ApiResponse<>("All data cleared successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Failed to clear all data: " + e.getMessage()));
        }
    }
}