package com.acme.simple_pattern_recognition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GraphController {

    @Autowired
    private GraphService graphService;

    @PostMapping("/point")
    public ResponseEntity<?> savePoint(@RequestBody PointRequest pointRequest) {
        try {
            Point point = new Point(pointRequest.getX(), pointRequest.getY());
            graphService.savePoint(point);
            ApiResponse<String> response = new ApiResponse<>("Point added successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to save point: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lines/{n}")
    public ResponseEntity<ApiResponse<?>> getLinesWithAtLeastNPoints(@PathVariable int n) {
        try {
            // Better to do havi each combination of points and check if the line has at least n points
            List<Line> lines = graphService.getAllLines();
            ApiResponse<List<Line>> response = new ApiResponse<>(lines);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to get lines: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/point")
    public ResponseEntity<ApiResponse<?>> getPoint(@RequestParam String id) {
        try {
            Point point = graphService.getPoint(id);
            ApiResponse<Point> response = new ApiResponse<>(point);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to get point: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/space")
    public ResponseEntity<ApiResponse<?>> getPoints() {
        try {
            List<Point> points = graphService.getPoints();
            ApiResponse<List<Point>> response = new ApiResponse<>(points);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to get points: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lines")
    public ResponseEntity<ApiResponse<?>> getLines() {
        try {
            List<Line> lines = graphService.getAllLines();
            ApiResponse<List<Line>> response = new ApiResponse<>(lines);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to get lines: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/space")
    public ResponseEntity<ApiResponse<?>> clearAll() {
        try {
            graphService.clearAll();
            ApiResponse<String> response = new ApiResponse<>("All data cleared");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> response = new ApiResponse<>("Failed to clear all data: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}