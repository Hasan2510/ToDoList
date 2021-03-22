package com.Project.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Project.Mappers.AchievementsMappers;
import com.Project.data.model.Achievements;
import com.Project.data.repository.AchievementsRepository;
import com.Project.dto.AchievementDTO;
import com.Project.service.AchievementsService;

@SpringBootTest
public class AchievementServiceIntegrationTest {

    @Autowired
    private AchievementsService achievementService;

    @Autowired
    private AchievementsRepository achievementRepository;

    @Autowired
    private AchievementsMappers achievementMapper;

    private List<Achievements> achievements;
    private List<AchievementDTO> achievementDTOs;

    private Achievements validAchievement;
    private AchievementDTO validAchievementDTO;

    @BeforeEach
    public void init() {
        validAchievement = new Achievements(1, "finish a marathon", true);
        validAchievementDTO = new AchievementDTO(1, "complete ", false);

        achievements = new ArrayList<Achievements>();
        achievementDTOs = new ArrayList<AchievementDTO>();

        achievementRepository.deleteAll();

        validAchievement = achievementRepository.save(validAchievement);
        validAchievementDTO = achievementMapper.mapToDTO(validAchievement);

        achievements.add(validAchievement);
        achievementDTOs.add(validAchievementDTO);
    }

    @Test
    void readAllAchievementsTest() {
        List<AchievementDTO> achievementsInDb = achievementService.readAllAchievements();
        assertThat(achievementDTOs).isEqualTo(achievementsInDb);
    }

    @Test
    void readByIdTest() {
        assertThat(validAchievementDTO).isEqualTo(achievementService.readById(validAchievement.getId()));
    }

    @Test
    void createAchievement() {
        Achievements newAchievement = new Achievements(2, "Get a PHD", false);
        assertThat(achievementMapper.mapToDTO(newAchievement)).isEqualTo(achievementService.createAchievements(newAchievement));
    }

    @Test
    void updateAchievement() {
        assertThat(validAchievementDTO).isEqualTo(achievementService.updateachievements(validAchievement.getId(), validAchievement));
    }

    @Test
    void deleteAchievement() {
        assertThat(achievementService.deleteachievements(validAchievement.getId())).isTrue();
    }
}
