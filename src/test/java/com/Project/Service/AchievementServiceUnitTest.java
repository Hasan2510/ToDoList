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

import com.Project.Mappers.AchievementsMappers;
import com.Project.data.model.Achievements;
import com.Project.data.repository.AchievementsRepository;
import com.Project.dto.AchievementDTO;
import com.Project.service.AchievementsService;




	
	
	@SpringBootTest
	class AchievementServiceUnitTest {

	    @Autowired
	    private AchievementsService achievementService;

	    @MockBean
	    private AchievementsRepository achievementRepository;

	    @MockBean
	    private AchievementsMappers achievementMapper;

	    private List<Achievements> achievements;
	    private List<AchievementDTO> achievementDTOs;

	    private Achievements validAchievement;
	    private AchievementDTO validAchievementDTO;

	    @BeforeEach
	    public void init() {
	        validAchievement = new Achievements(1, "get a degree",true );
	        validAchievementDTO = new AchievementDTO(3, "got a degree", false);

	        achievements = new ArrayList<Achievements>();
	        achievementDTOs = new ArrayList<AchievementDTO>();

	        achievements.add(validAchievement);
	        achievementDTOs.add(validAchievementDTO);
	    }

	    @Test
	    void readAllAchievementsTest() {
	        when(achievementRepository.findAll()).thenReturn(achievements);
	        when(achievementMapper.mapToDTO(Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        assertThat(achievementDTOs).isEqualTo(achievementService.readAllAchievements());

	        verify(achievementRepository, times(1)).findAll();
	        verify(achievementMapper, times(1)).mapToDTO(Mockito.any(Achievements.class));
	    }

	    @Test
	    void readByIdTest() {
	        Optional<Achievements> optional = Optional.of(validAchievement);
	        when(achievementRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
	        when(achievementMapper.mapToDTO(Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        assertThat(validAchievementDTO).isEqualTo(achievementService.readById(validAchievement.getId()));

	        verify(achievementRepository, times(1)).findById(Mockito.any(Integer.class));
	        verify(achievementMapper, times(1)).mapToDTO(Mockito.any(Achievements.class));
	    }

	    @Test
	    void createAchievementTest() {
	        when(achievementRepository.save(Mockito.any(Achievements.class))).thenReturn(validAchievement);
	        when(achievementMapper.mapToDTO(Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        assertThat(validAchievementDTO).isEqualTo(achievementService.createAchievements(validAchievement));

	        verify(achievementRepository, times(1)).save(Mockito.any(Achievements.class));
	        verify(achievementMapper, times(1)).mapToDTO(Mockito.any(Achievements.class));
	    }

	    @Test
	    void updateAchievementTest() {
	        Optional<Achievements> optional = Optional.of(validAchievement);
	        when(achievementRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
	        when(achievementRepository.save(Mockito.any(Achievements.class))).thenReturn(validAchievement);
	        when(achievementMapper.mapToDTO(Mockito.any(Achievements.class))).thenReturn(validAchievementDTO);

	        assertThat(validAchievementDTO).isEqualTo(achievementService.updateachievements(validAchievement.getId(), validAchievement));

	        verify(achievementRepository, times(1)).findById(Mockito.any(Integer.class));
	        verify(achievementRepository, times(1)).save(Mockito.any(Achievements.class));
	        verify(achievementMapper, times(1)).mapToDTO(Mockito.any(Achievements.class));
	    }

	    @Test
	    void deleteAchievementTest() {
		        when(achievementRepository.existsById(Mockito.any(Integer.class))).thenReturn(true);
		        
	        assertThat(true).isEqualTo(achievementService.deleteachievements(1));
	        verify(achievementRepository, times(1)).deleteById(Mockito.any(Integer.class));
	        verify(achievementRepository, times(1)).existsById(Mockito.any(Integer.class));
	    }

	   
}

