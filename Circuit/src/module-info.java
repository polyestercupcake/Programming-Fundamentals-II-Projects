module Circuit {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	exports application to javafx.graphics;
	opens controller to javafx.fxml;
}