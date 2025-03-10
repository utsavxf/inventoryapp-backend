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
public class Shelf {
 @GeneratedValue
 @Id
 private Long id;

 @NotNull(message = "name cannot be null")
 private String name;
 @NotNull(message = "type cannot be null")
 private String type;


 @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
 private ShelfPosition shelfPosition;

 //FUNCTION TO ADD SHEL TO SHELF POSITION
 public void addShelfPosition(ShelfPosition shelfPosition){
  this.shelfPosition = shelfPosition; //Update the Neo4j relationship
 }

}
