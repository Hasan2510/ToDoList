package com.Project.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AchievementDTOUnitTest {

	
	static AchievementDTO achievementsDTO;

	@BeforeEach
	void setup() {
		achievementsDTO = new AchievementDTO(1, "get married", true);
	}

	@Test
	void testEquals() {
		EqualsVerifier.simple().forClass(AchievementDTO.class)
				.withPrefabValues(PersonDTO.class, new PersonDTO(1,"Hasan"), new PersonDTO(2,"ali")).verify();
	}
	
	@Test
    void categoryWithIdTest() {
        AchievementDTO achievement1 = new AchievementDTO(1, "get married", true);
        assertThat(achievement1).isNotNull().isInstanceOf(AchievementDTO.class);
        assertThat(achievement1.getId()).isEqualTo(1);
        assertThat(achievement1.getAchievement()).isEqualTo("get married");
        assertThat(achievement1.getComplete()).isEqualTo(true);
    }
	
	@Test
	void hashCodeTest() {
		AchievementDTO achievementsDTO1 = new AchievementDTO(1, "get married", true);
		AchievementDTO achievementsDTO2 = new AchievementDTO(1, "get married", true);
		assertThat(achievementsDTO1).hasSameHashCodeAs(achievementsDTO2);
	}

	@Test
	void toStringTest() {
		assertThat(achievementsDTO).hasToString(
				"Achievementdto [id=" + achievementsDTO.getId() + ", achievement=" + achievementsDTO.getAchievement() + ", complete=" + achievementsDTO.getComplete() + 
						 "]");
				
	}
}
