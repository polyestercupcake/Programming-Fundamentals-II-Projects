package controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Gender;
import model.Person;

public class Controller {

	@FXML
	private TextField txtStudentID;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private ComboBox<Gender> choiceGender;

	@FXML
	private TextField txtHeight;

	@FXML
	private TextField txtWeight;

	@FXML
	private DatePicker txtBirthdate;

	@FXML
	private Button btnLoadStudent;

	@FXML
	private Button btnSaveStudent;

	@FXML
	private Button btnDeleteStudent;

	private Person myPerson = new Person();

	// METHODS
	public final void initialize() {
		// choiceGender.setItems(Gender.values());
		choiceGender.getItems().addAll(Gender.values());
		choiceGender.setValue(Gender.UNSPECIFIED);
	}
	
	/**
	 * Clears all fields after a save or delete.
	 */
	public void handleClear() {
		txtStudentID.clear();
		txtFirstName.clear();
		txtLastName.clear();
		choiceGender.setValue(Gender.UNSPECIFIED);
		txtHeight.clear();
		txtWeight.clear();
		txtBirthdate.setValue(null);
	}

	/**
	 * Loads the student's information and populates the GUI with it. Takes the
	 * student ID as a parameter.
	 */
	@FXML
	public void handleLoad() {
		try {
			myPerson.load(Integer.parseInt(txtStudentID.getText()));

			txtFirstName.setText(myPerson.getFirstName());
			txtLastName.setText(myPerson.getLastName());
			choiceGender.setValue(myPerson.getGender());
			txtHeight.setText(String.valueOf(myPerson.getHeight()));
			txtWeight.setText(String.valueOf(myPerson.getWeight()));
			txtBirthdate.setValue(myPerson.getBirthdate());

		} catch (IllegalArgumentException e) {
			// alert box for if studentID is not found in db
			Alert studentIDNotFound = new Alert(AlertType.ERROR);
			studentIDNotFound.setTitle("Load Error");
			studentIDNotFound.setContentText(
					"Your student's ID number" + " has not been found. Please try a different student ID.");
			studentIDNotFound.showAndWait();
		}

	}

	/**
	 * Saves a new students information to the database.
	 * Repeated student information will be overwrote with the most recent save.
	 */
	@FXML
	public void handleSave() {
	
			myPerson.setStudentID(Integer.parseInt(txtStudentID.getText()));
			myPerson.setFirstName(txtFirstName.getText());
			myPerson.setLastName(txtLastName.getText());
			myPerson.setGender(choiceGender.getSelectionModel().getSelectedItem());
			myPerson.setHeight(Double.parseDouble(txtHeight.getText()));
			myPerson.setWeight(Double.parseDouble(txtWeight.getText()));
			myPerson.setBirthdate(txtBirthdate.getValue());

			myPerson.save();
			handleClear();

			// confirmation of save
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("Save Confirmed");
			confirm.setContentText("Your student information has been "
					+ "saved. Click OK to save or load another student's information.");
			confirm.showAndWait();
	}

	/**
	 * Deletes student's information by receiving the student ID as a parameter.
	 */
	@FXML
	public void handleDelete() {
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
				handleClear();
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

}