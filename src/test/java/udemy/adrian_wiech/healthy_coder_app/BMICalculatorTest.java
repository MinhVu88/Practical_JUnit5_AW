package udemy.adrian_wiech.healthy_coder_app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {
	@BeforeAll
	private static void setupBeforeAll() {
		System.out.println("before all unit tests");
	}

	@AfterAll
	private static void announceAferAll() {
		System.out.println("after all unit tests");
	}

	@Test
	public void should_isDietRecommended_return_true() {
		// GIVEN
		double weight = 89.0;
		double height = 1.72;

		// WHEN
		boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

		// THEN
		assertTrue(isRecommended);
	}

	@Test
	public void should_isDietRecommended_return_false() {
		// GIVEN
		double weight = 50.0;
		double height = 1.92;

		// WHEN
		boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

		// THEN
		assertFalse(isRecommended);
	}

	@Test
	public void should_throw_ArithmeticException_when_height_is_zero() {
		// given
		double weight = 50.0;
		double height = 0.0;

		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(ArithmeticException.class, executable);
	}

	@Test
	public void should_return_coder_with_worst_bmi_when_codersList_not_empty() {
		// given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));

		// when
		Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		// case #1: both assertions pass
		// assertEquals(1.82, coderWithWorstBMI.getHeight());
		// assertEquals(98.0, coderWithWorstBMI.getWeight());

		// case #2: if the 1st assertion fails, the 2nd one will never be executed.
		// In this case, there's no way to know if the 2nd assertion also fails or just the 1st one
		// assertEquals(1.85, coderWithWorstBMI.getHeight());
		// assertEquals(98.5, coderWithWorstBMI.getWeight());

		// case #3: use assertAll to execute both assertions
		assertAll(
			() -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
			() -> assertEquals(98.0, coderWithWorstBMI.getWeight())
		);
	}

	@Test
	public void should_return_no_coder_with_worst_bmi_when_codersList_is_empty() {
		// given
		List<Coder> coders = new ArrayList<>();

		// when
		Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coderWithWorstBMI);
	}

	@Test
	public void should_getBMIScores_return_correct_scores_when_codersList_not_empty() {
		// given
		List<Coder> coders = new ArrayList<>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));

		double[] expectedBmiScores = {18.52, 29.59, 19.53};

		// when
		double[] bmiScores = BMICalculator.getBMIScores(coders);

		// then
		assertArrayEquals(expectedBmiScores, bmiScores);
	}
}
