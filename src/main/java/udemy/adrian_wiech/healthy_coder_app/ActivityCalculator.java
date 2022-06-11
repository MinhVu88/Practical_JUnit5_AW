/* Business requirements:
	- Rate user's weekly workout activity levels.
	- Accept the mins user spend doing cardio & the number of workout sessions (1 session = 45 mins).
	- Throw exception if the input is below 0.
	- Calculate the weekly total level, divided by 7 to find the average.
	- If average < 20, return "bad"
	- If average >= 20 & < 40, return "ok".
	- Otherwise, return "good".
*/
package udemy.adrian_wiech.healthy_coder_app;

public class ActivityCalculator {
	private static final int WORKOUT_SESSION_DURATION = 45;
	public static String rateActivityLevel(int weeklyCardioMins, int weeklyWorkoutSessions) {
		String feedback;

		int totalWeeklyWorkoutTime = weeklyCardioMins + weeklyWorkoutSessions * WORKOUT_SESSION_DURATION;

		double averageDailyWorkoutMins = totalWeeklyWorkoutTime / 7.0;

		if(weeklyCardioMins < 0 || weeklyWorkoutSessions < 0) {
			throw new RuntimeException("input below 0");
		}else if(averageDailyWorkoutMins < 20) {
			feedback = "bad";
		}else if(averageDailyWorkoutMins < 40) {
			feedback = "ok";
		}else {
			feedback = "good";
		}

		return feedback;
	}
}
