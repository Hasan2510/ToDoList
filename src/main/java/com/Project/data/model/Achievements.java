package com.Project.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Achievements {


	

		
		
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(unique = true)
		@NotNull
		private String achievement;
		
		@NotNull
		private Boolean complete; 
		
		public Achievements() {
		
		}
	
		
		@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	    @JoinColumn(name = "fk_person_id")
	    private Person person;
		
		public Achievements(String achievement , Boolean complete) {
			super();
			this.achievement = achievement;
			this.complete = complete;
			
		}
		
		

		public Achievements(int id,  String achievement, Boolean complete) {
			super();
			this.id = id;
			this.achievement = achievement;
			this.complete = complete;
		}



		public Achievements(int id,  String achievement,  Boolean complete, Person person) {
			super();
			this.id = id;
			this.achievement = achievement;
			this.complete = complete;
			this.person = person;
		}



		public Person getPerson() {
			return person;
		}



		public void setPerson(Person person) {
			this.person = person;
		}



		public Boolean getComplete() {
			return complete;
		}


		public void setComplete(Boolean complete) {
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



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((achievement == null) ? 0 : achievement.hashCode());
			result = prime * result + ((complete == null) ? 0 : complete.hashCode());
			//result = prime * result + id;
			result = prime * result + ((person == null) ? 0 : person.hashCode());
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
			Achievements other = (Achievements) obj;
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
			//if (id != other.id)
				//return false;
			if (person == null) {
				if (other.person != null)
					return false;
			} else if (!person.equals(other.person))
				return false;
			return true;
		}



		@Override
		public String toString() {
			return "Achievements [id=" + id + ", achievement=" + achievement + ", complete=" + complete + ", person="
					+ person + "]";
		}

		
	}
	
	

