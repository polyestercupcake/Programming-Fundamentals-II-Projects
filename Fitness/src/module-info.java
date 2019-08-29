module fitness {
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires BerkstresserDB.ToyCircuit;
	opens application to javafx.graphics;
	opens controller to javafx.fxml;
}