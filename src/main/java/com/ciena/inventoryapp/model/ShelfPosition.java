package com.ciena.inventoryapp.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Node
public class ShelfPosition {

    @GeneratedValue
    @Id
    private Long id;
    private String name;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private Shelf shelf;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING) // Relationship to Device
    private Device device; //object reference is needed for traversal


    public void attachDevice(Device device) {
        this.device = device;
    }

    public void attachShelf(Shelf shelf) {this.shelf = shelf;}

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

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

}
