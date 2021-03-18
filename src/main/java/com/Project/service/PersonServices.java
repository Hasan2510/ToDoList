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
	
	public List<PersonDTO> readAllPersons() {
		List<Person> persons = personRepository.findAll();
		List<PersonDTO> personDTOs = new ArrayList<>();
		persons.forEach(person -> personDTOs.add(personMapper.mapToDTO(person)));
		return personDTOs;
	}

	public PersonDTO readById(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return personMapper.mapToDTO(person.get());
        } else {
            throw new PersonNotFoundException("Person not found");
        }
	}
	
	public PersonDTO updatePerson(int id, Person person) {
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()) {
        	Person personToUpdate = optional.get();
            personToUpdate.setName(person.getName());
            return personMapper.mapToDTO(personRepository.save(personToUpdate));
        } else {
            throw new PersonNotFoundException();
        }
    }
	
}
