package geography.view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HomeView {

	// Variablen für Konstruktor
	public Stage stage;

	// View
	private CreateView createView;

	// Superbehälter des Fensters:
	private BorderPane window = new BorderPane();
	

	// Subbehälter der BorderPane Regionen:
	private HBox top;
	private HBox center;
	private GridPane right;

	// Elemente top:
	private Label title;
	private ImageView imageView;
	private Image image = new Image(getClass().getResourceAsStream("/geography/view/images/Home_Pic.jpg"));

	// Elemente center:
	public Button create;

	// Elemente bottom:
	private Label recCountries;
	protected Label countCountries;
	private Label recRegions;
	protected Label countRegions;
	private Label recCities;
	protected Label countCities;

	// Konstruktor
	public HomeView(Stage stage) {
		this.stage = stage;
		this.createView = new CreateView(stage);

	}

	// Szene
	public Scene getScene() {

		// Top of BorderPane:
		this.top = new HBox();
		
		this.title = new Label("Welcome To GeoBase\nYour Geography Database");
		title.setPadding(new Insets(10));
		
		this.imageView = new ImageView(this.image);
		imageView.setFitHeight(90);
		imageView.setFitWidth(100);

		top.getChildren().addAll(title, imageView);
		top.setPadding(new Insets(40, 20, 10, 20));
		top.setSpacing(150);

		top.setId("top");
		title.setId("title");

		// Center of BorderPane:
		this.right = new GridPane();
		this.center = new HBox();
		this.recCountries = new Label("Recorded Countries:");
		this.countCountries = new Label(Integer.toString(0));
		this.recRegions = new Label("Recorded Regions:");
		this.countRegions = new Label(Integer.toString(0));
		this.recCities = new Label("Recorded Cities:");
		this.countCities = new Label(Integer.toString(0));
	
		this.create = new Button("Create A GeoRecord");
		create.setPadding(new Insets(40, 50, 40, 50));
		right.getChildren().addAll(create, recCountries,countCountries, recRegions, countRegions, recCities, countCities);
		
		

		GridPane.setConstraints(recCountries, 0, 0);		GridPane.setConstraints(countCountries, 1, 0);
		GridPane.setConstraints(recRegions, 0, 1);			GridPane.setConstraints(countRegions, 1, 1);
		GridPane.setConstraints(recCities, 0, 2);			GridPane.setConstraints(countCities, 1, 2);		

		center.getChildren().addAll(create,right);
		
		center.setAlignment(Pos.CENTER);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(90));
		right.setHgap(80);
		right.setVgap(40);
		right.setId("right");
		center.setId("center");
		
	
		// Platzierung der Subbehälter in BorderPane:
		window.setTop(top);
		window.setCenter(center);

		Scene scene = new Scene(window, 800, 500);
		scene.getStylesheets().add(getClass().getResource("HomeView_Stylings.css").toExternalForm());

		return scene;

	}

	public void setCountCities(Label countCities) {
		this.countCities = countCities;
	}

	public void setCountRegions(Label countRegions) {
		this.countRegions = countRegions;
	}

	public void setCountCountries(Label countCountries) {
		this.countCountries = countCountries;
	}

	public Label getCountCities() {
		return countCities;
	}

	public Label getCountCountries() {
		return countCountries;
	}

	public Label getCountRegions() {
		return countRegions;
	}

	public Button getCreate() {
		return create;
	}

}
