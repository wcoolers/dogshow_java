package com.sheridan.akanbiad.beans;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinTable(
			name="OWNER_DOG", //table name
			joinColumns=@JoinColumn(name="DOG_ID"), //column name for this entity id
			inverseJoinColumns=@JoinColumn(name="OWNER_ID")) //column name for joining entity id
	private Owner owner;
	
	
	@ManyToMany
	@JoinTable(name="DOG_JUDGE", //table name
			joinColumns=@JoinColumn(name="DOG_ID"), //column name for this entity id
			inverseJoinColumns=@JoinColumn(name="JUDGE_ID")) //column name for joined entity id)
	
	private List<Judge> judges;

}
