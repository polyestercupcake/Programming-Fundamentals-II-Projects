package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This class will contain all information needed for inspecting toys from
 * Kembell Berkstresser Enterprises, including database functionality.
 * This class inplements the IToy and IPermanentStorage interfaces.
 * @author Tanner's Laptop
 *
 */
public class Toy implements IToy, IPermanentStorage {
	/**
	 * The ID number of the toy. Restricted to six numbers.
	 */
	private int toyID;
	/**
	 * The name of the toy inspector at Kembell Berkstresser Enterprises.
	 */
	private String inspector;
	/**
	 * Holds the exact date and time when the "Save" button is clicked.
	 * Not time zone specific.
	 */
	private LocalDateTime inspectionDateTime;
	/**
	 * This is an instance field of the Circuit class. This will contains the values needed for Circuit 1 of the Toy.
	 */
	private Circuit circuit1 = new Circuit(1);
	/**
	 * This is an instance field of the Circuit class. This will contains the values needed for Circuit 2 of the Toy.
	 */
	private Circuit circuit2 = new Circuit(2);

	@Override
	public final int getToyID() {
		// TODO Auto-generated method stub
		return toyID;
	}

	@Override
	public final String getInspector() {
		// TODO Auto-generated method stub
		return inspector;
	}

	@Override
	public final LocalDateTime getInspectionDateTime() {
		// TODO Auto-generated method stub
		return inspectionDateTime;
	}

	@Override
	public final Circuit getCircuit1() {
		// TODO Auto-generated method stub
		return circuit1;
	}

	@Override
	public final Circuit getCircuit2() {
		// TODO Auto-generated method stub
		return circuit2;
	}

	@Override
	public final void setToyID(final int pToyID) {
		toyID = pToyID;
		circuit1.setToyID(pToyID);
		circuit2.setToyID(pToyID);

	}

	@Override
	public final void setInspector(final String pInspector) {
		inspector = pInspector;

	}

	@Override
	public final void setInspectionDateTime(final LocalDateTime pInspectionDateTime) {
		inspectionDateTime = pInspectionDateTime;

	}

	@Override
	public final void setCircuit1(final Circuit pCircuit1) {
		circuit1 = pCircuit1;
		circuit1.setToyID(toyID);

	}

	@Override
	public final void setCircuit2(final Circuit pCircuit2) {
		circuit2 = pCircuit2;
		circuit2.setToyID(toyID);
		

	}

	/**
	 * This method is the back-end code that saves toy information to the database.
	 */
	@Override
	public final void save() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(toyID));
			params.add(new Parameter<String>(inspector));
			params.add(new Parameter<LocalDateTime>(inspectionDateTime));
			
			db.executeSql("usp_SaveToy", params);
			
			circuit1.save();
			circuit2.save();
		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong in saving the Toy.");
		}
		
	}

	/**
	 * This method is the back-end code that deletes toy information from the database.
	 */
	@Override
	public final void delete() {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(toyID));
		
			db.executeSql("usp_DeleteToy", params);
			
			//circuits automatically delete themselves
	} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in deleting the Toy.");
	}
}

	
	/**
	 * This method is the back-end code that loads toy information from the database.
	 */
	@Override
	public final void load(final int... id) {
		//id will be an array where the first element is id[0], the second (if there) is id[1]...
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(id[0]));
			
			ResultSet rsToy = db.getResultSet("usp_LoadToy", params);
			if (rsToy.next()) {
				toyID = rsToy.getInt("ToyID");
				inspector = rsToy.getString("Inspector");
				inspectionDateTime = rsToy.getTimestamp("InspectionDateTime").toLocalDateTime();
				circuit1.load(toyID, 1);
				circuit2.load(toyID, 2);
			} else {
				throw new IllegalArgumentException("This toy is not found in our database.");
			}
			
		//circuits automatically delete themselves
	} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in loading the Toy.");
		}	
	}
}
