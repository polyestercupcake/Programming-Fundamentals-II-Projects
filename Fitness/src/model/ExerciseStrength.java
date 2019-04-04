package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * 
 * @author Tanner's Laptop
 *
 */
public class ExerciseStrength extends Exercise {
	
	/**
	 * 
	 */
	private int sets;
	/**
	 * 
	 */
	private int reps;
	/**
	 * 
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
	public void load(final int studentID, LocalDate exerciseDate, String exerciseName) {
		//DONE???
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			
			ResultSet rsStudent = db.getResultSet("Exercise.usp_GetStrengthExerciseByPerson", params);
			if (rsStudent.next()) {
				this.studentID = rsStudent.getInt("studentID");
				this.exerciseDate = LocalDate.parse(rsStudent.getString("exerciseDate"));
				this.exerciseName = rsStudent.getString("exerciseName");
				this.exerciseDuration = Duration.parse(rsStudent.getString("exerciseDuration"));
				sets = rsStudent.getInt("sets");
				reps = rsStudent.getInt("reps");
				weightLifted = rsStudent.getDouble("weightLifted");
			} else {
				throw new IllegalArgumentException("This strength exercise is not found in our database.");
			}
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Something went wrong in loading the aerobic exercise.");
		}	
		
	}

	@Override
	public void save() {
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
	public void delete() {
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
