/*
 * PURPOSE: to calculate Ohm's Law values of a circuit and return them to the user
 * define constant fields
 * define variables
 * instance of Circuit class
 * display greeting message
 * ask user if they would like to calculate voltage, amperage, or resistance
 * save userChoice
 * while loop for if user enters something other than 1,2, or 3. Error proofing!
 * 
 * if voltage:
 * ask for amperage
 * save
 * ask for resistance
 * save 
 * set voltage = amperage x resistance
 * display results
 * 
 * if amperage:
 * ask for voltage
 * save 
 * ask for resistance
 * save
 * set amperage = voltage  / resistance 
 * display results
 * 
 * if resistance:
 * ask for voltage
 * save
 * ask for amperage
 * save
 * set resistance = voltage / amperage
 * display results
 * 
 * create do while loop for if user wants to restart program once they are finished
 * enter "Y" or "y" to restart and anything else to quit
 * closing message
 * 
 * close keyboard
 *
 */

package ui;

import java.util.Scanner;
import bp.Circuit;

/**
 * This is my presentation class that presents this lovely program to the user.
 * I used an abstract class because we have implemented the Circuit class with
 * the ICiruit interface and created an instance of the Circuit class in this
 * class.
 * 
 * @author Tanner's Laptop
 */
abstract class Main {

	// declare constant fields
	/**
	 * Voltage will always be represented as 1 to the user.
	 */
	static final int CALCULATE_VOLTAGE = 1;
	/**
	 * Amperage will always be represented as 2 to the user.
	 */
	static final int CALCUALTE_AMPERAGE = 2;
	/**
	 * Resistance will always be represented as 3 to the user.
	 */
	static final int CALCULATE_RESISTANCE = 3;

	/**
	 * 
	 * @param args that should be passed
	 */
	public static void main(final String[] args) {

		Scanner keyboard = new Scanner(System.in);

		// instance of Circuit object
		Circuit myCircuit = new Circuit();

		// declare variables
		int userChoice;
		String startOver;

		// Greeting message and purpose statement
		System.out.println("Hello! This program is designed to " + "calculate "
				+ "voltage, amperage, \nor resistance of a " + "circuit given two of the others are known.");

		do {
			System.out.println("\nWould you like to calculate: " + "\n1) voltage, " + "2) amperage, or 3) resistance?");
			userChoice = keyboard.nextInt();

			while (!(userChoice == CALCULATE_VOLTAGE || userChoice == CALCUALTE_AMPERAGE
					|| userChoice == CALCULATE_RESISTANCE)) {
				System.out.println("ERROR: Please enter either 1, 2, or 3" + " in order to calculate a circuit.");
				userChoice = keyboard.nextInt();
			}

			// if user chooses to calculate voltage
			if (userChoice == CALCULATE_VOLTAGE) {
				// ask for amperage
				System.out.println("What is the amperage?");
				// save amperage
				myCircuit.setAmperage(keyboard.nextDouble());
				// ask for resistance
				System.out.println("What is the resistance?");
				// save resistance
				myCircuit.setResistance(keyboard.nextDouble());
				// calculate voltage
				myCircuit.calculateVoltage();

				// output calculated voltage
				System.out.println("With a given amperage of " + myCircuit.getAmperage()
						+ " amps and a given resistance of " + myCircuit.getResistance() + " ohms, the voltage"
						+ " of the circuit is " + myCircuit.getVoltage() + " volts.");
			}

			// if user chooses to calculate amperage
			if (userChoice == CALCUALTE_AMPERAGE) {
				// ask for voltage
				System.out.println("What is the voltage?");
				// save voltage
				myCircuit.setVoltage(keyboard.nextDouble());
				// ask for resistance
				System.out.println("What is the resistance?");
				// save resistance
				myCircuit.setResistance(keyboard.nextDouble());
				// calculate amperage
				myCircuit.calculateAmperage();

				// output calculated amperage
				System.out.println("With a given voltage of " + myCircuit.getVoltage() + " volts and a resistance of "
						+ myCircuit.getResistance() + " ohms, the " + "amperage of the circuit is "
						+ myCircuit.getAmperage() + " amps.");
			}

			// if user chooses to calculate resistance
			if (userChoice == CALCULATE_RESISTANCE) {
				// ask for voltage
				System.out.println("What is the voltage?");
				// save voltage
				myCircuit.setVoltage(keyboard.nextDouble());
				// ask for amperage
				System.out.println("What is the amperage?");
				// save amperage
				myCircuit.setAmperage(keyboard.nextDouble());
				// calculate resistance
				myCircuit.calculateResistance();

				// output calculated resistance
				System.out.println("With a given voltage of " + myCircuit.getVoltage()
						+ " volts and a given amperage of " + myCircuit.getAmperage()
						+ " amps, the resistance of the circuit is " + myCircuit.getResistance() + " ohms.");
			}

			// ask if they want to start over
			System.out.println("Thanks for being a part of this amazing program! " + "Would you like to start over? \n"
					+ "\nEnter Y to restart or anything else to close the program.");
			startOver = keyboard.next();

			} while (startOver.equals("Y") || startOver.equals("y"));

		// close program
		System.out.println("Goodbye!");

		keyboard.close();
	}

}
