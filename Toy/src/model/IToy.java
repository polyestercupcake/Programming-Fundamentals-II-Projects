package model;

import java.time.LocalDateTime;

/**
 * This interface provides the basic layout of a toy circuit.
 * 
 * @author cberkstresser
 * @version 1.2
 * 
 */
public interface IToy {
	// **********Assessors************
	/**
	 * 
	 * @return The ToyID of the toy.
	 */
	int getToyID();

	/**
	 * 
	 * @return The inspector performing quality control on this toy.
	 */
	String getInspector();

	/**
	 * 
	 * @return The inspection date/time this toy was inspected.
	 */
	LocalDateTime getInspectionDateTime();

	/**
	 * The first circuit of the toy.
	 * 
	 * @return The first circuit of the toy.
	 */
	Circuit getCircuit1();

	/**
	 * The second circuit of the toy.
	 * 
	 * @return The second circuit of the toy.
	 */
	Circuit getCircuit2();

	// **********Mutators************
	/**
	 * Sets the ToyID in the class.
	 * 
	 * @param pToyID
	 *            The Toy's ID to set.
	 */
	void setToyID(int pToyID);

	// **********Methods************
	/**
	 * Sets the inspector of the toy.
	 * 
	 * @param pInspector
	 *            The inspector's unique identifier/name.
	 */
	void setInspector(String pInspector);

	/**
	 * Sets the inspection date/time of this toy.
	 * 
	 * @param pInspectionDateTime
	 *            The date/time of inspection.
	 */
	void setInspectionDateTime(LocalDateTime pInspectionDateTime);

	/**
	 * Sets the first circuit of the toy.
	 * 
	 * @param pCircuit1
	 *            The first circuit of the toy.
	 */
	void setCircuit1(Circuit pCircuit1);

	/**
	 * Sets the second circuit of the toy.
	 * 
	 * @param pCircuit2
	 *            The second circuit of the toy.
	 */
	void setCircuit2(Circuit pCircuit2);
}
