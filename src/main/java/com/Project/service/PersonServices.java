package com.Project.service;

import org.springframework.stereotype.Service;

import com.Project.Exceptions.PersonNotFoundException;
import com.Project.Mappers.PersonMappers;
import com.Project.data.model.Person;
import com.Project.data.repository.PersonRepository;
import com.Project.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonServices {

	private PersonRepository personRepository;
	private PersonMappers personMapper;

	@Autowired
	public PersonServices(PersonRepository personRepository, PersonMappers personMapper) {

		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}
	
	public PersonDTO createPerson(Person person) {
		return personMapper.mapToDTO(personRepository.save(person));
	}
	
}
