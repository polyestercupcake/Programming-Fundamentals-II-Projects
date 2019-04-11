package model;

import java.time.Duration;
import java.time.LocalDate;
/**
 * This is an abstract that acts as the parent class for ExerciseAerobic and ExerciseStrength.
 * It contains fields and method that will pertain to all things dealing with student exercises.
 * @author Tanner's Laptop
 */
public abstract class Exercise {
	//FIELDS
	//made protected so anyone who inherits exercise can access these fields
	/**
	 * The student ID number of the user. Must have this to save, load, or delete information
	 * or exercises.
	 */
	protected int studentID;
	/**
	 * The date the the exercise took place. LocalDate format.
	 */
	protected LocalDate exerciseDate;
	/**
	 * The name of the student's exercise.
	 */
	protected String exerciseName;
	/**
	 * The duration of the exercise. i.e. How long it took to complete.
	 */
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
	public final void setStudentID(final int studentID) {
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
	public final void setExerciseDate(final LocalDate exerciseDate) {
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
	public final void setExerciseName(final String exerciseName) {
		this.exerciseName = exerciseName;
	}
	/**
	 * @return the exerciseDuration
	 */
	public final Duration getExerciseDuration() {
		return exerciseDuration;
	}
	/**
	 * @return the exerciseDuration
	 */
	public final long getExerciseDurationMinutes() {
		return exerciseDuration.toMinutes();
	}
	/**
	 * @param exerciseDuration the exerciseDuration to set
	 */
	public final void setExerciseDuration(final Duration exerciseDuration) {
		this.exerciseDuration = exerciseDuration;
	}
	/**
	 * The code will be written in the ExerciseAerobic and ExerciseStrength classes.
	 * This is just the template method. It is overridden in the other classes.
	 * @param studentID the ID number of the student.
	 * @param exerciseDate the date the exercise took place.
	 * @param exerciseName the name of the current exercise.
	 */
	public abstract void load(int studentID, LocalDate exerciseDate, String exerciseName);
	/**
	 * The code will be written in the ExerciseAerobic and ExerciseStrength classes.
	 * This is just the template method. It is overridden in the other classes.
	 */
	public abstract void save();
	/**
	 * The code will be written in the ExerciseAerobic and ExerciseStrength classes.
	 * This is just the template method. It is overridden in the other classes.
	 */
	public abstract void delete();

}
