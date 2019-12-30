package controller;

//always import the scene.control and NEVER the awt import
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Circuit;

/**
 * This class, Controller, contains all fields and methods required to make this
 * program run smoothly and efficiently. Receiving user input, calculating, and
 * outputting to the user as well as error checking is included.
 * 
 * @author 217056
 *
 */
public class Controller {

	/**
	 * This field is a button that the user can click on to activate their
	 * calculation as long as all prior conditions are met. Can also calculate by
	 * pressing the Enter key.
	 */
	@FXML
	private Button calculate;

	/**
	 * This field is a TextField where the user can enter the value they wish for
	 * Voltage.
	 */
	@FXML
	private TextField txtVoltage;

	/**
	 * This field is a TextField where the user can enter the value they wish for
	 * Amperage.
	 */
	@FXML
	private TextField txtAmperage;

	/**
	 * This field is a TextField where the user can enter the value they wish for
	 * Resistance.
	 */
	@FXML
	private TextField txtResistance;

	/**
	 * This method first determines which area the user wants to calculate, then
	 * calculates and sends the output back to the user. Error checking is in place
	 * for strings and negative numbers. The Circuit class is used in this method.
	 */
	@FXML
	private void handleCalculate() {
		// create instance of Circuit class
		Circuit myCircuit = new Circuit();
		try {
			// if user chooses to calculate voltage
			if (txtVoltage.getText().isEmpty()) {
				myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
				myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
				myCircuit.calculateVoltage();
				txtVoltage.setText(String.valueOf(myCircuit.getVoltage()));
				// if user wants to calculate amperage
			} else if (txtAmperage.getText().isEmpty()) {
				myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
				myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
				myCircuit.calculateAmperage();
				txtAmperage.setText(String.valueOf(myCircuit.getAmperage()));
				// if user wants to calculate resistance
			} else if (txtResistance.getText().isEmpty()) {
				myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
				myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
				myCircuit.calculateResistance();
				txtResistance.setText(String.valueOf(myCircuit.getResistance()));
			}
			// catches strings
		} catch (NumberFormatException e) {
			Alert message = new Alert(AlertType.ERROR);
			message.setTitle("Bad Input");
			message.setContentText("Input must be a non-negative number. \nPlease try again.");
			message.showAndWait();
			// catches negative numbers
		} catch (IllegalArgumentException e) {
			Alert message = new Alert(AlertType.ERROR);
			message.setTitle("Bad Input");
			message.setContentText(e.getMessage());
			message.showAndWait();
		}
	}

	/**
	 * This method will clear the contents of each TextField once a calculation is
	 * made. The user may either click the "Clear" button or press the Escape key.
	 */
	@FXML
	private void handleClear() {
		// clears voltage
		txtVoltage.setText("");
		// clears amperage
		txtAmperage.setText("");
		// clears resistance
		txtResistance.setText("");
		// requests that this Component gets input focus
		txtVoltage.requestFocus();
	}
}
