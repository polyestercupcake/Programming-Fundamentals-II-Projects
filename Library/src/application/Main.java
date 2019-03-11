package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

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
			Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("/application/icon.png"));
			//I titled my gui from here
			primaryStage.setTitle("Kembell Berkstresser Library Control");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method launches the JavaFX application.
	 * @param args that should be passed.
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
