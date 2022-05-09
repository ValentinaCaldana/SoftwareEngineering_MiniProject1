package geography;

import java.util.HashMap;
import java.util.Map;

import geography.controller.ButtonController;
import geography.view.TableContainer;
import geography.model.SceneName;
import geography.model.WorldMap;
import geography.view.CreateView;
import geography.view.HomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GeographyApp extends Application {

	// HashMap zum Szenen verbinden
	private static Map<SceneName, Scene> scenes = new HashMap<>();

	public static TableContainer tableContainer = new TableContainer();
	public static WorldMap map = new WorldMap();
	public static ButtonController buttonController;

	public void start(Stage primaryStage) throws Exception {

		// model
		var homeView = new HomeView(primaryStage);
		var createView = new CreateView(primaryStage);

		scenes.put(SceneName.HOME, homeView.getScene());
		scenes.put(SceneName.CREATE, createView.getScene());

		// controller
		buttonController = new ButtonController(primaryStage, createView, homeView);

		primaryStage.setScene(scenes.get(SceneName.HOME));
		primaryStage.setTitle("Geography");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	// Methoden die die Szenen holt
	public static Map<SceneName, Scene> getScenes() {
		return scenes;
	}

	public static void main(String[] args) {
		launch();
	}

}
