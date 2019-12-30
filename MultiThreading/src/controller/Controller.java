package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Database;

public class Controller {
	@FXML
	private TextArea txtQuery;
	@FXML
	private TextArea txtAnswer;
	@FXML 
	private void handleQuery() {
		Database db = new Database();
		txtAnswer.setText(db.getAnswer(txtQuery.getText()));
	}
	@FXML 
	private void handleClear() {
		txtAnswer.setText("");
		txtQuery.setText("SELECT * FROM ADMIN;");
	}
}
