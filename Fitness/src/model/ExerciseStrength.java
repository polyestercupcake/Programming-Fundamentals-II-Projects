package model;

import java.time.LocalDate;

public class ExerciseStrength extends Exercise {
	
	private int sets;
	private int reps;
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
	public final void setSets(int sets) {
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
	public final void setReps(int reps) {
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
	public final void setWeightLifted(double weightLifted) {
		this.weightLifted = weightLifted;
	}

	@Override
	public void load(int studentID, LocalDate exerciseDate, String exerciseName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
