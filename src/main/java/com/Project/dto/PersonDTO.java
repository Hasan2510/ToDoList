package com.Project.dto;

import java.util.List;

public class PersonDTO {
	private int id;
	private String name;

	private List<AchievementDTO> achievements;

	public PersonDTO(int id, String name) {

		this.id = id;
		this.name = name;

	}

	public PersonDTO(int id, String name, List<AchievementDTO> achievements) {
		super();
		this.id = id;
		this.name = name;
		this.achievements = achievements;
	}

	public PersonDTO(String name, List<AchievementDTO> achievements) {
		super();
		this.name = name;
		this.achievements = achievements;
	}

	public PersonDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AchievementDTO> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<AchievementDTO> achievements) {
		this.achievements = achievements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achievements == null) ? 0 : achievements.hashCode());

		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		if (achievements == null) {
			if (other.achievements != null)
				return false;
		} else if (!achievements.equals(other.achievements))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + ", achievements=" + achievements + "]";
	}

}
