package com.Project.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Project.Mappers.PersonMappers;
import com.Project.data.model.Achievements;
import com.Project.data.model.Person;
import com.Project.dto.PersonDTO;
import com.Project.service.PersonServices;

@SpringBootTest
	class PersonControllerUnitTest {

	    @Autowired
	    private PersonController personController;

	    @MockBean
	    private PersonServices personService;

	    @MockBean
	    private PersonMappers personMapper;

	    private List<Person> persons;
	    private List<PersonDTO> personDTOs;

	    private Person validPerson;
	    private PersonDTO validPersonDTO;

	    @BeforeEach
	    public void init() {
	        validPerson = new Person(1, "Hasan",18,List.of(new Achievements (" get a degree",true)));
	        validPersonDTO = new PersonDTO(1, "hasan");

	        persons = new ArrayList<Person>();
	        personDTOs = new ArrayList<PersonDTO>();

	        persons.add(validPerson);
	        personDTOs.add(validPersonDTO);
	    }

	    @Test
	    void deletePersonTest() {
	        when(personService.deletePerson(Mockito.any(Integer.class))).thenReturn(true);

	        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);

	        assertThat(response).isEqualTo(personController.deletePerson(validPerson.getId()));

	        verify(personService, times(1)).deletePerson(Mockito.any(Integer.class));
	    }

	    @Test
	    void updatePersonTest() {
	        when(personService.updatePerson(Mockito.any(Integer.class), Mockito.any(Person.class))).thenReturn(validPersonDTO);

	        ResponseEntity<PersonDTO> response = new ResponseEntity<PersonDTO>(validPersonDTO, HttpStatus.OK);

	        assertThat(response).isEqualTo(personController.updatePerson(validPerson.getId(), validPerson));

	        verify(personService, times(1)).updatePerson(Mockito.any(Integer.class), Mockito.any(Person.class));
	    }

	    @Test
	    void getAllPersonsTest() {
	        when(personService.readAllPersons()).thenReturn(personDTOs);

	        ResponseEntity<List<PersonDTO>> response = new ResponseEntity<List<PersonDTO>>(personDTOs, HttpStatus.OK);

	        assertThat(response).isEqualTo(personController.getAllPersons());

	        verify(personService, times(1)).readAllPersons();
	    }

	    @Test
	    void getPersonsByIdTest() {
	        when(personService.readById(Mockito.any(Integer.class))).thenReturn(validPersonDTO);

	        ResponseEntity<PersonDTO> response = new ResponseEntity<PersonDTO>(validPersonDTO, HttpStatus.OK);

	        assertThat(response).isEqualTo(personController.getPersonById(validPerson.getId()));

	        verify(personService, times(1)).readById(Mockito.any(Integer.class));
	    }

	    @Test
	    void createPersonTest() {
	        when(personService.createPerson(Mockito.any(Person.class))).thenReturn(validPersonDTO);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Location", String.valueOf(validPerson.getId()));
	        ResponseEntity<PersonDTO> response = new ResponseEntity<PersonDTO>(validPersonDTO, headers, HttpStatus.CREATED);

	        assertThat(response).isEqualTo(personController.createPperson(validPerson));

	        verify(personService, times(1)).createPerson(Mockito.any(Person.class));
	    }

	  

}
