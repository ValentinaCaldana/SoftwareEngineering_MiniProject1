package geography.view;

import geography.GeographyApp;
import geography.controller.ButtonController;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CreateView {

    // Parameter für Konstruktor:
    public Stage stage;
    private ButtonController buttonController;

    // Superbehälter:
    private BorderPane window = new BorderPane();


    // Views
    public RegionView regionView= new RegionView();
    public CountryView countryView = new CountryView();
    public CityView cityView= new CityView();



    // Konstruktor:
    public CreateView(Stage stage) {
        this.stage = stage;
    }

    public CountryView getCountryView() {
        return countryView;
    }

    // Szene
    public Scene getScene() {

        // BorderPane "Top-Elemente":
        this.window.setLeft(this.countryView);
        this.window.setCenter(this.regionView);
        this.window.setRight(this.cityView);

        Scene scene = new Scene(this.window);
        stage.sizeToScene();

        this.window.setId("window");
        scene.getStylesheets().add(getClass().getResource("Create&Read_Stylings.css").toExternalForm());

        return scene;
    }





    // getter und setter für CityView
    public CityView getCityView() {
        return cityView;
    }

    public void setCityView(CityView cityView) {
        this.cityView = cityView;
    }

    // getter und setter für RegionView
    public RegionView getRegionView() {
        return regionView;
    }

    public void setRegionView(RegionView regionView) {
        this.regionView = regionView;
    }



}
