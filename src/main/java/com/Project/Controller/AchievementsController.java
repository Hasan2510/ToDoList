package com.Project.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.data.model.Achievements;
import com.Project.dto.AchievementDTO;
import com.Project.service.AchievementsService;

@RestController
@RequestMapping(path = "/achievement")
@CrossOrigin // Enables CORS
public class AchievementsController {

	
	    @Autowired
	    private AchievementsService achievementsService;

	    @GetMapping
	    public ResponseEntity<List<AchievementDTO>> getAllAchievements() {
	        List<AchievementDTO> data = achievementsService.readAllAchievements();

	        return new ResponseEntity<List<AchievementDTO>>(data, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<AchievementDTO> getAchievementsById(@PathVariable("id") int id) {
	        AchievementDTO achievement = achievementsService.readById(id);
	        return new ResponseEntity<AchievementDTO>(achievement, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<AchievementDTO> createAchievement(@Valid @RequestBody Achievements achievement) {
	        AchievementDTO newAchievement = achievementsService.createAchievements(achievement);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Location", String.valueOf(newAchievement.getId()));

	        return new ResponseEntity<AchievementDTO>(newAchievement, headers, HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<AchievementDTO> updateAchievement(@PathVariable("id") int id, @RequestBody Achievements achievement) {
	        AchievementDTO updatedAchievementDTO = achievementsService.updateachievements(id, achievement);
	        return new ResponseEntity<AchievementDTO>(updatedAchievementDTO, HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Boolean> deleteAchievement(@PathVariable("id") int id) {
	        boolean response = achievementsService.deleteachievements(id);
	        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
	    }

	    
}
