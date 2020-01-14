package model;

/**
 * This is my business logic class, containing all the fields and methods
 * necessary for the program to function properly. The ICircuit interface is
 * implemented in this class.
 * 
 * @author Tanner's Laptop
 */
public class Circuit implements ICircuit {
	/**
	 * This field represents the voltage of the circuit.
	 */
	private double voltage;
	/**
	 * This field represents the amperage of the circuit.
	 */
	private double amperage;
	/**
	 * This field represents the resistance of the circuit.
	 */
	private double resistance;

	@Override
	public final double getVoltage() {
		return voltage;
	}

	@Override
	public final double getAmperage() {
		return amperage;
	}

	@Override
	public final double getResistance() {
		return resistance;
	}

	@Override
	public final void setVoltage(final double pVoltage) {
		voltage = pVoltage;
		if (pVoltage <= 0) {
			throw new IllegalArgumentException("Voltage " + "must be greater than or equal to 0. \nPlease try again.");
		}

	}

	@Override
	public final void setAmperage(final double pAmperage) {
		amperage = pAmperage;
		if (pAmperage <= 0) {
			throw new IllegalArgumentException("Amperage " + "must be greater than or equal to 0. \nPlease try again.");
		}
	}

	@Override
	public final void setResistance(final double pResistance) {
		resistance = pResistance;
		if (pResistance <= 0) {
			throw new IllegalArgumentException(
					"Resistance " + "must be greater than or equal to 0. \nPlease try again.");
		}
	}

	@Override
	public final void calculateVoltage() {
		voltage = amperage * resistance;
	}

	@Override
	public final void calculateAmperage() {
		amperage = voltage / resistance;
	}

	@Override
	public final void calculateResistance() {
		resistance = voltage / amperage;
	}

}
