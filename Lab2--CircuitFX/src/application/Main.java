package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class is the entry point for JavaFX. This is what actually creates the user interface
 * so we can edit in Scene builder.
 * 
 * @author 217056
 */
public class Main extends Application {
	@Override
	public final void start(final Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Circuit.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//I titled my gui from here
			primaryStage.setTitle("Ohm's Law Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * This method is ignored in a correctly deployed JavaFX application.
	 * It serves only as fallback in case the application cannot be
	 * launched in IDEs with limited FX support.
	 * 
	 * @param args that should be passed.
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
