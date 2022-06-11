package udemy.adrian_wiech.healthy_coder_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivityCalculatorTest {
	@Test
	public void should_return_bad_when_averageLevel_below_20() {
		// given
		int weeklyCardioMins = 40;
		int weeklyWorkoutSessions = 1;

		// when
		String result = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkoutSessions);

		// then
		assertEquals("bad", result);
	}

	@Test
	public void should_return_ok_when_averageLevel_between_20_and_40() {
		// given
		int weeklyCardioMins = 40;
		int weeklyWorkoutSessions = 3;

		// when
		String result = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkoutSessions);

		// then
		assertEquals("ok", result);
	}

	@Test
	public void should_return_good_when_averageLevel_above_40() {
		// given
		int weeklyCardioMins = 40;
		int weeklyWorkoutSessions = 7;

		// when
		String result = ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkoutSessions);

		// then
		assertEquals("good", result);
	}

	@Test
	public void should_throw_exception_when_input_below_zero() {
		// given
		int weeklyCardioMins = -40;
		int weeklyWorkoutSessions = 7;

		// when
		Executable executable = () -> ActivityCalculator.rateActivityLevel(weeklyCardioMins, weeklyWorkoutSessions);

		// then
		assertThrows(RuntimeException.class, executable);
	}
}
