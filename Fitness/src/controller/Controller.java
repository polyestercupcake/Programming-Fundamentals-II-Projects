package controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import model.Exercise;
import model.ExerciseAerobic;
import model.ExerciseStrength;
import model.Gender;
import model.Person;

/**
 * This class controls all functions with the UI down to the finest detail.
 * This is essentially what the user sees when the program is launched.
 * This class calls methods from the Person class as well as the ExerciseAerobic and
 * ExerciseStrength classes.
 * @author Tanner's Laptop
 *
 */
public class Controller {
	// FIELDS
	/**
	 * This field is used for the ChangeListener added to restrict the Exercise Duration
	 * to 6 digits only.
	 */
	static final int LIMIT = 6;
	/**
	 * Picture used to represent the female avatar.
	 */
	@FXML private ImageView womanAvatar;
	/**
	 * Picture used to represent the male avatar.
	 */
	@FXML private ImageView manAvatar;
	/**
	 * Picture used to represent the unspecified avatar.
	 */
	@FXML private ImageView unspecifiedAvatar;
	/**
	 * The tab pane that displays all the student's execises
	 */
	@FXML private TabPane tabPane;
	/**
	 * The tab inside the tab pane that displays the student's aerobic exercises.
	 */
	@FXML private Tab aerobicTab;
	/**
	 * The tab inside the tab pane that displays the student's strength exercises.
	 */
	@FXML private Tab strengthTab;
	// PERSONAL INFORMATION
	/**
	 * Text field containing the student ID of the student.
	 */
	@FXML private TextField txtStudentID;
	/**
	 * Text field containing the first name of the student.
	 */
	@FXML private TextField txtFirstName;
	/**
	 * Text field containing the last name of the student.
	 */
	@FXML private TextField txtLastName;
	/**
	 * Combo box that allows the student to choose their gender. Choices of male,
	 * female, and unspecified are all options.
	 */
	@FXML private ComboBox<Gender> choiceGender;
	/**
	 * Text field containing the height of the student.
	 */
	@FXML private TextField txtHeight;
	/**
	 * Text field containing the weight (in lbs) of the student.
	 */
	@FXML private TextField txtWeight;
	/**
	 * Date Picker text field that picks the birth date of the given student.
	 */
	@FXML private DatePicker txtBirthdate;
	/**
	 * Button that will load the student's information into the appropriate text
	 * fields, given a student ID.
	 */
	@FXML private Button btnLoadStudent;
	/**
	 * Button that will save a new student's information or maybe their updated
	 * information to the database.
	 */
	@FXML private Button btnSaveStudent;
	/**
	 * Button that will delete a student's account from the database.
	 */
	@FXML private Button btnDeleteStudent;
	// EXERCISES
	/**
	 * Label representing the date of the exercise.
	 */
	@FXML private Label lblDate;
	/**
	 * Label representing the name of the exercise.
	 */
	@FXML private Label lblExerciseName;
	/**
	 * Label representing the duration of the exercise.
	 */
	@FXML private Label lblDuration;
	// AEROBIC
	/**
	 * Depending on which radio button is selected, this label will either be
	 * labeled as aerobic exercise max heart rate or strength exercise sets.
	 */
	@FXML private Label lblMaxHRorSets;
	/**
	 * Depending on which radio button is selected, this label will either be
	 * labeled as aerobic exercise average heart rate or strength exercise reps.
	 */
	@FXML private Label lblAvgHRorReps;
	/**
	 * Depending on which radio button is selected, this label will either be
	 * labeled as aerobic exercise distance or strength exercise weight lifted.
	 */
	@FXML private Label lblDistanceorWeightLifted;
	/**
	 * Toggle group containing each of the two radio buttons: radioAerobic and
	 * radioStrength.
	 */
	@FXML private ToggleGroup exerciseGroup;
	/**
	 * Radio button that will direct you towards aerobic exercises.
	 */
	@FXML private RadioButton radioAerobic;
	/**
	 * Radio button that will direct you towards strength exercises.
	 */
	@FXML private RadioButton radioStrength;
	/**
	 * Date Picker text field containing the date of the exercise.
	 */
	@FXML private DatePicker txtExerciseDate;
	/**
	 * Text field containing the name of the exercise.
	 */
	@FXML private TextField txtExerciseName;
	/**
	 * Text field containing the duration of the exercise.
	 */
	@FXML private TextField txtExerciseDuration;
	/**
	 * Depending on which radio button is selected, this text field will either be
	 * directed towards aerobic exercise max heart rate or strength exercise sets.
	 */
	@FXML private TextField txtMaxHRorSets;
	/**
	 * Depending on which radio button is selected, this text field will either be
	 * directed towards aerobic exercise average heart rate or strength exercise
	 * reps.
	 */
	@FXML private TextField txtAvgHRorReps;
	/**
	 * Depending on which radio button is selected, this text field will either be
	 * directed towards aerobic exercise distance or strength exercise weight
	 * lifted.
	 */
	@FXML private TextField txtDistanceorWeightLifted;
	/**
	 * The TableView that will contain the information from each exercise the
	 * student has added.
	 */
	@FXML private TableView<ExerciseAerobic> aerobicTable;
	/**
	 * Column 1 of the Aerobic TableView.
	 */
	@FXML private TableColumn<Exercise, LocalDate> aerobicDateC1;
	/**
	 * Column 2 of the Aerobic TableView.
	 */
	@FXML private TableColumn<Exercise, String> aerobicNameC2;
	/**
	 * Column 3 of the Aerobic TableView.
	 */
	@FXML private TableColumn<Exercise, Long> aerobicDurationC3;
	/**
	 * Column 4 of the Aerobic TableView.
	 */
	@FXML private TableColumn<ExerciseAerobic, Integer> aerobicMaxHRC4;
	/**
	 * Column 5 of the Aerobic TableView.
	 */
	@FXML private TableColumn<ExerciseAerobic, Integer> aerobicAvgHRC5;
	/**
	 * Column 6 of the Aerobic TableView.
	 */
	@FXML private TableColumn<ExerciseAerobic, Double> aerobicDistanceC6;
	/**
	 * The TableView that will contain the information from each exercise the
	 * student has added.
	 */
	@FXML private TableView<ExerciseStrength> strengthTable;
	/**
	 * Column 1 of the Strength TableView.
	 */
	@FXML private TableColumn<Exercise, LocalDate> strengthDateC1;
	/**
	 * Column 2 of the Strength TableView.
	 */
	@FXML private TableColumn<Exercise, String> strengthNameC2;
	/**
	 * Column 3 of the Strength TableView.
	 */
	@FXML private TableColumn<Exercise, Long> strengthDurationC3;
	/**
	 * Column 4 of the Strength TableView.
	 */
	@FXML private TableColumn<ExerciseStrength, Integer> strengthSetsC4;
	/**
	 * Column 5 of the Strength TableView.
	 */
	@FXML private TableColumn<ExerciseStrength, Integer> strengthRepsC5;
	/**
	 * Column 6 of the Strength TableView.
	 */
	@FXML private TableColumn<ExerciseStrength, Double> strengthWeightLiftedC6;
	/**
	 * Button that will add exercises to the student's account.
	 */
	@FXML private Button btnAddExercise;
	/**
	 * Button that will remove exercises from the student's account.
	 */
	@FXML private Button btnRemoveExercise;
	/**
	 * Check box that allows a dark mode to be toggled on the scene.
	 */
	@FXML private CheckBox chkbxDarkMode;
	/**
	 * Label specifying the loaded student's age
	 */
	@FXML private Label lblUserAge;
	/**
	 * Instance of the Person class, dealing with the student's Personal
	 * Information.
	 */
	private Person myPerson = new Person();
	/**
	 * Instance of the ExerciseAerobic class, which extends Exercise.
	 */
	private ExerciseAerobic exerciseAerobic = new ExerciseAerobic();
	/**
	 * Instance of the ExerciseStrength class, which extends Exercise.
	 */
	private ExerciseStrength exerciseStrength = new ExerciseStrength();
	// METHODS
	/**
	 * Sets startup information in the program when the program is ran.
	 */
	public final void initialize() {
// cannot edit the contents of the table view
		aerobicTable.setEditable(false);
		strengthTable.setEditable(false);
// import Aerobic Table
		aerobicDateC1.setCellValueFactory(new PropertyValueFactory<Exercise, LocalDate>("exerciseDate"));
		aerobicNameC2.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
		aerobicDurationC3.setCellValueFactory(new PropertyValueFactory<Exercise, Long>("exerciseDurationMinutes"));
		aerobicMaxHRC4.setCellValueFactory(new PropertyValueFactory<ExerciseAerobic, Integer>("maxHeartRate"));
		aerobicAvgHRC5.setCellValueFactory(new PropertyValueFactory<ExerciseAerobic, Integer>("averageHeartRate"));
		aerobicDistanceC6.setCellValueFactory(new PropertyValueFactory<ExerciseAerobic, Double>("distance"));
//import Strength Table
		strengthDateC1.setCellValueFactory(new PropertyValueFactory<Exercise, LocalDate>("exerciseDate"));
		strengthNameC2.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
		strengthDurationC3.setCellValueFactory(new PropertyValueFactory<Exercise, Long>("exerciseDurationMinutes"));
		strengthSetsC4.setCellValueFactory(new PropertyValueFactory<ExerciseStrength, Integer>("sets"));
		strengthRepsC5.setCellValueFactory(new PropertyValueFactory<ExerciseStrength, Integer>("reps"));
		strengthWeightLiftedC6.setCellValueFactory(new PropertyValueFactory<ExerciseStrength, Double>("weightLifted"));
//disables all buttons until appropriate criteria is given
		btnSaveStudent.setDisable(true);
		btnLoadStudent.setDisable(true);
		btnDeleteStudent.setDisable(true);
//adds all gender values
		choiceGender.getItems().addAll(Gender.values());
//sets invisible all avatars
		womanAvatar.setVisible(false);
		manAvatar.setVisible(false);
		unspecifiedAvatar.setVisible(false);
//sets text of exercise labels
		lblDate.setText("Date");
		lblExerciseName.setText("Name of Exercise");
		lblDuration.setText("Duration");
		txtExerciseDuration.setPromptText("(HH:MM:SS)");
//sets text of aerobic exercise labels and prompt text of text fields as needed
		exerciseGroup.selectToggle(radioAerobic);
		lblMaxHRorSets.setText("Max Heart Rate");
		txtMaxHRorSets.setPromptText("BPM");
		lblAvgHRorReps.setText("Avg Heart Rate");
		txtAvgHRorReps.setPromptText("BPM");
		lblDistanceorWeightLifted.setText("Distance");
		txtDistanceorWeightLifted.setPromptText("(miles)");
//adds listener to all double text fields to stop anything other than numbers from entering
		addDoubleListener(txtStudentID);
		addDoubleListener(txtHeight);
		addDoubleListener(txtWeight);
		addDoubleListener(txtExerciseDuration);
		addDoubleListener(txtMaxHRorSets);
		addDoubleListener(txtAvgHRorReps);
		addDoubleListener(txtDistanceorWeightLifted);
//adds listener to all String text fields
		addStringListener(txtFirstName);
		addStringListener(txtLastName);
		addStringListener(txtExerciseName);
//sets the radio buttons to the toggleGroup
		radioAerobic.setToggleGroup(exerciseGroup);
		radioStrength.setToggleGroup(exerciseGroup);
//sets the visible table according to the radio button
		exerciseGroup.selectToggle(radioAerobic);
		tabPane.getSelectionModel().select(aerobicTab);
//disable add/remove buttons
		btnAddExercise.setDisable(true);
		btnRemoveExercise.setDisable(true);
	}
	/**
	 * Changes the names of the exercise labels as the different types of exercises are
	 * toggled.
	 */
	@FXML private void labelChange() {
		if (tabPane.getSelectionModel().getSelectedItem().equals(aerobicTab)) {
			lblMaxHRorSets.setText("Max Heart Rate");
			txtMaxHRorSets.setPromptText("BPM");
			lblAvgHRorReps.setText("Avg Heart Rate");
			txtAvgHRorReps.setPromptText("BPM");
			lblDistanceorWeightLifted.setText("Distance");
			txtDistanceorWeightLifted.setPromptText("(miles)");
		} else {
			lblMaxHRorSets.setText("Sets");
			txtMaxHRorSets.setPromptText("");
			lblAvgHRorReps.setText("Reps");
			txtAvgHRorReps.setPromptText("");
			lblDistanceorWeightLifted.setText("Weight Lifted");
			txtDistanceorWeightLifted.setPromptText("lbs");
		}
	}
	/**
	 * String Listener for String TextFields to only accept characters for input.
	 * @param textField is a general TextField and this method will be called by all
	 * TextFields with String input.
	 */
	private void addStringListener(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				// HOW IT WORKS
				// if newValue does not match \w (all letters and numbers) , then whitespace ,
				// then all letters and numbers
				// set newValue text to replace anything that is not in the set below (all
				// letters, numbers, and whitespace)
				if (!newValue.matches("\\w*( )?\\w*")) {
					textField.setText(newValue.replaceAll("[^\\w ]", ""));
				}
			}
		});
	}
	/**
	 * Double Listener for TextFields to only accept numbers for input.
	 * @param textField is a general TextField and this method will be called by all
	 * TextFields that require numerical input.
	 */
	private void addDoubleListener(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				// HOW IT WORKS
				// if newValue does not match \d (digits 0-9), a period, and \d (digits 0-9)
				// set newValue to replace anything that is not in the set below (\d)
				if (!newValue.matches("\\d*(\\.)?\\d*")) {
					textField.setText(newValue.replaceAll("[^\\d.]", ""));
				}
				// sets Exercise Duration max length to 6 characters for input
				if (textField.equals(txtExerciseDuration)) {
				if (textField.getText().length() >= LIMIT) {
					textField.setText(textField.getText().substring(0, LIMIT));
				}}
			}
		});
	}
	/**
	 * Clears personal information fields after a save or delete.
	 */
	@FXML private void clearPersonalInfo() {
		txtStudentID.clear();
		txtFirstName.clear();
		txtLastName.clear();
		choiceGender.setValue(null);
		txtHeight.clear();
		txtWeight.clear();
		txtBirthdate.setValue(null);
		txtStudentID.requestFocus();
	}
	/**
	 * This method is an error-checking methodology that will disable the "Save"
	 * button until all information is entered or selected in the form.
	 */
	@FXML private void disableSave() {
		if (txtStudentID.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty()
				|| txtHeight.getText().isEmpty() || txtWeight.getText().isEmpty() || txtBirthdate.getValue() == null) {
			btnSaveStudent.setDisable(true);
		} else {
			btnSaveStudent.setDisable(false);
		}
	}
	/**
	 * This method is an error-checking methodology that will disable the "Load
	 * Student" button until a Student ID has been entered to be loaded if it is a
	 * valid ID number.
	 */
	@FXML private void disableLoadStudent() {
		if (txtStudentID.getText().isEmpty()) {
			btnLoadStudent.setDisable(true);
		} else {
			btnLoadStudent.setDisable(false);
		}
	}
	/**
	 * This method is an error-checking methodology that will disable the "Delete
	 * Student" button until a Student ID has been entered to be potentially
	 * deleted.
	 */
	@FXML private void disableDeleteStudent() {
		if (txtStudentID.getText().isEmpty()) {
			btnDeleteStudent.setDisable(true);
		} else {
			btnDeleteStudent.setDisable(false);
		}
	}
	/**
	 * Disables the add and remove exercise buttons until they are ready.
	 */
	@FXML private void disableAddRemoveButtons() {
		if (aerobicTable.getSelectionModel().getSelectedItem() != null ||
				strengthTable.getSelectionModel().getSelectedItem() != null) {
			btnAddExercise.setDisable(false);
			btnRemoveExercise.setDisable(false);
		} else if (txtExerciseName.getText().isEmpty() ||
				txtExerciseDate.getValue() == null ||
				txtExerciseDuration.getText().isEmpty() ||
				txtAvgHRorReps.getText().isEmpty() ||
				txtMaxHRorSets.getText().isEmpty() ||
				txtDistanceorWeightLifted.getText().isEmpty()) {
			btnAddExercise.setDisable(true);
			btnRemoveExercise.setDisable(true);
		} else {
			btnAddExercise.setDisable(false);
			btnRemoveExercise.setDisable(false);
		}
	}
	/**
	 * This is a method that calls the three methods that disable the save, load,
	 * and delete buttons in the form. Since some TextFields require more than one
	 * of these methods to be called for their proper validation, this is a method
	 * that contains them all so it can be easily accessed and maintained.
	 */
	@FXML private void disableButtons() {
		disableSave();
		disableDeleteStudent();
		disableLoadStudent();
	}
	/**
	 * Display picture of corresponding gender when student is loaded.
	 */
	@FXML private void displayGender() {
		if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.UNSPECIFIED)) {
			unspecifiedAvatar.setVisible(true);
			womanAvatar.setVisible(false);
			manAvatar.setVisible(false);
		} else if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.FEMALE)) {
			womanAvatar.setVisible(true);
			unspecifiedAvatar.setVisible(false);
			manAvatar.setVisible(false);
		} else if (choiceGender.getSelectionModel().getSelectedItem().equals(Gender.MALE)) {
			manAvatar.setVisible(true);
			unspecifiedAvatar.setVisible(false);
			womanAvatar.setVisible(false);
		} else {
			unspecifiedAvatar.setVisible(false);
			womanAvatar.setVisible(false);
			manAvatar.setVisible(false);
		}
	}
	/**
	 * Loads the student's information and populates the GUI with it. Takes the
	 * student ID as a parameter.
	 */
	@FXML private void handleLoad() {
		try {
			myPerson.load(Integer.parseInt(txtStudentID.getText()));
			txtFirstName.setText(myPerson.getFirstName());
			txtLastName.setText(myPerson.getLastName());
			choiceGender.setValue(myPerson.getGender());
			txtHeight.setText(String.valueOf(myPerson.getHeight()));
			txtWeight.setText(String.valueOf(myPerson.getWeight()));
			txtBirthdate.setValue(myPerson.getBirthdate());
//displays image of what gender the user has specified
			displayGender();
//displays age of the user
			lblUserAge.setVisible(true);
			lblUserAge.setText("Age: " + myPerson.getAge());
//clears the tables
			aerobicTable.getItems().clear();
			strengthTable.getItems().clear();
//fills the tables
			aerobicTable.getItems().addAll(myPerson.getExercisesAerobic());
			strengthTable.getItems().addAll(myPerson.getExercisesStrength());

		} catch (IllegalArgumentException e) {
			// alert box for if studentID is not found in db
			Alert studentIDNotFound = new Alert(AlertType.ERROR);
			studentIDNotFound.setTitle("Load Error");
			studentIDNotFound.setContentText("Your student's ID number" 
			+ " has not been found. Please try a different student ID.");
			studentIDNotFound.showAndWait();
		}
	}
	/**
	 * Saves a new students information to the database. Repeated student
	 * information will be overwrote with the most recent save.
	 */
	@FXML private void handleSave() {
		
		myPerson.setStudentID(Integer.parseInt(txtStudentID.getText()));
		myPerson.setFirstName(txtFirstName.getText());
		myPerson.setLastName(txtLastName.getText());
		myPerson.setGender(choiceGender.getSelectionModel().getSelectedItem());
		myPerson.setHeight(Double.parseDouble(txtHeight.getText()));
		myPerson.setWeight(Double.parseDouble(txtWeight.getText()));
		myPerson.setBirthdate(txtBirthdate.getValue());

		myPerson.save();
		clearPersonalInfo();
		manAvatar.setVisible(false);
		womanAvatar.setVisible(false);
		unspecifiedAvatar.setVisible(false);
		lblUserAge.setVisible(false);
		aerobicTable.getItems().clear();
		strengthTable.getItems().clear();
		
		// confirmation of save
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("Save Confirmed");
		confirm.setContentText("Your student information has been "
				+ "saved. Click OK to save or load another student's information.");
		confirm.showAndWait();
	}
	/**
	 * Deletes student's personal information by receiving the student ID as a
	 * parameter.
	 */
	@FXML private void handleDelete() {
		try {
			myPerson.load(Integer.parseInt(txtStudentID.getText()));
			Alert deleteMessage = new Alert(AlertType.CONFIRMATION);
			deleteMessage.setTitle("Confirm Deletion");
			deleteMessage.setContentText("Are you sure you want to permanently delete this" + " student's information?"
					+ "\nPress OK to delete or CANCEL to cancel deletion.");
			Optional<ButtonType> result = deleteMessage.showAndWait();

			if (result.get() == ButtonType.CANCEL) {
				deleteMessage.close();
			} else {
				myPerson.delete();
				clearPersonalInfo();
				manAvatar.setVisible(false);
				womanAvatar.setVisible(false);
				unspecifiedAvatar.setVisible(false);
			}
		} catch (IllegalArgumentException e) {
			// alert box for if toyID is not found in db
			Alert studentIDNotFound = new Alert(AlertType.ERROR);
			studentIDNotFound.setTitle("Deletion Error");
			studentIDNotFound.setContentText(
					"Your student ID number" + " has not been found. Please try a different student ID.");
			studentIDNotFound.showAndWait();
		}
	}

	/**
	 * Clears all text fields after adding or removing an exercise.
	 * Also clears when a different type of exercise is toggled.
	 */
	@FXML private void clearExerciseInfo() {
		if (tabPane.getSelectionModel().getSelectedItem().equals(aerobicTab) || 
				tabPane.getSelectionModel().getSelectedItem().equals(strengthTab)) {
				txtExerciseDate.setValue(null);
				txtExerciseName.clear();
				txtExerciseDuration.clear();
				txtMaxHRorSets.clear();
				txtAvgHRorReps.clear();
				txtDistanceorWeightLifted.clear();
			}
		labelChange();
		toggleSelect();
	}
	/**
	 * Simple method to break up the size of the add exercise method. It simply sets
	 * all the information needed from the aerobic exercise to add it to the
	 * student's account.
	 */
	@FXML private void addAerobicInformation() {
		exerciseAerobic.setStudentID(Integer.parseInt(txtStudentID.getText()));
		exerciseAerobic.setExerciseDate(txtExerciseDate.getValue());
		exerciseAerobic.setExerciseName(txtExerciseName.getText());
		exerciseAerobic.setExerciseDuration(Duration.ofSeconds(Long.parseLong(txtExerciseDuration.getText())));
		exerciseAerobic.setMaxHeartRate(Integer.parseInt(txtMaxHRorSets.getText()));
		exerciseAerobic.setAverageHeartRate(Integer.parseInt(txtAvgHRorReps.getText()));
		exerciseAerobic.setDistance(Double.parseDouble(txtDistanceorWeightLifted.getText()));
		// saves the exercise
		exerciseAerobic.save();
	}
	/**
	 * Simple method to break up the size of the add exercise method. It simply sets
	 * all the information needed from the weights exercise to add it to the
	 * student's account.
	 */
	@FXML private void addStrengthInformation() {
		
		exerciseStrength.setStudentID(Integer.parseInt(txtStudentID.getText()));
		exerciseStrength.setExerciseDate(txtExerciseDate.getValue());
		exerciseStrength.setExerciseName(txtExerciseName.getText());
		exerciseStrength.setExerciseDuration(Duration.ofSeconds(Long.parseLong(txtExerciseDuration.getText())));
		exerciseStrength.setSets(Integer.parseInt(txtMaxHRorSets.getText()));
		exerciseStrength.setReps(Integer.parseInt(txtAvgHRorReps.getText()));
		exerciseStrength.setWeightLifted(Double.parseDouble(txtDistanceorWeightLifted.getText()));
		
		if(txtDistanceorWeightLifted.getText() == null) {
			Alert badLuck = new Alert(AlertType.ERROR);
			badLuck.setTitle("ERROR");
			badLuck.setContentText("Cannot have a Weight Lifted of 0. If you lifted"
					+ " bodyweight, enter 0.1 lbs.");
			badLuck.showAndWait();
		}
		// save the exercise
		exerciseStrength.save();
	}
	/**
	 * Simple method to break up the size of the remove exercise method. It simply
	 * sets all the information needed from the aerobic exercise to delete it from
	 * the student's account.
	 */
	@FXML private void aerobicDelete() {
		try {
		exerciseAerobic.setStudentID(Integer.parseInt(txtStudentID.getText()));
		exerciseAerobic.setExerciseDate(aerobicTable.getSelectionModel().getSelectedItem().getExerciseDate());
		exerciseAerobic.setExerciseName(aerobicTable.getSelectionModel().getSelectedItem().getExerciseName());
		// deletes the exercise
		exerciseAerobic.delete();
		} catch (IllegalArgumentException e) {
			Alert badLuck = new Alert(AlertType.ERROR);
			badLuck.setTitle("ERROR");
			badLuck.setContentText("Please enter the exercise date and name, along with your student profile"
					+ " loaded to delete an exercise.");
			badLuck.showAndWait();
		}
	}
	/**
	 * Simple method to break up the size of the remove exercise method. It simply
	 * sets all the information needed from the weights exercise to delete it from
	 * the student's account.
	 */
	@FXML private void strengthDelete() {
	try {
		exerciseStrength.setStudentID(Integer.parseInt(txtStudentID.getText()));
		exerciseStrength.setExerciseDate(txtExerciseDate.getValue());
		exerciseStrength.setExerciseName(txtExerciseName.getText());
		// deletes the exercise
		exerciseStrength.delete(); 
	} catch (IllegalArgumentException e) {
		Alert badLuck = new Alert(AlertType.ERROR);
		badLuck.setTitle("ERROR");
		badLuck.setContentText("Either no student profile has been loaded or not all" 
				+ " information necessary has been added in order to save an exercise.");
		badLuck.showAndWait();
		}
	}
	/**
	 * Saves a new exercise to the student's account. Can either be aerobic or
	 * weights.
	 */
	@FXML private void handleAddExercise() {
		//adds an aerobic exercise
		if (exerciseGroup.getSelectedToggle().equals(radioAerobic)) {
				//adds the exercise and refreshes the table
				addAerobicInformation();
				clearExerciseInfo();
				aerobicTable.getItems().clear();
				aerobicTable.getItems().addAll(myPerson.getExercisesAerobic());
				handleLoad();
				btnAddExercise.setDisable(true);
				btnRemoveExercise.setDisable(true);
				
				// confirmation box
				Alert confirm = new Alert(AlertType.CONFIRMATION);
				confirm.setTitle("Confirmation");
				confirm.setContentText("Your aerobic exercise" + " has been saved.");
				confirm.showAndWait();
		}
		//adds a strength exercise
		if (exerciseGroup.getSelectedToggle().equals(radioStrength)) {
				
				//adds the exercise and refreshes the table
				addStrengthInformation();
				clearExerciseInfo();
				strengthTable.getItems().clear();
				strengthTable.getItems().addAll(myPerson.getExercisesStrength());
				handleLoad();
				btnAddExercise.setDisable(true);
				btnRemoveExercise.setDisable(true);
				
				// confirmation box
				Alert confirm = new Alert(AlertType.CONFIRMATION);
				confirm.setTitle("Confirmation");
				confirm.setContentText("Your strength exercise" + " has been saved.");
				confirm.showAndWait();
		}
	}
	/**
	 * Removes and exercise from the student's account. Can be either aerobic or
	 * weights.
	 */
	@FXML private void handleRemoveExercise() {
			// ALERT BOX
			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");
			Alert editable = new Alert(AlertType.NONE, "Are you sure?", yes, no);
			editable.setTitle("Delete This Book");
			editable.setContentText("Are you sure you want to remove this exercise?" + " Click YES to delete"
					+ " or NO to continue with your Fitness Tracker.");
			editable.showAndWait().ifPresent(response -> {
			if (response == yes) {
				if (exerciseGroup.getSelectedToggle().equals(radioAerobic)) {
						aerobicDelete();
						clearExerciseInfo();
						aerobicTable.getItems().clear();
						aerobicTable.getItems().addAll(myPerson.getExercisesAerobic());
						handleLoad();
						btnAddExercise.setDisable(true);
						btnRemoveExercise.setDisable(true);
						
						// confirmation box
						Alert confirm = new Alert(AlertType.CONFIRMATION);
						confirm.setTitle("Confirmation");
						confirm.setContentText("Your aerobic exercise" + " has been removed.");
						confirm.showAndWait();
					}
				if (exerciseGroup.getSelectedToggle().equals(radioStrength)) {
						strengthDelete();
						clearExerciseInfo();
						myPerson.loadStrengthExercise();
						strengthTable.getItems().clear();
						strengthTable.getItems().addAll(myPerson.getExercisesStrength());
						handleLoad();
						btnAddExercise.setDisable(true);
						btnRemoveExercise.setDisable(true);
						
						// confirmation box
						Alert confirm = new Alert(AlertType.CONFIRMATION);
						confirm.setTitle("Confirmation");
						confirm.setContentText("Your strength exercise" + " has been removed.");
						confirm.showAndWait();
				}
			}
		});
	}
	/**
	 * Once a student's exercises are loaded in the table view, if they click on an
	 * exercise, populate the information into the text fields to the left.
	 * 
	 * Also depending on the exercise type, correlate the choice with the labels on
	 * the table and the left fields.
	 */
	@FXML private void populateExerciseInfo() {
		if (tabPane.getSelectionModel().getSelectedItem().equals(aerobicTab)) {
		ExerciseAerobic aerobicExerciseToEdit = aerobicTable.getSelectionModel().getSelectedItem();
		txtExerciseDate.setValue(aerobicExerciseToEdit.getExerciseDate());
		txtExerciseName.setText(aerobicExerciseToEdit.getExerciseName().toString());
		txtExerciseDuration.setText(String.valueOf(aerobicExerciseToEdit.getExerciseDuration().toMinutes()));
		txtMaxHRorSets.setText(String.valueOf(aerobicExerciseToEdit.getMaxHeartRate()));
		txtAvgHRorReps.setText(String.valueOf(aerobicExerciseToEdit.getAverageHeartRate()));
		txtDistanceorWeightLifted.setText(String.valueOf(aerobicExerciseToEdit.getDistance()));
		}
		
		if (tabPane.getSelectionModel().getSelectedItem().equals(strengthTab)) {
		ExerciseStrength strengthExerciseToEdit = strengthTable.getSelectionModel().getSelectedItem();
		txtExerciseDate.setValue(strengthExerciseToEdit.getExerciseDate());
		txtExerciseName.setText(strengthExerciseToEdit.getExerciseName().toString());
		txtExerciseDuration.setText(String.valueOf(strengthExerciseToEdit.getExerciseDurationMinutes()));
		txtMaxHRorSets.setText(String.valueOf(strengthExerciseToEdit.getSets()));
		txtAvgHRorReps.setText(String.valueOf(strengthExerciseToEdit.getReps()));
		txtDistanceorWeightLifted.setText(String.valueOf(strengthExerciseToEdit.getWeightLifted()));
		}
		
		disableAddRemoveButtons();
	}
	/**
	 * Error checking that aligns whatever tab or radio button is selected with the
	 * labels and text fields.
	 */
	@FXML private void tabSelect() {
		if (exerciseGroup.getSelectedToggle().equals(radioAerobic)) {
			tabPane.getSelectionModel().select(aerobicTab);
		}
		if (exerciseGroup.getSelectedToggle().equals(radioStrength)) {
			tabPane.getSelectionModel().select(strengthTab);
		}
	}
	/**
	 * 
	 */
	@FXML private void toggleSelect() {
		if (aerobicTab.isSelected()) {
			exerciseGroup.selectToggle(radioAerobic);
		} else {
			exerciseGroup.selectToggle(radioStrength);
		}
	}
	/**
	 * Changes style to dark mode when check box is toggled.
	 */
	@FXML private void handleStyleChange() {
		if (chkbxDarkMode.isSelected()) {
			txtBirthdate.getScene().getStylesheets().add("/application/dark-theme.css");
		} else {
			txtBirthdate.getScene().getStylesheets().remove("/application/dark-theme.css");
		}
	}
}
//PROBLEM: when I edit a user's personal information then save, it deletes all their exercises.
//PROBLEM: enabling and disabling add/remove buttons