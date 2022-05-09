package geography.controller;

import geography.GeographyApp;
import geography.model.City;
import geography.model.Country;
import geography.model.Region;
import geography.model.SceneName;
import geography.view.CreateView;
import geography.view.HomeView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ButtonController {

	// Variablen für Konstruktor
	private final HomeView homeView;
	private Stage stage;
	private CreateView createView;

	// Konstruktor
	public ButtonController(Stage stage, CreateView createView, HomeView homeView) {
		this.stage = stage;
		this.createView = createView;
		this.homeView = homeView;
		setUpHomeView();
		setUpCountryView();
		setUpRegionView();
		setUpCityView();
		setUpTableFilterFunctions();
	}

	// Methode Filterung der zusammenhängenden Objekte
	private void setUpTableFilterFunctions() {
		setUpCountryTableFilter();
		setUpRegionTableFilter();
	}

	private void setUpRegionTableFilter() {
		GeographyApp.tableContainer.tableRegion.setOnMouseClicked(e -> {
			var region = GeographyApp.tableContainer.tableRegion.getSelectionModel().getSelectedItem();
			this.createView.getCityView().setRegion(region);
			GeographyApp.tableContainer.tableCity.getItems().clear();
			GeographyApp.tableContainer.tableCity.getItems().addAll(region.getCityCollection());
		});
	}

	private void setUpCountryTableFilter() {
		GeographyApp.tableContainer.tableCountry.setOnMouseClicked(e -> {
			var country = GeographyApp.tableContainer.tableCountry.getSelectionModel().getSelectedItem();
			this.createView.getRegionView().setCountry(country);
			GeographyApp.tableContainer.tableRegion.getItems().clear();
			GeographyApp.tableContainer.tableRegion.getItems().addAll(country.getRegionCollection());
			GeographyApp.tableContainer.tableCity.getItems().clear();
		});
	}

	// Methoden für die Home-View
	public void setUpHomeView() {
		createButtonClickedOnHomeView();
	}

	private void createButtonClickedOnHomeView() {
		this.homeView.getCreate()
				.setOnMouseClicked(e -> stage.setScene(GeographyApp.getScenes().get(SceneName.CREATE)));
	}

	// Methoden für die Region-View
	private void setUpRegionView() {
		addRegionButtonClickedOnRegionView();
		deleteButtonClickedOnRegionView();
	}

	// Add Events
	private void addRegionButtonClickedOnRegionView() {
		var regionView = this.createView.getRegionView();
		regionView.getAddRegion().setOnMouseClicked(e -> {
			var region = new Region(regionView.getRegionName(), regionView.getRegionPopulation(),
					regionView.getRegionArea(), regionView.getCountry());
			regionView.getCountry().addRegion(region);
			GeographyApp.tableContainer.addRegion(region);
			GeographyApp.buttonController.enableCityPane();
			GeographyApp.buttonController.setCityViewRegion(region);
			regionView.clearFieldRegion();

			this.homeView.getCountRegions()
					.setText(Integer.toString(regionView.getCountry().getRegionCollection().size()));

		});
	}

	// Delete Events
	private void deleteButtonClickedOnRegionView() {
		var regionView = this.createView.getRegionView();
		regionView.getDeleteRegionButton().setOnMouseClicked(e -> {
			deleteRegionFromTable();
		});
	}

	public void deleteRegionFromTable() {
		int row = GeographyApp.tableContainer.tableRegion.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			GeographyApp.tableContainer.tableCity.getItems().clear();
			GeographyApp.tableContainer.tableRegion.getItems().remove(row);

			this.createView.getRegionView().getCountry().getRegionCollection().remove(row);

			var regionView = this.createView.getRegionView();
			var cityView = this.createView.getCityView();

			this.homeView.getCountRegions()
					.setText(Integer.toString(regionView.getCountry().getRegionCollection().size()));

		}
		if (GeographyApp.tableContainer.getTableRegion().getItems().size() == 0) {
			GeographyApp.buttonController.disableCityPane();

			this.homeView.getCountRegions().setText("0");
			this.homeView.getCountCities().setText("0");
		}
	}

	// Methoden für die Country-View
	public void setUpCountryView() {
		addCountryButtonClickedOnCountryView();
		deleteButtonClickedOnCountryView();
		backButtonClickedOnCountryView();
		helpButtonClickedOnCountryView();
	}

	// Help
	private void helpButtonClickedOnCountryView() {
		this.createView.getCountryView().getHelp().setOnMouseClicked(e -> createCountryHelp());
	}

	// Back Button
	private void backButtonClickedOnCountryView() {
		var countryView = this.createView.getCountryView();
		countryView.getBack().setOnMouseClicked(e -> goBack());
	}

	// Add
	public void addCountryButtonClickedOnCountryView() {
		var countryView = this.createView.getCountryView();
		countryView.getAddCountry().setOnMouseClicked(e -> {
			var country = new Country(countryView.getCountryName(), countryView.getCountryPopulation(),
					countryView.getCountryArea(), countryView.getGovernment());
			GeographyApp.map.addCountry(country);
			System.out.println(GeographyApp.map.countryCollection.size());
			this.homeView.getCountCountries().setText(Integer.toString(GeographyApp.map.countryCollection.size()));
			GeographyApp.tableContainer.addCountry(country);
			this.createView.getRegionView().setDisable(false);
			this.createView.getRegionView().setCountry(country);
			countryView.clearFieldCountry();
		});
	}

	// delete
	public void deleteCountryFromTable() {
		int row = GeographyApp.tableContainer.tableCountry.getSelectionModel().getSelectedIndex();

		var regionView = this.createView.getRegionView();
		var cityView = this.createView.getCityView();
		var countryView = this.createView.getCountryView();

		if (row >= 0) {
			GeographyApp.tableContainer.tableRegion.getItems().clear();
			GeographyApp.tableContainer.tableCountry.getItems().remove(row);
			GeographyApp.map.countryCollection.remove(row);

			this.homeView.getCountCountries().setText(Integer.toString(GeographyApp.map.countryCollection.size()));

		}
		if (GeographyApp.tableContainer.getTableCountry().getItems().size() == 0) {
			GeographyApp.buttonController.disableRegionPane();
			GeographyApp.buttonController.disableCityPane();

			regionView.clearFieldRegion();
			cityView.clearFieldCity();
			countryView.clearFieldCountry();

			this.homeView.getCountCountries().setText("0");
			this.homeView.getCountRegions().setText("0");
			this.homeView.getCountCities().setText("0");
		}
	}

	// delete
	private void deleteButtonClickedOnCountryView() {
		var countryView = this.createView.getCountryView();
		countryView.getDeleteCountryButton().setOnMouseClicked(e -> {
			deleteCountryFromTable();
		});
	}

	// Methode für die City View
	public void setUpCityView() {
		addCityButtonClickedOnCityView();
		deleteButtonClickedOnCityView();
		forwardButtonClickedOnCityView();
	}

	// Add
	public void addCityButtonClickedOnCityView() {
		var cityView = this.createView.getCityView();
		cityView.getAddCity().setOnMouseClicked(e -> {

			var city = new City(cityView.getCityName(), cityView.getCityPopulation(), cityView.getCityArea());
			cityView.getRegion().addCity(city);
			GeographyApp.tableContainer.addCity(city);

			cityView.clearFieldCity();
			var cityCount = Integer.parseInt(this.homeView.getCountCities().getText()) + 1;
			this.homeView.getCountCities().setText(Integer.toString(cityCount));

		});
	}

	// Delete
	private void deleteButtonClickedOnCityView() {
		var cityView = this.createView.getCityView();
		cityView.getDeleteCityButton().setOnMouseClicked(e -> {
			deleteCityFromTable();
		});
	}

	public void deleteCityFromTable() {
		int row = GeographyApp.tableContainer.tableCity.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			GeographyApp.tableContainer.tableCity.getItems().remove(row);
			this.createView.getCityView().getRegion().getCityCollection().remove(row);

			var cityView = this.createView.getCityView();
			this.homeView.getCountCities().setText(Integer.toString(cityView.getRegion().getCityCollection().size()));
		}
	}

	private void forwardButtonClickedOnCityView() {
		var cityView = this.createView.getCityView();

		cityView.getNext().setOnMouseClicked(e -> goNext());
	}

	// Methode mit der Inhalt von Help
	public void createReadHelp(MouseEvent e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Need some help here?");
		alert.setHeaderText(null);
		alert.setContentText("Here you can comfortably view your recorded data. "
				+ "Additionally you can delete single regions or cities. If you want to edit any data, "
				+ "you can click the edit button. Then the tables and text fields"
				+ " become editable. When you save, everything is updated. ");

		alert.showAndWait();
	}

	public void createCountryHelp() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Need some help here?");
		alert.setHeaderText(null);
		alert.setContentText("Here you can create a country and its related regions and cities."
				+ "You can also comfortably view your recorded data."
				+ "\n\nCreate A Record: To enter a record, you can simply fill in the respective text fields. With a click on "
				+ "the \"Add-Icon\" you can confirm your entry or add it to the list. If you want to delete a line, you can "
				+ "click on it and select the \"Delete-Icon\""
				+ "\n\nView Your Records: With a simple click on a recorded country, the corresponding regions appear. A "
				+ "click on the desired region will show you all the cities belonging to it."
				+ "\n\nEdit Your Records: To edit a captured line, simply double-click on it. This allows you to overwrite "
				+ "the entered data.");

		alert.showAndWait();
	}

	// Methode für die aktivierung/deaktivierung der City View
	public void enableCityPane() {
		this.createView.getCityView().setDisable(false);
	}

	public void disableCityPane() {
		this.createView.getCityView().setDisable(true);
	}

	// Methode für die aktivierung/deaktivierung der Region View
	public void enableRegionPane() {
		this.createView.getRegionView().setDisable(false);
	}

	public void disableRegionPane() {
		this.createView.getRegionView().setDisable(true);
	}

	public void setCityViewRegion(Region region) {
		this.createView.getCityView().setRegion(region);
	}

	public void setScene(Scene scene) {
		this.stage.setScene(scene);
	}

	// geht von Create auf Home
	public void goNext() {
		stage.setScene(GeographyApp.getScenes().get(SceneName.HOME));
	}

	// geht von ReadView zurück zum Home
	public void goBackHome(MouseEvent e) {
		stage.setScene(GeographyApp.getScenes().get(SceneName.HOME));
	}

	// geht von CreateView zurück zum Home
	public void goBack() {
		stage.setScene(GeographyApp.getScenes().get(SceneName.HOME));
	}

}
