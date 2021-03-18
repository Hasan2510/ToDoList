package com.Project.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.data.model.Achievements;


@Repository
public interface AchievementsRepository extends JpaRepository<Achievements,Integer> {

}


