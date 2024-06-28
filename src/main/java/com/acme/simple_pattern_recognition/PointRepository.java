package com.acme.simple_pattern_recognition;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.List;

public interface PointRepository extends Neo4jRepository<Point, String> {
    List<Point> findAll();
}

