package com.Project.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Project.Mappers.PersonMappers;
import com.Project.data.model.Achievements;
import com.Project.data.model.Person;
import com.Project.data.repository.PersonRepository;
import com.Project.dto.PersonDTO;
import com.Project.service.PersonServices;

@SpringBootTest
class PersonServiceUnitTest {

    @Autowired
    private PersonServices personService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonMappers personMapper;

    private List<Person> persons;
    private List<PersonDTO> personDTOs;

    private Person validPerson;
    private PersonDTO validPersonDTO;

    @BeforeEach
    public void init() {
        validPerson = new Person(1, "Hasan",18,List.of(new Achievements (" get a degree",true)));
        validPersonDTO = new PersonDTO(3, "Hasan");

        persons = new ArrayList<Person>();
        personDTOs = new ArrayList<PersonDTO>();

        persons.add(validPerson);
        personDTOs.add(validPersonDTO);
    }

    @Test
    void readAllPersonsTest() {
        when(personRepository.findAll()).thenReturn(persons);
        when(personMapper.mapToDTO(Mockito.any(Person.class))).thenReturn(validPersonDTO);

        assertThat(personDTOs).isEqualTo(personService.readAllPersons());

        verify(personRepository, times(1)).findAll();
        verify(personMapper, times(1)).mapToDTO(Mockito.any(Person.class));
    }

    @Test
    void readByIdTest() {
        Optional<Person> optional = Optional.of(validPerson);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(personMapper.mapToDTO(Mockito.any(Person.class))).thenReturn(validPersonDTO);

        assertThat(validPersonDTO).isEqualTo(personService.readById(validPerson.getId()));

        verify(personRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(personMapper, times(1)).mapToDTO(Mockito.any(Person.class));
    }

    @Test
    void createPersonTest() {
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(validPerson);
        when(personMapper.mapToDTO(Mockito.any(Person.class))).thenReturn(validPersonDTO);

        assertThat(validPersonDTO).isEqualTo(personService.createPerson(validPerson));

        verify(personRepository, times(1)).save(Mockito.any(Person.class));
        verify(personMapper, times(1)).mapToDTO(Mockito.any(Person.class));
    }

    @Test
    void updatePersonTest() {
        Optional<Person> optional = Optional.of(validPerson);
        when(personRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(validPerson);
        when(personMapper.mapToDTO(Mockito.any(Person.class))).thenReturn(validPersonDTO);

        assertThat(validPersonDTO).isEqualTo(personService.updatePerson(validPerson.getId(), validPerson));

        verify(personRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(personRepository, times(1)).save(Mockito.any(Person.class));
        verify(personMapper, times(1)).mapToDTO(Mockito.any(Person.class));
    }

    @Test
    void deletePersonTest() {
    	when(personRepository.existsById(Mockito.any(Integer.class))).thenReturn(true).thenReturn(false);
        
        assertThat(true).isEqualTo(personService.deletePerson(1));
        verify(personRepository, times(1)).deleteById(Mockito.any(Integer.class));
        verify(personRepository, times(2)).existsById(Mockito.any(Integer.class));
    }

    
}
