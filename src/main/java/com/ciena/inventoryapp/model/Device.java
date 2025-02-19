package com.ciena.inventoryapp.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Node  //to mark device as a neo4j node
public class Device {
 @Id //this annotation defines id as the primary key
 @GeneratedValue // to auto generate the ID
 private Long id;
 private String name;
 private String type;


 @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING) //outgoing relationship to shelfPositions
 private List<ShelfPosition> shelfPositions=new ArrayList<>();

    //Default constructor
    public Device() {}
    // Constructor
    public Device(Long id, String name, String type) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.shelfPositions=new ArrayList<>();
    }

    public void addShelfPosition(ShelfPosition shelfPosition){
        this.shelfPositions.add(shelfPosition);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<ShelfPosition> getShelfPositions() {
        return shelfPositions;
    }

    public void setShelfPositions(List<ShelfPosition> shelfPositions) {
        this.shelfPositions = shelfPositions;
    }
}
