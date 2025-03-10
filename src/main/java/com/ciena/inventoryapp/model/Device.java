package com.ciena.inventoryapp.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //to prevent infinite recursion
@Node  //to mark device as a neo4j node
public class Device {


 @Id //this annotation defines id as the primary key
 @GeneratedValue // to auto generate the ID
 private Long id;

 @NotBlank(message = "Device name is required")
 private String name;
 @NotBlank(message = "Device type is required")
 private String type;


 @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING) //outgoing relationship to shelfPositions
 private List<ShelfPosition> shelfPositions=new ArrayList<>();

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
}
