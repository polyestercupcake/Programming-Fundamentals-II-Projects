package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class contains all the information having to do with aerobic exercises.
 * All information not here that is needed will be inside the Exercise class, which is
 * extended by this class.
 * @author Tanner's Laptop
 *
 */
public class ExerciseAerobic extends Exercise {
	
	/**
	 * Max Heart Rate during the student's aerobic exercise.
	 */
	private int maxHeartRate;
	/**
	 * Average Heart Rate during the student's aerobic exercise.
	 */
	private int averageHeartRate;
	/**
	 * Distance traveled during the student's aerobic exercise.
	 */
	private double distance;

	/**
	 * @return the maxHeartRate
	 */
	public final int getMaxHeartRate() {
		return maxHeartRate;
	}

	/**
	 * @param maxHeartRate the maxHeartRate to set
	 */
	public final void setMaxHeartRate(final int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	/**
	 * @return the averageHeartRate
	 */
	public final int getAverageHeartRate() {
		return averageHeartRate;
	}

	/**
	 * @param averageHeartRate the averageHeartRate to set
	 */
	public final void setAverageHeartRate(final int averageHeartRate) {
		this.averageHeartRate = averageHeartRate;
	}

	/**
	 * @return the distance
	 */
	public final double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public final void setDistance(final double distance) {
		this.distance = distance;
	}

	@Override
	public void load(final int studentID, final LocalDate exerciseDate, final String exerciseName) {	
	}

	@Override
	public final void save() {
		
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			params.add(new Parameter<LocalDate>(exerciseDate));
			params.add(new Parameter<String>(exerciseName));
			params.add(new Parameter<Long>(exerciseDuration.getSeconds()));
			params.add(new Parameter<Integer>(maxHeartRate));
			params.add(new Parameter<Integer>(averageHeartRate));
			params.add(new Parameter<Double>(distance));

			db.executeSql("Exercise.usp_SaveExerciseAerobic", params);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in saving the aerobic exercise.");
		}
	}

	@Override
	public final void delete() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			params.add(new Parameter<LocalDate>(exerciseDate));
			params.add(new Parameter<String>(exerciseName));
		
			db.executeSql("Exercise.usp_DeleteExerciseAerobic", params);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in deleting the student's aerobic exercise.");
		}
	}
}
