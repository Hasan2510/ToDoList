package com.Project.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Project.data.model.Person;
import com.Project.dto.PersonDTO;

@Component
public class PersonMappers {


	

	    private ModelMapper modelMapper;

	    @Autowired
	    public PersonMappers(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    public PersonDTO mapToDTO(Person person) {
	        return this.modelMapper.map(person, PersonDTO.class);
	    }

	    public Person mapToPerson(PersonDTO personDTO) {
	        return this.modelMapper.map(personDTO, Person.class);
	    }
}
