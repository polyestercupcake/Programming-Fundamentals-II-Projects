package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import db.Database;
import db.Parameter;

/**
 * 
 * @author 217056
 *
 */
public class Person {
	
	//FIELDS
	/**
	 * 
	 */
	private int studentID;
	/**
	 * 
	 */
	private List<Exercise> exercises;
	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String lastName;
	/**
	 * 
	 */
	private double height;
	/**
	 * 
	 */
	private double weight;
	/**
	 * 
	 */
	private Gender gender;
	/**
	 * 
	 */
	private LocalDate birthdate;
	
	//METHODS
	/**
	 * @return the studentID
	 */
	public final int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public final void setStudentID(final int studentID) {
		this.studentID = studentID;
	}
	/**
	 * 
	 * @return the exercises
	 */
	public final List<Exercise> getExercises() {
		return exercises;
	}
	/**
	 * @return the exercises
	 */
	public final List<ExerciseStrength> getExercisesStrength() {
		//lambda: stream something, filter something, collect and finish
		return exercises
				.stream()
				//filter on condition that e is of type ExerciseStrength
				.filter(e -> e instanceof ExerciseStrength)
				.map(e -> (ExerciseStrength) e)
				//collect back up and get back into a list
				.collect(Collectors.toList());
	}
	/**
	 * @return the exercises
	 */
	public final List<ExerciseAerobic> getExercisesAerobic() {
		//lambda: stream something, filter something, collect and finish
		return exercises
				.stream()
				//filter on condition that e is of type ExerciseAerobic
				.filter(e -> e instanceof ExerciseAerobic)
				.map(e -> (ExerciseAerobic) e)
				//collect back up and get back into a list
				.collect(Collectors.toList());
	}
	/**
	 * @param exercises the exercises to set
	 */
	public final void setExercises(final List<Exercise> exercises) {
		this.exercises = exercises;
	}
	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the height
	 */
	public final double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public final void setHeight(final double height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public final double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public final void setWeight(final double weight) {
		this.weight = weight;
	}
	/**
	 * @return the gender
	 */
	public final Gender getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public final void setGender(Gender genderr) {
		this.gender = genderr;
	}
	/**
	 * @return the birthdate
	 */
	public final LocalDate getBirthdate() {
		return birthdate;
	}
	/**
	 * @param birthdate the birthdate to set
	 */
	public final void setBirthdate(final LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	/**
	 * 
	 * @return
	 */
	public final Period getAge() {
		//year 2019...born 1999 = 20...I'm not 20
		birthdate.getYear();
		LocalDate.now().getYear();
		return Period.between(birthdate, LocalDate.now());
	}
	/**
	 * 
	 * @param studentID
	 */
	public final void load(final int studentID) {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			
			ResultSet rsStudent = db.getResultSet("Exercise.usp_GetPerson", params);
			if (rsStudent.next()) {
				this.studentID = rsStudent.getInt("studentID");
				firstName = rsStudent.getString("firstName");
				lastName = rsStudent.getString("lastName");
				gender = Gender.valueOf(rsStudent.getString("gender").toUpperCase());
				height = rsStudent.getDouble("height");
				weight = rsStudent.getDouble("weight");
				birthdate = LocalDate.parse(rsStudent.getString("birthdate"));
			} else {
				throw new IllegalArgumentException("This student is not found in our database.");
			}
			
	} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in loading the student.");
		}	
	}
	/**
	 * 
	 */
	public final void save() {
		
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			params.add(new Parameter<String>(firstName));
			params.add(new Parameter<String>(lastName));
			params.add(new Parameter<Double>(height));
			params.add(new Parameter<Double>(weight));
			params.add(new Parameter<String>(gender.toString()));
			params.add(new Parameter<LocalDate>(birthdate));
			
			db.executeSql("Exercise.usp_SavePerson", params);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in saving the student.");
		}
	}
	/**
	 * 
	 */
	public final void delete() {
		
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		
		try {
			//add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			db.executeSql("Exercise.usp_DeletePerson", params);
			
	} catch (SQLException e) {
		throw new RuntimeException("Something went wrong in deleting the student's information.");
	}
}
	
}
