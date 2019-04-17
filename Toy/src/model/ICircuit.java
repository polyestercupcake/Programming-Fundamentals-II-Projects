package model;

/**
 * This interface provides the basic Ohm's law structure of a circuit.
 * 
 * @author cberkstresser
 * @version 1.2
 * 
 */
public interface ICircuit {
	// **********Assessors************

	/**
	 * 
	 * @return the ToyID of the circuit
	 */
	int getToyID();

	/**
	 * 
	 * @return the CircuitID of the circuit
	 */
	int getCircuitID();

	/**
	 * 
	 * @return the voltage of the circuit
	 */
	double getVoltage();

	/**
	 * 
	 * @return the amperage of the circuit
	 */
	double getAmperage();

	/**
	 * 
	 * @return the resistance of the circuit
	 */
	double getResistance();

	/**
	 * 
	 * @return the location where this circuit was manufactured.
	 */
	String getManufactureLocation();

	// **********Mutators************
	/**
	 * Sets the ToyID.
	 * 
	 * @param pToyID the toy's ID to set
	 */
	void setToyID(int pToyID);

	/**
	 * Sets the CircuitID.
	 * 
	 * @param pCircuitID the circuit's ID to set
	 */
	void setCircuitID(int pCircuitID);

	/**
	 * Sets the voltage.
	 * 
	 * @param pVoltage the voltage to set
	 */
	void setVoltage(double pVoltage);

	/**
	 * Sets the resistance.
	 * 
	 * @param pResistance the resistance to set
	 */
	void setResistance(double pResistance);

	/**
	 * Sets the location where the circuit was manufactured.
	 * 
	 * @param pManufactureLocation location where circuit was manufactured.
	 */
	void setManufactureLocation(String pManufactureLocation);

}
