package model;

import java.time.LocalDate;

public class ExerciseAerobic extends Exercise {
	
	private int maxHeartRate;
	private int averageHeartRate;
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
	public final void setMaxHeartRate(int maxHeartRate) {
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
	public final void setAverageHeartRate(int averageHeartRate) {
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
	public final void setDistance(double distance) {
		this.distance = distance;
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
