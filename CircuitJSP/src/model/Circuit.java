package model;

/**
 * This is the business process (kitchen) for the Ohm's Law calculations.
 * @author Tanner's Laptop
 *
 */
public class Circuit {

	double voltage;
	double amperage;
	double resistance;
	
	
	public double getVoltage() {
		return voltage;
	}
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}
	public double getAmperage() {
		return amperage;
	}
	public void setAmperage(double amperage) {
		this.amperage = amperage;
	}
	public double getResistance() {
		return resistance;
	}
	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	
	public void calcualteVoltage() {
		voltage = amperage * resistance;
		}
	public void calcualteAmperage() {
		amperage = voltage / resistance;
	}
	public void calcualteResistance() {
		resistance = voltage / amperage;
	}
	
}
