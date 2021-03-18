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

		
	}
	
	

