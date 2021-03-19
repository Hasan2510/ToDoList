package com.Project.Controller;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.Project.data.model.Achievements;
import com.Project.dto.AchievementDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema-achievement.sql",
                "classpath:test-data-achievement.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class AchievementControllerIntegrationTest {

        @Autowired
        private MockMvc mvc;

     

        @Autowired
        private ObjectMapper objectMapper;

        
        private AchievementDTO validAchievementDTO = new AchievementDTO(1, "get a phd", true);

       
        private List<AchievementDTO> validAchievementDTOs = List.of(validAchievementDTO);

        
        //validAchievementDTOs.forEach(ach -> ach.setAchievement("Train for the Olympics"));
        @Test
        void createAchievementTest() throws Exception {
                Achievements achievementToSave = new Achievements(2, "Train for the Olympics", true);
                AchievementDTO expectedAchievement = new AchievementDTO(2, "Train for the Olympics",true);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/achievement");

                mockRequest.contentType(MediaType.APPLICATION_JSON);
                mockRequest.content(objectMapper.writeValueAsString(achievementToSave));
                mockRequest.accept(MediaType.APPLICATION_JSON);

                ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

                ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                                .json(objectMapper.writeValueAsString(expectedAchievement));

                ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location",
                                String.valueOf(expectedAchievement.getId()));

                mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);

        }
        @Test
        void readAllAchievementTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/achievement");
           
            mockRequest.accept(MediaType.APPLICATION_JSON);

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(validAchievementDTOs));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }

        @Test
        void readAchievementByIdTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/achievement/1");
            mockRequest.accept(MediaType.APPLICATION_JSON);
            

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(validAchievementDTO));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }

        @Test
        void updateAchievementTest() throws Exception {
            Achievements achievementToUpdate = new Achievements(1, "Train for the Olympics", true);
            AchievementDTO expectedAchievement = new AchievementDTO(1, "Train for the Olympics", true);
          

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/achievement/1");

            mockRequest.contentType(MediaType.APPLICATION_JSON);
            mockRequest.content(objectMapper.writeValueAsString(achievementToUpdate));
          
            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                    .json(objectMapper.writeValueAsString(expectedAchievement));

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
        }

        @Test
        void deleteAchievementTest() throws Exception {
            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/achievement/1");

            mockRequest.contentType(MediaType.TEXT_PLAIN);

            ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

            ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");

            mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

        }

}