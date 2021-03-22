package com.Project.dto;



public class AchievementDTO {

	private int id;

	private String achievement;

	private Boolean complete;

	public AchievementDTO() {
		super();
	}
	
	
	public AchievementDTO(int id, String achievement, Boolean complete) {
		super();
		this.id = id;
		this.achievement = achievement;
		this.complete = complete;
		
		
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAchievement() {
		return achievement;
	}


	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}


	public Boolean getComplete() {
		return complete;
	}


	public void setComplete(Boolean complete) {
		this.complete = complete;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achievement == null) ? 0 : achievement.hashCode());
		result = prime * result + ((complete == null) ? 0 : complete.hashCode());
		result = prime * result + id;
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
		AchievementDTO other = (AchievementDTO) obj;
		if (achievement == null) {
			if (other.achievement != null)
				return false;
		} else if (!achievement.equals(other.achievement))
			return false;
		if (complete == null) {
			if (other.complete != null)
				return false;
		} else if (!complete.equals(other.complete))
			return false;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Achievementdto [id=" + id + ", achievement=" + achievement + ", complete=" + complete + "]";
	}

	
	
	
}
