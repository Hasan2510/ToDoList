package com.Project.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Project.Mappers.PersonMappers;
import com.Project.data.model.Achievements;
import com.Project.data.model.Person;
import com.Project.data.repository.PersonRepository;
import com.Project.dto.PersonDTO;
import com.Project.service.PersonServices;

@SpringBootTest
	public class PersonServiceIntegrationTest {

	    @Autowired
	    private PersonServices personService;

	    @Autowired
	    private PersonRepository personRepository;

	    @Autowired
	    private PersonMappers personMapper;

	    private List<Person> persons;
	    private List<PersonDTO> personDTOs;

	    private Person validPerson;
	    private PersonDTO validPersonDTO;

	    @BeforeEach
	    public void init() {
	        validPerson = new Person(1, "Hasan",18,List.of());
	        validPersonDTO = personMapper.mapToDTO(validPerson);

	        persons = new ArrayList<Person>();
	        personDTOs = new ArrayList<PersonDTO>();

	        personRepository.deleteAll();

	        validPerson = personRepository.save(validPerson);
	        validPersonDTO = personMapper.mapToDTO(validPerson);

	        persons.add(validPerson);
	        personDTOs.add(validPersonDTO);
	    }

	    @Test
	    void readAllPersonsTest() {
	        List<PersonDTO> personsInDb = personService.readAllPersons();
	        assertThat(personDTOs).isEqualTo(personsInDb);
	    }

	    @Test
	    void readByIdTest() {
	        assertThat(validPersonDTO).isEqualTo(personService.readById(validPerson.getId()));
	    }

	    @Test
	    void createPerson() {
	        Person newPerson = new Person(5, "Ali",18,List.of(new Achievements (null,null)));
	        assertThat(personMapper.mapToDTO(newPerson)).isEqualTo(personService.createPerson(newPerson));
	    }

	    @Test
	    void updatePerson() {
	    	Person newPerson = new Person(1,"Hasan",25, new ArrayList<Achievements>());
	    	 assertThat(personMapper.mapToDTO(newPerson)).isEqualTo(personService.updatePerson(validPerson.getId(), validPerson));
	        
	    
	    }

	    @Test
	    void deletePerson() {
	        assertThat(personService.deletePerson(validPerson.getId())).isTrue();
	    }
	
	
}
