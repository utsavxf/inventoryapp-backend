package com.ciena.inventoryapp.repository;

import com.ciena.inventoryapp.model.Shelf;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ShelfRepository extends Neo4jRepository<Shelf,Long> {
}
