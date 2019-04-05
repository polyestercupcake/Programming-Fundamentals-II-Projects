package model;

import java.time.Duration;
import java.time.LocalDate;

public abstract class Exercise {
	//FIELDS
	//made protected so anyone who inherits exercise can access these fields
	protected int studentID;
	protected LocalDate exerciseDate;
	protected String exerciseName;
	protected Duration exerciseDuration;
	//METHODS
	/**
	 * @return the studentID
	 */
	public final int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public final void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	/**
	 * @return the exerciseDate
	 */
	public final LocalDate getExerciseDate() {
		return exerciseDate;
	}
	/**
	 * @param exerciseDate the exerciseDate to set
	 */
	public final void setExerciseDate(LocalDate exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	/**
	 * @return the exerciseName
	 */
	public final String getExerciseName() {
		return exerciseName;
	}
	/**
	 * @param exerciseName the exerciseName to set
	 */
	public final void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	/**
	 * @return the exerciseDuration
	 */
	public final Duration getExerciseDuration() {
		return exerciseDuration;
	}
	/**
	 * @param exerciseDuration the exerciseDuration to set
	 */
	public final void setExerciseDuration(Duration exerciseDuration) {
		this.exerciseDuration = exerciseDuration;
	}
	
	public abstract void load(int studentID, LocalDate exerciseDate, String exerciseName);
	
	public abstract void save();
	
	public abstract void delete();

}
