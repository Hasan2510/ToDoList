package com.Project.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Project.data.model.Achievements;
import com.Project.dto.AchievementDTO;

@Component
public class AchievementsMappers {


	    private ModelMapper modelMapper;

	    @Autowired
	    public AchievementsMappers(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    public AchievementDTO mapToDTO(Achievements achievement) {
	        return this.modelMapper.map(achievement, AchievementDTO.class);
	    }

	    public Achievements mapToAchievement(AchievementDTO achievementDTO ) {
	        return this.modelMapper.map(achievementDTO, Achievements.class);
	    }
	
	
	
}
