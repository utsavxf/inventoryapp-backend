package com.ciena.inventoryapp.model;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class ShelfPosition {
    @Id
    private Long id;
    private String name;
    private Long deviceId;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private Shelf shelf;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING) // Relationship to Device
    private Device device; //object reference is needed for traversal


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
}
