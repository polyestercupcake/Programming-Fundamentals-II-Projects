package model;

/**
 * This is the business process (kitchen) for the Ohm's Law calculations.
 * @author Tanner's Laptop
 *
 */
public class Circuit {
	/**
	 * Holds the value for voltage.
	 */
	private double voltage;
	/**
	 * Holds the value for amperage.
	 */
	private double amperage;
	/**
	 * Holds the value for resistance.
	 */
	private double resistance;
	/**
	 * 
	 * @return voltage
	 */
	public double getVoltage() {
		return voltage;
	}
	/**
	 * 
	 * @param voltage the voltage to be set
	 */
	public void setVoltage(final double voltage) {
		this.voltage = voltage;
	}
	/**
	 * 
	 * @return amperage
	 */
	public double getAmperage() {
		return amperage;
	}
	/**
	 * 
	 * @param amperage the amperage to be set
	 */
	public void setAmperage(final double amperage) {
		this.amperage = amperage;
	}
	/**
	 * 
	 * @return resistance
	 */
	public double getResistance() {
		return resistance;
	}
	/**
	 * 
	 * @param resistance the resistance to be set
	 */
	public void setResistance(final double resistance) {
		this.resistance = resistance;
	}
	/**
	 * Performs the calculation for voltage.
	 */
	public void calcualteVoltage() {
		voltage = amperage * resistance;
		}
	/**
	 * Performs the calculation for amperage.
	 */
	public void calcualteAmperage() {
		amperage = voltage / resistance;
	}
	/**
	 * Performs the calculation for resistance.
	 */
	public void calcualteResistance() {
		resistance = voltage / amperage;
	}
}
