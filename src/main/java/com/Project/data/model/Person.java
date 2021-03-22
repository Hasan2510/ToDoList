package com.Project.data.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
	
	
	private String name;
	
	@Min(16)
	private int age;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private List<Achievements> achievements ;

	public Person() {
		super();
	}

	public Person(int id, String name, int age, List<Achievements> achievements) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.achievements = achievements;
	}

	public Person(String name,  int age, List<Achievements> achievements) {
		super();
		this.name = name;
		this.age = age;
		this.achievements = achievements;
	}

	public Person(int id, String name, @Min(16) int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Achievements> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievements> achievements) {
		this.achievements = achievements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((achievements == null) ? 0 : achievements.hashCode());
		result = prime * result + age;
		//result = prime * result + id;
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
		Person other = (Person) obj;
		if (achievements == null) {
			if (other.achievements != null)
				return false;
		} else if (!achievements.equals(other.achievements))
			return false;
		if (age != other.age)
			return false;
		//if (id != other.id)
		//	return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", achievements=" + achievements + "]";
	}
	
	
	

}
