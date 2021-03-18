package com.Project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.data.model.Person;
import com.Project.dto.PersonDTO;
import com.Project.service.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonServices personServices ;
	
	@Autowired
	public PersonController(PersonServices personServices) {
		super();
		this.personServices = personServices;
	}
	
	@GetMapping
	public ResponseEntity<List<PersonDTO>> getAllPersons() {
		List<PersonDTO> data = personServices.readAllPersons();

        return new ResponseEntity<List<PersonDTO>>(data, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") int id) {
        PersonDTO person = personServices.readById(id);
        return new ResponseEntity<PersonDTO>(person, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<PersonDTO> createPperson(@Valid @RequestBody Person person) {
        PersonDTO newPerson = personServices.createPerson(person);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(newPerson.getId()));

        return new ResponseEntity<PersonDTO>(newPerson, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        PersonDTO updatedPerson = personServices.updatePerson(id, person);
        return new ResponseEntity<PersonDTO>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable("id") int id) {
        boolean response = personServices.deletePerson(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
    }
}
