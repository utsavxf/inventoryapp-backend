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
public class Shelf {
 @GeneratedValue
 @Id
 private Long id;
 private String name;
 private String type;


 @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
 private ShelfPosition shelfPosition;

 //FUNCTION TO ADD SHEL TO SHELF POSITION
 public void addShelfPosition(ShelfPosition shelfPosition){
  this.shelfPosition = shelfPosition; //Update the Neo4j relationship
 }

 //GETTER AND SETTERS
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


 public ShelfPosition getShelfPosition() {
  return shelfPosition;
 }

 public void setShelfPosition(ShelfPosition shelfPosition) {
  this.shelfPosition = shelfPosition;
 }

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }
}
