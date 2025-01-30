package com.ciena.inventoryapp.repository;

import com.ciena.inventoryapp.model.ShelfPosition;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShelfPositionRepository extends Neo4jRepository<ShelfPosition,Long> {
}
