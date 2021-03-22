package com.Project.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
