//Database Function CRUD: Create, Read, Update, Delete
//ADD A MENU BUTTON AT THE TOP THAT ALLOWS YOU TO CHANGE LANGUAGES...MAYBE EVEN A DARK MODE
package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import model.Toy;

/**
 * This class is pretty much the place where everything gets told what to do
 * within the GUI and the program as a whole. Also contains substantial error-proofing.
 * 
 * @author Tanner's Laptop
 */
public class Controller implements Initializable {
	/**
	 * This field is used for the ChangeListener added to restrict the ToyID's ID number
	 * to 6 characters only.
	 */
	static final int LIMIT = 6;

	/**
	 * This is a group of radio buttons containing all buttons from Circuit 1.
	 */
	@FXML
	private ToggleGroup group1 = new ToggleGroup();

	/**
	 * This is a group of radio buttons containing all buttons from Circuit 2.
	 */
	@FXML
	private ToggleGroup group2 = new ToggleGroup();

	/**
	 * This is an instance of the Toy class as a field. Within the Toy class are
	 * also instances of the Circuit class, which will be utilized. No FXML is
	 * needed.
	 */
	private Toy myToy = new Toy();

	/**
	 * This TextField holds the name of the inspector doing the toy inspection.
	 */
	@FXML
	private TextField txtInspector;

	/**
	 * This TextField holds the ID number of the toy.
	 */
	@FXML
	private TextField txtToyID;

	/**
	 * This TextField will automatically contain the exact date and time the submit
	 * button was pressed on the inspection report. This this is technically
	 * requires no user input, the ability for inspectors to enter data into this
	 * field has been disabled. It's pretty cool!
	 */
	@FXML
	private TextField inspectionDateTime;

	/**
	 * This Label represents the voltage of Circuit 1 of the toy.
	 */
	@FXML
	private Label labelVoltage1;

	/**
	 * This Label represents the resistance of Circuit 1 of the toy.
	 */
	@FXML
	private Label labelResistance1;

	/**
	 * This Label represents the voltage of Circuit 2 of the toy.
	 */
	@FXML
	private Label labelVoltage2;

	/**
	 * This Label represents the resistance of Circuit 2 of the toy.
	 */
	@FXML
	private Label labelResistance2;

	/**
	 * This TextField holds the voltage of Circuit 1 of the toy.
	 */
	@FXML
	private TextField txtVoltageCircuit1;

	/**
	 * This TextField holds the resistance of Circuit 1 of the toy.
	 */
	@FXML
	private TextField txtResistanceCircuit1;

	/**
	 * This TextField holds the voltage of Circuit 2 of the toy.
	 */
	@FXML
	private TextField txtVoltageCircuit2;

	/**
	 * This TextField holds the resistance of Circuit 2 of the toy.
	 */
	@FXML
	private TextField txtResistanceCircuit2;

	/**
	 * This RadioButton represents Germany in Circuit 1 of the toy.
	 */
	@FXML
	private RadioButton radioGermanyCircuit1;

	/**
	 * This RadioButton represents China in Circuit 1 of the toy.
	 */
	@FXML
	private RadioButton radioChinaCircuit1;

	/**
	 * This RadioButton represents the United States in Circuit 1 of the toy.
	 */
	@FXML
	private RadioButton radioUSCircuit1;

	/**
	 * This RadioButton represents Germany in Circuit 2 of the toy.
	 */
	@FXML
	private RadioButton radioGermanyCircuit2;

	/**
	 * This RadioButton represents China in Circuit 2 of the toy.
	 */
	@FXML
	private RadioButton radioChinaCircuit2;

	/**
	 * This RadioButton represents the United States in Circuit 2 of the toy.
	 */
	@FXML
	private RadioButton radioUSCircuit2;

	/**
	 * This Button saves all information needed to a database so it may be loaded,
	 * viewed, and/or deleted in the future. Error-checking has been included
	 * for this button.
	 */
	@FXML
	private Button handleSave;

	/**
	 * This Button is the button that loads the Toy ID from the database.
	 * Error-checking has been included for this button.
	 */
	@FXML
	private Button handleLoadToyID;

	/**
	 * This Button is the button that deletes the Toy information from the database.
	 * Error-checking has been included for this button.
	 */
	@FXML
	private Button handleDelete;

	/**
	 * This string is for the Manufacture Location. 
	 * It's so that the text representing each of the three toy manufacturing countries
	 * may be easily visible inside the database. Each countries name is set to 
	 * this variable if and only if their country's radio button has been selected 
	 * within their respected toy's circuit.
	 */
	private String s;
	
	@Override
	public final void initialize(final URL arg0, final ResourceBundle arg1) {
		// disables the save, load, and delete buttons at program launch
		handleSave.setDisable(true);
		handleLoadToyID.setDisable(true);
		handleDelete.setDisable(true);
		handleSave.setDefaultButton(true);

		// listener added to all String TextFields
		addStringListener(txtInspector);

		// listener added to all Double TextFields works...but doesn't do decimals
		addDoubleListener(txtVoltageCircuit1);
		addDoubleListener(txtVoltageCircuit2);
		addDoubleListener(txtResistanceCircuit1);
		addDoubleListener(txtResistanceCircuit2);
		//restrict ToyID to 6 characters
		addDoubleListener(txtToyID);

		// changes languages from English to Spanish from the lang text files inside the folder
		// change .forLanguageTag("en") to ("es") in order to switch from English to Spanish
		ResourceBundle rd = ResourceBundle.getBundle("Translations", Locale.forLanguageTag("en"));
		labelVoltage1.setText(rd.getString("Voltage"));
		labelResistance1.setText(rd.getString("Resistance"));
		labelVoltage2.setText(rd.getString("Voltage"));
		labelResistance2.setText(rd.getString("Resistance"));
	}

	/**
	 * This method contains all the actions occurring when the "Save" button is
	 * clicked, including saving the information to a database. The user may either
	 * click the button or press the Enter key on their keyboard to save.
	 */
	@FXML
	private void handleSave() {
		// inspector text field
		myToy.setInspector(txtInspector.getText());
		// toyID text field
		myToy.setToyID(Integer.parseInt(txtToyID.getText()));
		// sets the time when save button is clicked
		myToy.setInspectionDateTime(LocalDateTime.now());

//Circuit 1
		// Voltage Circuit 1
		myToy.getCircuit1().setVoltage(Double.parseDouble(txtVoltageCircuit1.getText()));
		// Resistance Circuit 1
		myToy.getCircuit1().setResistance(Double.parseDouble(txtResistanceCircuit1.getText()));
		// Manufacture Location Circuit 1
		RadioButton b1 = (RadioButton) group1.getSelectedToggle();
		if (b1 == radioChinaCircuit1) {
			s = "China";
		} else if (b1 == radioGermanyCircuit1) {
			s = "Germany";
		} else {
			s = "United States";
		}
		myToy.getCircuit1().setManufactureLocation(s);

//Circuit 2
		// Voltage Circuit 2
		myToy.getCircuit2().setVoltage(Double.parseDouble(txtVoltageCircuit2.getText()));
		// Resistance Circuit 2
		myToy.getCircuit2().setResistance(Double.parseDouble(txtResistanceCircuit2.getText()));
		// Manufacture Location Circuit 2
		RadioButton b2 = (RadioButton) group2.getSelectedToggle();
		if (b2 == radioChinaCircuit2) {
			s = "China";
		} else if (b2 == radioGermanyCircuit2) {
			s = "Germany";
		} else {
			s = "United States";
		}
		myToy.getCircuit2().setManufactureLocation(s);

		// saves into database
		myToy.save();

		// display box
		Alert confirmMessage = new Alert(AlertType.CONFIRMATION);
		confirmMessage.setTitle("Save Confirmed");
		confirmMessage.setContentText("Your toy's information has been " 
		+ "saved. Click OK to save or load another toy.");
		confirmMessage.showAndWait();
}

	/**
	 * This method is connected to the "Clear" button and will clear the contents of 
	 * each TextField once a calculation is made. The user may either click 
	 * the "Clear" button or press the Escape (Esc) key to clear.
	 * It clears all TextFields except Inspector because one inspector may
	 * enter multiple toys into the database in one sitting.
	 */
	@FXML
	private void handleClear() {
		// clears all TextFields EXCEPT Inspector ID because one Inspector may
		// enter multiple toys into the database at once
		txtResistanceCircuit1.setText("");
		txtResistanceCircuit2.setText("");
		inspectionDateTime.setText("<calculated>");
		txtToyID.setText("");
		txtVoltageCircuit1.setText("");
		txtVoltageCircuit2.setText("");
		group1.selectToggle(null);
		group2.selectToggle(null);
		// requests that this Component gets input focus
		txtInspector.requestFocus();
	}

	/**
	 * This method is connected to the "Load Toy" button and will load all of 
	 * the toy's information when the button is pressed. All that is needed is
	 * the Toy ID number to load the toy's information.
	 */
	@FXML
	private void handleLoadToyID() {

		try {
			// pulls everything from toy and circuit when load toy id is pressed
			myToy.load(Integer.parseInt(txtToyID.getText()));
			// sets inspector
			txtInspector.setText(myToy.getInspector());
			// sets time the inspection was submitted
			inspectionDateTime.setText(String.valueOf(myToy.getInspectionDateTime()));

//Circuit 1
			// sets voltage
			txtVoltageCircuit1.setText(String.valueOf(myToy.getCircuit1().getVoltage()));
			// sets resistance
			txtResistanceCircuit1.setText(String.valueOf(myToy.getCircuit1().getResistance()));
			// sets manufacture location
			if (myToy.getCircuit1().getManufactureLocation().equals("China")) {
				radioChinaCircuit1.setSelected(true);
			} else if (myToy.getCircuit1().getManufactureLocation().equals("Germany")) {
				radioGermanyCircuit1.setSelected(true);
			} else {
				radioUSCircuit1.setSelected(true);
			}

//Circuit 2
			// sets voltage
			txtVoltageCircuit2.setText(String.valueOf(myToy.getCircuit2().getVoltage()));
			// sets resistance
			txtResistanceCircuit2.setText(String.valueOf(myToy.getCircuit2().getResistance()));
			// sets manufacture location
			if (myToy.getCircuit2().getManufactureLocation().equals("China")) {
				radioChinaCircuit2.setSelected(true);
			} else if (myToy.getCircuit2().getManufactureLocation().equals("Germany")) {
				radioGermanyCircuit2.setSelected(true);
			} else {
				radioUSCircuit2.setSelected(true);
			}
		} catch (IllegalArgumentException e) {
			// alert box for if toyID is not found in db
			Alert toyIDNotFound = new Alert(AlertType.ERROR);
			toyIDNotFound.setTitle("Load Error");
			toyIDNotFound.setContentText("Your Toy's ID number" 
			+ " has not been found. Please try a different Toy ID.");
			toyIDNotFound.showAndWait();
		}
	}
	
	/**
	 * This method will delete the toy's information when pressed.
	 * The Toy ID number is the only information needed to delete a
	 * toy's information.
	 */
	@FXML
	private void handleDelete() {
	try {
		// myToy.setToyID(Integer.parseInt(txtToyID.getText()));
		myToy.load(Integer.parseInt(txtToyID.getText()));
		// display box
		Alert deleteMessage = new Alert(AlertType.CONFIRMATION);
		deleteMessage.setTitle("Confirm Deletion");
		deleteMessage.setContentText("Are you sure you want to permanently delete this Toy?"
		+ "\nPress OK to delete or CANCEL to cancel deletion.");
		Optional<ButtonType> result = deleteMessage.showAndWait();

			if (result.get() == ButtonType.CANCEL) {
				deleteMessage.close();
		} else {
			myToy.delete();
			// clears all fields except Inspector name
			handleClear();
		}
		
	} catch (IllegalArgumentException e) {
		// alert box for if toyID is not found in db
		Alert toyIDNotFound = new Alert(AlertType.ERROR);
		toyIDNotFound.setTitle("Deletion Error");
		toyIDNotFound.setContentText("Your Toy's ID number" 
		+ " has not been found. Please try a different Toy ID.");
		toyIDNotFound.showAndWait();
	}
}

	/**
	 *  String Listener for String TextFields to only accept characters for input.
	 * @param textField is a general TextField and this method will be called by 
	 * all TextFields with String input.
	 */
	private void addStringListener(final TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue, 
					final String newValue) {
			// HOW IT WORKS
			// if newValue does not match \w (all letters and numbers) , then whitespace , then all letters and numbers
			// set newValue text to replace anything that is not in the set below (all letters, numbers, and whitespace)
				if (!newValue.matches("\\w*( )?\\w*")) {
					textField.setText(newValue.replaceAll("[^\\w ]", ""));
				}
 			}
		});
	}

	/**
	 *  Double Listener for TextFields to only accept numbers for input.
	 * @param textField is a general TextField and this method will be 
	 * called by all TextFields that require numerical input.
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
			
				// sets ToyID max length to 6 characters for input
				if (textField.equals(txtToyID)) {
				if (textField.getText().length() >= LIMIT) {
					textField.setText(textField.getText().substring(0, LIMIT));
					}
				}
			}
		});
	}
	
	/**
	 * This method is an error-checking methodology that will disable the "Save" button
	 * until all information is entered or selected in the form.
	 */
	@FXML
	private void disableSave() {
		if (txtInspector.getText().isEmpty() 
				|| txtResistanceCircuit1.getText().isEmpty()
				|| txtResistanceCircuit2.getText().isEmpty() 
				|| txtToyID.getText().isEmpty()
				|| txtVoltageCircuit1.getText().isEmpty() 
				|| txtVoltageCircuit2.getText().isEmpty()
				|| group1.getSelectedToggle() == null 
				|| group2.getSelectedToggle() == null) {
			handleSave.setDisable(true);
		} else {
			handleSave.setDisable(false);
		}
	}
	
	/**
	 * This method is an error-checking methodology that will disable 
	 * the "Delete Toy" button until a Toy ID has been entered to be potentially deleted.
	 */
	@FXML
	private void disableDeleteToy() {
		if (txtToyID.getText().isEmpty()) {
			handleDelete.setDisable(true);
		} else {
			handleDelete.setDisable(false);
		}
	}
	
	/**
	 * This method is an error-checking methodology that will disable 
	 * the "Load Toy" button until a Toy ID has been entered
	 * to be loaded if it is a valid ID number.
	 */
	@FXML
	private void disableLoadToy() {
		if (txtToyID.getText().isEmpty()) {
			handleLoadToyID.setDisable(true);
		} else {
			handleLoadToyID.setDisable(false);
		}
	}
	
	/**
	 * This is a method that calls the three methods that disable the save, load,
	 * and delete buttons in the form. Since some TextFields require more than one
	 * of these methods to be called for their proper validation, this is a method that
	 * contains them all so it can be easily accessed and maintained.
	 */
	@FXML
	private void disableToyButtons() {
		disableSave();
		disableDeleteToy();
		disableLoadToy();
	}
}
