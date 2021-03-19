package com.Project.Controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.Project.data.model.Person;
import com.Project.dto.PersonDTO;

import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema-achievement.sql",
                "classpath:test-data-achievement.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class PersonControllerIntegrationTest {

        @Autowired
        private MockMvc mvc;

     

        @Autowired
        private ObjectMapper objectMapper;

        
        private PersonDTO validPersonDTO = new PersonDTO(1, "Hasan");

       
        private List<PersonDTO> validPersonDTOs = List.of(validPersonDTO);

        
        
        @Test
        void createPersonTest() throws Exception {
                Person personToSave = new Person(1, "Hasan", 20,List.of());
                PersonDTO expectedPerson = new PersonDTO(1, "Hasan");

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/person");

                mockRequest.contentType(MediaType.APPLICATION_JSON);
                mockRequest.content(objectMapper.writeValueAsString(personToSave));
                mockRequest.accept(MediaType.APPLICATION_JSON);

                ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

                ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                                .json(objectMapper.writeValueAsString(expectedPerson));

                ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location",
                                String.valueOf(expectedPerson.getId()));

                mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);

        }
        @Test
        void readAllPersonTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/person");
           
            mockRequest.accept(MediaType.APPLICATION_JSON);

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(validPersonDTOs));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }

        @Test
        void readPersonByIdTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/person/1");
            mockRequest.accept(MediaType.APPLICATION_JSON);
            

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(validPersonDTO));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }

        @Test
        void updatePersonTest() throws Exception {
            Person personToUpdate = new Person(1, "Hasan", 20,List.of());
            PersonDTO expectedPerson = new PersonDTO(1, "Hasan");
          

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/person/1");

            mockRequest.contentType(MediaType.APPLICATION_JSON);
            mockRequest.content(objectMapper.writeValueAsString(personToUpdate));
          
            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(expectedPerson));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
        }

        @Test
        void deletePersonTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/person/1");

            mockRequest.contentType(MediaType.TEXT_PLAIN);

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }
	
	}

