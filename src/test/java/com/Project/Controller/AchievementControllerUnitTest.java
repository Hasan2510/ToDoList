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

import com.Project.Mappers.AchievementsMappers;
import com.Project.data.model.Achievements;
import com.Project.dto.AchievementDTO;
import com.Project.service.AchievementsService;

@SpringBootTest
	class AchievementControllerUnitTest {

	    @Autowired
	    private AchievementsController achievementController;

	    @MockBean
	    private AchievementsService achievementService;

	    @MockBean
	    private AchievementsMappers achievementMapper;

	    private List<Achievements> achievements;
	    private List<AchievementDTO> achievementDTOs;

	    private Achievements validAchievement;
	    private AchievementDTO validAchievementDTO;

	    @BeforeEach
	    public void init() {
	        validAchievement = new Achievements(1, "get a degree",true);
	        validAchievementDTO = new AchievementDTO(1 ,"get a degree",false);

	        achievements = new ArrayList<Achievements>();
	        achievementDTOs = new ArrayList<AchievementDTO>();

	        achievements.add(validAchievement);
	        achievementDTOs.add(validAchievementDTO);
	    }

	    @Test
	    void deleteAchievementTest() {
	        when(achievementService.deleteachievements(Mockito.any(Integer.class))).thenReturn(true);

	        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);

	        assertThat(response).isEqualTo(achievementController.deleteAchievement(validAchievement.getId()));

	        verify(achievementService, times(1)).deleteachievements(Mockito.any(Integer.class));
	    }

	    @Test
	    void updateAchievementTest() {
	        when(achievementService.updateachievements(Mockito.any(Integer.class), Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        ResponseEntity<AchievementDTO> response = new ResponseEntity<AchievementDTO>(validAchievementDTO, HttpStatus.OK);

	        assertThat(response).isEqualTo(achievementController.updateAchievement(validAchievement.getId(), validAchievement));

	        verify(achievementService, times(1)).updateachievements(Mockito.any(Integer.class), Mockito.any(Achievements.class));
	    }

	    @Test
	    void getAllAchievementsTest() {
	        when(achievementService.readAllAchievements()).thenReturn(achievementDTOs);

	        
	        ResponseEntity<List<AchievementDTO>> response = new ResponseEntity<List<AchievementDTO>>(achievementDTOs,  HttpStatus.OK);

	        assertThat(response).isEqualTo(achievementController.getAllAchievements());

	        verify(achievementService, times(1)).readAllAchievements();
	    }

	    @Test
	    void getAchievementsByIdTest() {
	        when(achievementService.readById(Mockito.any(Integer.class))).thenReturn(validAchievementDTO);

	        ResponseEntity<AchievementDTO> response = new ResponseEntity<AchievementDTO>(validAchievementDTO, HttpStatus.OK);

	        assertThat(response).isEqualTo(achievementController.getAchievementsById(validAchievement.getId()));

	        verify(achievementService, times(1)).readById(Mockito.any(Integer.class));
	    }

	    @Test
	    void createAchievementTest() {
	        when(achievementService.createAchievements(Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Location", String.valueOf(validAchievement.getId()));
	        ResponseEntity<AchievementDTO> response = new ResponseEntity<AchievementDTO>(validAchievementDTO, headers, HttpStatus.CREATED);

	        assertThat(response).isEqualTo(achievementController.createAchievement(validAchievement));

	        verify(achievementService, times(1)).createAchievements(Mockito.any(Achievements.class));
	    }

	    
}
