package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import db.Database;
import db.Parameter;

/**
 * This class is the business processes for all actions dealing with the
 * student's personal information, including their housed exercises.
 * 
 * @author 217056
 */
public class Person {

	// FIELDS
	/**
	 * What is used primarily by the program to identify the student.
	 */
	private int studentID;
	/**
	 * Contains a list or exercises the student has according to their student ID.
	 */
	private List<Exercise> exercises = new ArrayList<>();
	/**
	 * First name of a student.
	 */
	private String firstName;
	/**
	 * Last name of a student.
	 */
	private String lastName;
	/**
	 * Height of a student.
	 */
	private double height;
	/**
	 * Weight of a student.
	 */
	private double weight;
	/**
	 * Gender of a student. Created with the enum class. Choices include: Male,
	 * Female, Unspecified.
	 */
	private Gender gender;
	/**
	 * DOB for a student.
	 */
	private LocalDate birthdate;

	// METHODS
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
	 * Gets all the exercises a student has.
	 * 
	 * @return the exercises
	 */
	public final List<Exercise> getExercises() {
		return exercises;
	}

	/**
	 * Gets all the strength categorized exercises a student has.
	 * 
	 * @return the exercises
	 */
	public final List<ExerciseStrength> getExercisesStrength() {
		// lambda: stream something, filter something, collect and finish
		return exercises.stream()
				// filter on condition that e is of type ExerciseStrength
				.filter(e -> e instanceof ExerciseStrength).map(e -> (ExerciseStrength) e)
				// collect back up and get back into a list
				.collect(Collectors.toList());
	}

	/**
	 * Gets all the aerobic categorized exercises a student has.
	 * 
	 * @return the exercises
	 */
	public final List<ExerciseAerobic> getExercisesAerobic() {
		// lambda: stream something, filter something, collect and finish
		return exercises.stream()
				// filter on condition that e is of type ExerciseAerobic
				.filter(e -> e instanceof ExerciseAerobic).map(e -> (ExerciseAerobic) e)
				// collect back up and get back into a list
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
	public final void setGender(final Gender gender) {
		this.gender = gender;
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
	 * Returns the age of the user after their student account is loaded.
	 * 
	 * @return the age of the user.
	 */
	public final int getAge() {
		// year 2019...born 1999 = 20...I'm not 20
		Period p = Period.between(birthdate, LocalDate.now());
		return p.getYears();
	}

	/**
	 * Loads the student's personal information from the database, assuming their ID
	 * has been saved as an account.
	 * 
	 * @param studentID need this to correctly identify one student from another.
	 */
	public final void load(final int studentID) {
		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			// add parameters in the required order (see campusweb cheatsheet)
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
				exercises.clear();
				loadAerobicExercise();
				loadStrengthExercise();
			} else {
				throw new IllegalArgumentException("This student is not found in our database.");
			}

		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong in loading the student.");
		}
	}

	/**
	 * This will load the student's aerobic exercises from the database.
	 */
	public void loadAerobicExercise() {

		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));

			ResultSet rsStudent = db.getResultSet("Exercise.usp_GetAerobicExercisesByPerson", params);
			while (rsStudent.next()) {
				ExerciseAerobic exAero = new ExerciseAerobic();
				exAero.setStudentID(rsStudent.getInt("studentID"));
				exAero.setExerciseDate(LocalDate.parse(rsStudent.getString("exerciseDate")));
				exAero.setExerciseName(rsStudent.getString("exerciseName"));
				exAero.setExerciseDuration(Duration.ofSeconds(Integer.parseInt(
						rsStudent.getString("exerciseSeconds"))));
				exAero.setMaxHeartRate(rsStudent.getInt("maxHeartRate"));
				exAero.setAverageHeartRate(rsStudent.getInt("averageHeartRate"));
				exAero.setDistance(rsStudent.getDouble("distance"));

				exercises.add(exAero);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in loading the aerobic exercise.");
		}

	}

	/**
	 * This will load the student's strength exercises from the database.
	 */
	public void loadStrengthExercise() {

		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			ExerciseStrength exStr = new ExerciseStrength();

			ResultSet rsStudent = db.getResultSet("Exercise.usp_GetStrengthExercisesByPerson", params);
			while (rsStudent.next()) {
				exStr.setStudentID(rsStudent.getInt("studentID"));
				exStr.setExerciseDate(LocalDate.parse(rsStudent.getString("exerciseDate")));
				exStr.setExerciseName(rsStudent.getString("exerciseName"));
				exStr.setExerciseDuration(Duration.ofSeconds(Integer.parseInt(
						rsStudent.getString("exerciseSeconds"))));
				exStr.setSets(rsStudent.getInt("sets"));
				exStr.setReps(rsStudent.getInt("reps"));
				exStr.setWeightLifted(rsStudent.getDouble("weightLifted"));

				exercises.add(exStr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong in loading the aerobic exercise.");
		}
	}

	/**
	 * Saves a student's personal information to the database once all categories of
	 * information necessary are filled. This is NOT for saving exercises.
	 */
	public final void save() {

		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			// add parameters in the required order (see campusweb cheatsheet)
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
	 * This will remove a student's personal information from the database.
	 */
	public final void delete() {

		Database db = new Database();
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		try {
			// add parameters in the required order (see campusweb cheatsheet)
			params.add(new Parameter<Integer>(studentID));
			db.executeSql("Exercise.usp_DeletePerson", params);

		} catch (SQLException e) {
			throw new RuntimeException("Something went wrong in deleting the student's information.");
		}
	}
}
