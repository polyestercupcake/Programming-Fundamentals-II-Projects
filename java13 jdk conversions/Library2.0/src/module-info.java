module library {
	requires java.sql;
	requires BerkstresserDB.ToyCircuit;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	exports application to javafx.graphics;
	exports controller to javafx.fxml;
	opens controller to javafx.fxml;
}