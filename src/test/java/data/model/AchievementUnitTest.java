package data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Project.data.model.Achievements;
import com.Project.data.model.Person;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AchievementUnitTest {

	static Achievements achievements;

	@BeforeEach
	void setup() {
		achievements = new Achievements(1, "get married", true);
	}

	@Test
	void testEquals() {
		EqualsVerifier.simple().forClass(Achievements.class)
				.withPrefabValues(Person.class, new Person(1,"Hasan",20,null), new Person(2,"ali",24,null)).verify();
	}
	
	@Test
    void categoryWithIdTest() {
        Achievements achievement1 = new Achievements(1, "get married", true);
        assertThat(achievement1).isNotNull().isInstanceOf(Achievements.class);
        assertThat(achievement1.getId()).isEqualTo(1);
        assertThat(achievement1.getAchievement()).isEqualTo("get married");
        assertThat(achievement1.getComplete()).isEqualTo(true);
    }
	
	@Test
	void hashCodeTest() {
		Achievements achievements1 = new Achievements(1, "get married", true);
		Achievements achievements2 = new Achievements(1, "get married", true);
		assertThat(achievements1).hasSameHashCodeAs(achievements2);
	}

	@Test
	void toStringTest() {
		assertThat(achievements).hasToString(
				"Achievements [id=" + achievements.getId() + ", achievement=" + achievements.getAchievement() + ", complete=" + achievements.getComplete() + ", person="
						+ achievements.getPerson() + "]");
				
	}
	
	
}
