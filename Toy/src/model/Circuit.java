package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class provides all the business operations for my app.
 * Circuit class implements the ICircuit and IPermanent interfaces.
 * @author 217056
 */
public class Circuit implements ICircuit, IPermanentStorage {
	/**
	 * This field holds the ID number of each toy.
	 */
	private int toyID;
	/**
	 * This field holds the ID of which circuit we are using. Either 1 or 2.
	 */
	private int circuitID;
	/**
	 * This field holds the voltage of either circuit 1 or circuit 2 once it has been decided.
	 */
	private double voltage;
	/**
	 * This field holds the resistance of either circuit 1 or circuit 2 once it has been decided.
	 */
	private double resistance;
	/**
	 * This field holds the manufacturing location of the toy in either circuit 1 or circuit 2.
	 */
	private String manufactureLocation;

	/**
	 * This constructor sets the proper ID to the proper circuit of each toy.
	 * @param pCircuitID either circuit 1 or circuit 2.
	 */
	public Circuit(final int pCircuitID) {
		circuitID = pCircuitID;
	}

	/*
	 * @see model.ICircuit#getToyID()
	 */
	@Override
	public final int getToyID() {
		return toyID;
	}

	/*
	 * @see model.ICircuit#getCircuitID()
	 */
	@Override
	public final int getCircuitID() {
		return circuitID;
	}

	/* 
	 * @see model.ICircuit#getVoltage()
	 */
	@Override
	public final double getVoltage() {
		return voltage;
	}

	/*
	 * @see model.ICircuit#getAmperage()
	 */
	@Override
	public final double getAmperage() {
		return voltage / resistance;
	}

	/*
	 * @see model.ICircuit#getResistance()
	 */
	@Override
	public final double getResistance() {
		return resistance;
	}

	/*
	 * @see model.ICircuit#getManufactureLocation()
	 */
	@Override
	public final String getManufactureLocation() {
		return manufactureLocation;
	}

	/* 
	 * @see model.ICircuit#setToyID(int)
	 */
	@Override
	public final void setToyID(final int pToyID) {
		toyID = pToyID;

	}

	/*
	 * @see model.ICircuit#setCircuitID(int)
	 */
	@Override
	public final void setCircuitID(final int pCircuitID) {
		circuitID = pCircuitID;

	}

	/* 
	 * @see model.ICircuit#setVoltage(double)
	 */
	@Override
	public final void setVoltage(final double pVoltage) {
		voltage = pVoltage;
		if (pVoltage < 0) {
			throw new IllegalArgumentException("Voltage " 
		+ "must be greater than or equal to 0. \nPlease try again.");
		}
	}

	/*
	 * @see model.ICircuit#setResistance(double)
	 */
	@Override
	public final void setResistance(final double pResistance) {
		resistance = pResistance;
		if (pResistance < 0) {
			throw new IllegalArgumentException("Resistance " 
		+ "must be greater than or equal to 0. \nPlease try again.");
		}
	}

	/*
	 * @see model.ICircuit#setManufactureLocation(java.lang.String)
	 */
	@Override
	public final void setManufactureLocation(final String pManufactureLocation) {
		manufactureLocation = pManufactureLocation;
	}
	
	@Override
	public final void save() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			// toyID, circuitID, manufactureLocation, voltage, amperage, resistance
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(toyID));
			params.add(new Parameter<Integer>(circuitID));
			params.add(new Parameter<String>(manufactureLocation));
			params.add(new Parameter<Double>(voltage));
			params.add(new Parameter<Double>(getAmperage()));
			params.add(new Parameter<Double>(resistance));
			
			db.executeSql("usp_SaveCircuit", params);
			
		} catch (SQLException e) {
//			throw new RuntimeException("Something went wrong in Circuit.");
			e.printStackTrace();
		}
		
	}

	@Override
	public final void delete() {
		throw new UnsupportedOperationException("Don't call delete from circuit. Call from Toy.");
	}

	@Override
	public final void load(final int... id) {
		//id will be an array where the first element is id[0], the second (if there) is id[1]...
				Database db = new Database();
				@SuppressWarnings("rawtypes")
				List<Parameter> params = new ArrayList<>();
				
				try {
					//add parameters in the required order (see campusweb cheatsheet)
					params.add(new Parameter<Integer>(id[0]));
					params.add(new Parameter<Integer>(id[1]));
					
					ResultSet rsCircuit = db.getResultSet("usp_LoadCircuit", params);
					
					if (rsCircuit.next()) {
						toyID = rsCircuit.getInt("ToyID");
						circuitID = rsCircuit.getInt("CircuitID");
						voltage = rsCircuit.getDouble("Voltage");
						resistance = rsCircuit.getDouble("Resistance");
						manufactureLocation = rsCircuit.getString("ManufactureLocation");
						
					} else {
						throw new IllegalArgumentException("This toy is not found in our database.");
					}
					
			//circuits automatically delete themselves
			} catch (SQLException e) {
				throw new RuntimeException("Something went wrong in loading the Toy.");
		}			
	}
}
