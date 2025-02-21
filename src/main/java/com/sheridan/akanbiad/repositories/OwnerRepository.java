package com.sheridan.akanbiad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheridan.akanbiad.beans.Owner;



public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
