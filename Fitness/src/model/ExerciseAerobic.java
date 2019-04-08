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
public class ExerciseAerobic extends Exercise {
	
	/**
	 * 
	 */
	private int maxHeartRate;
	/**
	 * 
	 */
	private int averageHeartRate;
	/**
	 * 
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
	/**
	 * 
	 * @param studentID
	 * @return
	 */
	public List<ExerciseAerobic> loadAerobicExercise(final int studentID) {

		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		List<ExerciseAerobic> exerciseAerobic = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			
			ResultSet rsStudent = db.getResultSet("Exercise.usp_GetAerobicExercisesByPerson", params);
			while (rsStudent.next()) {
				ExerciseAerobic exAero = new ExerciseAerobic();
				exAero.setStudentID(rsStudent.getInt("studentID"));
				exAero.setExerciseDate(LocalDate.parse(rsStudent.getString("exerciseDate")));
				exAero.setExerciseName(rsStudent.getString("exerciseName"));
				exAero.setExerciseDuration(Duration.ofSeconds(Integer.parseInt(
						rsStudent.getString("exerciseSeconds"))));
				exAero.setMaxHeartRate(rsStudent.getInt("maxHeartRate"));
				exAero.setAverageHeartRate(rsStudent.getInt("averageHeartRate"));
				exAero.setDistance(rsStudent.getDouble("distance"));
				
				exerciseAerobic.add(exAero);
			}
			
	} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException("Something went wrong in loading the aerobic exercise.");
		}
		return exerciseAerobic;	
		
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
			//not getting either of these
			params.add(new Parameter<LocalDate>(exerciseDate));
			params.add(new Parameter<String>(exerciseName));
		
			db.executeSql("Exercise.usp_DeleteExerciseAerobic", params);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in deleting the student's aerobic exercise.");
		}
	}
}
