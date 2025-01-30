package com.ciena.inventoryapp.model;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Node  //to mark device as a neo4j node
public class Device {
 @Id //this annotation defines id as the primary key
 private Long id;
 private String name;
 private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING) //outgoing relationship to shelfPositions
    private List<ShelfPosition> shelfPositions=new ArrayList<>();


    // Constructor
    public Device(Long id, String name, String deviceType) {

        this.id = id;
        this.name = name;
        this.deviceType = deviceType;
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

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }


}
