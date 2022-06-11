package udemy.adrian_wiech.healthy_coder_app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class BMICalculatorTest {
	private String env= "prod";

	@BeforeAll
	private static void setupBeforeAll() {
		System.out.println("before all unit tests");
	}

	@AfterAll
	private static void announceAferAll() {
		System.out.println("after all unit tests");
	}

	@Nested
	class IsDietRecommendedTests {
		//@Test
		@ParameterizedTest(name = "weight={0}, height={1}")
		//@ValueSource(doubles = {89.0, 95.0, 110.0})
		//@CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
		@CsvFileSource(
			resources = "/diet-recommended-input-data.csv",
			numLinesToSkip = 1
		)
		public void should_isDietRecommended_return_true(
			/*double valueSourceWeight*/
			/* double csvSourceWeight,
			double csvSourceHeight */
			double csvFileSourceWeight,
			double csvFileSourceHeight
		) {
			// GIVEN
			// double weight = 89.0;
			// double weight = valueSourceWeight;
			// double height = 1.72;
			// double weight = csvSourceWeight;
			// double height = csvSourceHeight;
			double weight = csvFileSourceWeight;
			double height = csvFileSourceHeight;

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
	}

	@Nested
	@DisplayName("tests that contain the findCoderWithWorstBMI method")
	class FindCoderWithWorstBMITests {
		@Test
		@DisplayName("this test should return the coder that has the worst BMI, as long as the coders list isn't empty")
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
		@DisabledOnOs(OS.WINDOWS)
		public void should_return_coderWithWorstBMI_within_a_timeframe_when_codersList_has_many_elements() {
			// given
			assumeTrue(BMICalculatorTest.this.env.equals("prod"));

			List<Coder> coders = new ArrayList<>();

			for(int i = 0; i < 10000; i++) {
				coders.add(new Coder(1.0 + i, 10.0 + i));
			}

			// when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

			// then
			assertTimeout(Duration.ofMillis(500), executable);
		}

		@Test
		@Disabled
		public void should_return_no_coder_with_worst_bmi_when_codersList_is_empty() {
			// given
			List<Coder> coders = new ArrayList<>();

			// when
			Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

			// then
			assertNull(coderWithWorstBMI);
		}
	}

	@Nested
	class GetBMIScoresTests {
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
}
