package data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Project.data.model.Achievements;
import com.Project.data.model.Person;

import nl.jqno.equalsverifier.EqualsVerifier;

public class PersonUnitTest {

	
static Person person;
	
	@BeforeEach
	void setup() {
		person = new Person(1,"Hasan",24,null );
	}
	
	@Test
	void testEquals() {
		EqualsVerifier.simple().forClass(Person.class)
				.withPrefabValues(Achievements.class, new Achievements(1,"get married",true ), new Achievements(2,"get phd",false)).verify();
	}	

	@Test
	void toStringTest() {
		assertThat(person).hasToString(
				"Person [id=" + person.getId() + ", name=" + person.getName() +", age=" + person.getAge() + ", achievements=" + person.getAchievements() + "]");

	}
}
