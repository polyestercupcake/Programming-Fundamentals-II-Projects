package bp;

/**
 * This is my business logic class, containing all the fields and methods
 * necessary for the program to function properly.
 * The ICircuit interface is implemented in this class.
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
		
	}

	@Override
	public final void setAmperage(final double pAmperage) {
		amperage = pAmperage;
		
	}

	@Override
	public final void setResistance(final double pResistance) {
		resistance = pResistance;
		
	}

	@Override
	public final void calculateVoltage() {
		voltage = amperage * resistance;
		
	}

	@Override
	public final void calculateAmperage() {
		amperage = voltage  / resistance;
		
	}

	@Override
	public final void calculateResistance() {
		resistance = voltage / amperage;
		
	}

}
