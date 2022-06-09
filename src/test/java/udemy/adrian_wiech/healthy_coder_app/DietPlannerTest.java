package udemy.adrian_wiech.healthy_coder_app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DietPlannerTest {
	private DietPlanner dietPlanner;

	@BeforeEach
	private void setupBeforeEach() {
		System.out.println("a new DietPlanner instance is instantiated before each test method");
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}

	@AfterEach
	private void announceAfterEach() {
		System.out.println("a message after each test method");
	}

	@Test
	public void should_calculate_diet_when_coder_provided() {
		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);

		DietPlan expectedDietPlan = new DietPlan(2202, 110, 73, 275);

		// when
		DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);

		// then
		assertAll(
			() -> assertEquals(expectedDietPlan.getCalories(), actualDietPlan.getCalories()),
			() -> assertEquals(expectedDietPlan.getProtein(), actualDietPlan.getProtein()),
			() -> assertEquals(expectedDietPlan.getFat(), actualDietPlan.getFat()),
			() -> assertEquals(expectedDietPlan.getCarbohydrate(), actualDietPlan.getCarbohydrate())
		);
	}
}
