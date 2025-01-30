package com.ciena.inventoryapp.model;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Shelf {
 @Id
 private Long id;
 private String name;
 private String shelfType;
 private Long positionId; //will be associated with shelf position

 @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
 private ShelfPosition shelfPosition;

 //FUNCTION TO ADD SHEL TO SHELF POSITION
 public void addShelf(ShelfPosition shelfPosition){
  this.shelfPosition = shelfPosition; // ✅ Update the Neo4j relationship
  this.positionId = shelfPosition.getId(); // (Optional) Keep track of ID
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

 public String getShelfType() {
  return shelfType;
 }

 public void setShelfType(String shelfType) {
  this.shelfType = shelfType;
 }

 public Long getPositionId() {
  return positionId;
 }

 public void setPositionId(Long positionId) {
  this.positionId = positionId;
 }
}
