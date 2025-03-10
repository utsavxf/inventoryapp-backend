package com.ciena.inventoryapp.repository;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.model.DeviceDTO;
import com.ciena.inventoryapp.model.ShelfPosition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends Neo4jRepository<Device,Long> {
    // Inherits CRUD methods (save, findById, deleteById) from Neo4jRepository
    // but it only supports basic query,if we want to perform other we have to manually write them


    //let's write 1 custom query to get a device by a name
    @Query("MATCH (d:Device) where d.name=$name RETURN d")
    Device getDeviceByName(String name);

//    @Query("MATCH (d:Device) WHERE id(d)=$id "+
//    "OPTIONAL MATCH (d)-[:HAS]->(sp:ShelfPosition) "+
//    "RETURN d as device,collect(sp{.name}) as shelfPositions")
//    DeviceDTO getDeviceById(Long id);



    @Query("CREATE (d:Device{name:$name,type:$type}) RETURN d")
    Device saveDevice(String name,String type);

    @Query("MATCH (d:Device) RETURN d")
    List<Device> geAllDevices();

}

