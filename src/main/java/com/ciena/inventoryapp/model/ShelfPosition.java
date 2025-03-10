package com.ciena.inventoryapp.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Node
public class ShelfPosition {

    @GeneratedValue
    @Id
    private Long id;

    @NotNull(message = "Shelf Position Name is required")
    private String name;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING)
    private Shelf shelf;

    @Relationship(type = "HAS", direction = Relationship.Direction.INCOMING) // Relationship to Device
    private Device device; //object reference is needed for traversal


    public void attachDevice(Device device) {
        this.device = device;
    }

    public void attachShelf(Shelf shelf) {this.shelf = shelf;}

}
