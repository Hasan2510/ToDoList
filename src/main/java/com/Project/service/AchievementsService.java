package com.Project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Project.Exceptions.AchievementNotFoundException;
import com.Project.Mappers.AchievementsMappers;
import com.Project.data.model.Achievements;
import com.Project.data.repository.AchievementsRepository;
import com.Project.dto.AchievementDTO;

@Service
public class AchievementsService {

	private AchievementsRepository achievementsRepository;
	private AchievementsMappers achievementsMapper;

	@Autowired
	public AchievementsService(AchievementsRepository achievementsRepository, AchievementsMappers achievementsMapper) {
		this.achievementsRepository = achievementsRepository;
		this.achievementsMapper = achievementsMapper;
	}

	public List<AchievementDTO> readAllAchievements() {
		List<Achievements> achievements = achievementsRepository.findAll();
		List<AchievementDTO> achievementDTO = new ArrayList<>();
		achievements.forEach(a -> achievementDTO.add(achievementsMapper.mapToDTO(a)));
		return achievementDTO;

	}

	public AchievementDTO readById(Integer id) {
		Optional<Achievements> achievement = achievementsRepository.findById(id);

		if (achievement.isPresent()) {
			return achievementsMapper.mapToDTO(achievement.get());
		} else {
			throw new AchievementNotFoundException("Achievement is not found");
		}
	}
	public AchievementDTO createAchievements(Achievements achievements) {
		Achievements newAchievements = achievementsRepository.save(achievements);
		return achievementsMapper.mapToDTO(newAchievements);
	}
}
