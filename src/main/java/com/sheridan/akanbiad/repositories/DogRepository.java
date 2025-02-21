package com.sheridan.akanbiad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheridan.akanbiad.beans.Dog;


public interface DogRepository extends JpaRepository<Dog, Long> {

}
