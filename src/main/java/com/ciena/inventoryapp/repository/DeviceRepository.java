package com.ciena.inventoryapp.repository;

import com.ciena.inventoryapp.model.Device;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends Neo4jRepository<Device,Long> {
    // Inherits CRUD methods (save, findById, deleteById) from Neo4jRepository
}
