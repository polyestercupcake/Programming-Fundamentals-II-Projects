package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class contains all the information having to do with strength exercises.
 * All information not here that is needed will be inside the Exercise class, which is
 * extended by this class.
 * @author Tanner's Laptop
 *
 */
public class ExerciseStrength extends Exercise {
	
	/**
	 * Amount of sets completed during the student's strength exercise.
	 */
	private int sets;
	/**
	 * Amount of reps completed per set during the student's strength exercise.
	 */
	private int reps;
	/**
	 * Amount of weight lifted during the student's strength exercise.
	 */
	private double weightLifted;
	

	/**
	 * @return the sets
	 */
	public final int getSets() {
		return sets;
	}

	/**
	 * @param sets the sets to set
	 */
	public final void setSets(final int sets) {
		this.sets = sets;
	}

	/**
	 * @return the reps
	 */
	public final int getReps() {
		return reps;
	}

	/**
	 * @param reps the reps to set
	 */
	public final void setReps(final int reps) {
		this.reps = reps;
	}

	/**
	 * @return the weightLifted
	 */
	public final double getWeightLifted() {
		return weightLifted;
	}

	/**
	 * @param weightLifted the weightLifted to set
	 */
	public final void setWeightLifted(final double weightLifted) {
		this.weightLifted = weightLifted;
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
			params.add(new Parameter<Integer>(sets));
			params.add(new Parameter<Integer>(reps));
			params.add(new Parameter<Double>(weightLifted));

			db.executeSql("Exercise.usp_SaveExerciseStrength", params);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in saving the strength exercise.");
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
		
			db.executeSql("Exercise.usp_DeleteExerciseStrength", params);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in deleting the student's strength exercise.");
		}
	}
}
