package geography.view;

import geography.GeographyApp;
import geography.model.City;
import geography.model.Country;
import geography.model.Region;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class TableContainer {

	// Variablen für die tables
	public TableView<Region> tableRegion = new TableView<Region>();
	public TableView<City> tableCity = new TableView<City>();
	public TableView<Country> tableCountry = new TableView<Country>();

	// Konstruktor
	public TableContainer() {
		setUpCountryTable();
		setUpRegionTable();
		setUpCityTable();
	}

	// Methode zur Erzeugung der Country Table
	private void setUpCountryTable() {
		TableColumn<Country, String> name = new TableColumn<Country, String>("Country");
		TableColumn<Country, Integer> population = new TableColumn<Country, Integer>("Population");
		TableColumn<Country, Integer> area = new TableColumn<Country, Integer>("Area");
		TableColumn<Country, String> government = new TableColumn<Country, String>("Government");

		name.setPrefWidth(150);
		name.setResizable(true);
		population.setPrefWidth(150);
		population.setResizable(true);
		area.setPrefWidth(150);
		area.setResizable(true);
		government.setPrefWidth(150);
		government.setResizable(true);
		population.setSortable(false);
		area.setSortable(false);

		tableCountry.setEditable(true);

		name.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Zelle editierbar for CountryName
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(t -> {
			((Country) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
		});

		population.setCellValueFactory(new PropertyValueFactory<>("population"));

		// Zelle editierbar for CountryPopulation
		population.setCellFactory(TextFieldTableCell.<Country, Integer>forTableColumn(new IntegerStringConverter()));
		population.setOnEditCommit((EventHandler<CellEditEvent<Country, Integer>>) t -> ((Country) t.getTableView()
				.getItems().get(t.getTablePosition().getRow())).setPopulation(t.getNewValue()));

		area.setCellValueFactory(new PropertyValueFactory<>("area"));

		// Zelle editierbar for CountryArea
		area.setCellFactory(TextFieldTableCell.<Country, Integer>forTableColumn(new IntegerStringConverter()));
		area.setOnEditCommit((EventHandler<CellEditEvent<Country, Integer>>) t -> ((Country) t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setArea(t.getNewValue()));

		government.setCellValueFactory(new PropertyValueFactory<>("government"));

		// Zelle editierbar für Government
		government.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),
				FXCollections.observableArrayList("Diktatur", "Demokratie", "Monarchie"))); // halb
		// funktionierend
		government.setOnEditCommit((EventHandler<CellEditEvent<Country, String>>) t -> ((Country) t.getTableView()
				.getItems().get(t.getTablePosition().getRow())).setGovernment(t.getNewValue()));

		tableCountry.getColumns().add(name);
		tableCountry.getColumns().add(population);
		tableCountry.getColumns().add(area);
		tableCountry.getColumns().add(government);
		tableCountry.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	// Methode zur Erzeugung der Region Table
	private void setUpRegionTable() {
		TableColumn<Region, String> rName = new TableColumn<Region, String>("Region");
		TableColumn<Region, Integer> rPopulation = new TableColumn<Region, Integer>("Population");
		TableColumn<Region, Integer> rArea = new TableColumn<Region, Integer>("Area");

		rName.setPrefWidth(150);
		rName.setResizable(false);
		rPopulation.setPrefWidth(150);
		rPopulation.setResizable(false);
		rArea.setPrefWidth(150);
		rArea.setResizable(false);
		rPopulation.setSortable(false);
		rArea.setSortable(false);

		rName.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Zelle editierbar for RegionName
		rName.setCellFactory(TextFieldTableCell.forTableColumn());
		rName.setOnEditCommit((EventHandler<CellEditEvent<Region, String>>) t -> ((Region) t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setName(t.getNewValue()));

		rPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));

		// Zelle editierbar for RegionPopulation
		rPopulation.setCellFactory(TextFieldTableCell.<Region, Integer>forTableColumn(new IntegerStringConverter()));
		rPopulation.setOnEditCommit((EventHandler<CellEditEvent<Region, Integer>>) t -> ((Region) t.getTableView()
				.getItems().get(t.getTablePosition().getRow())).setPopulation(t.getNewValue()));

		rArea.setCellValueFactory(new PropertyValueFactory<>("area"));

		// Zelle editierbar for RegionPopulation
		rArea.setCellFactory(TextFieldTableCell.<Region, Integer>forTableColumn(new IntegerStringConverter()));
		rArea.setOnEditCommit((EventHandler<CellEditEvent<Region, Integer>>) t -> ((Region) t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setArea(t.getNewValue()));
		tableRegion.setEditable(true);
		tableRegion.getColumns().add(rName);
		tableRegion.getColumns().add(rPopulation);
		tableRegion.getColumns().add(rArea);
		tableRegion.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	// Methode zur Erzeugung der City Table
	private void setUpCityTable() {
		TableColumn<City, String> cName = new TableColumn<City, String>("City");
		TableColumn<City, Integer> cPopulation = new TableColumn<City, Integer>("Population");
		TableColumn<City, Integer> cArea = new TableColumn<City, Integer>("Area");

		cName.setPrefWidth(150);
		cName.setResizable(false);
		cPopulation.setPrefWidth(150);
		cPopulation.setResizable(false);
		cArea.setPrefWidth(150);
		cArea.setResizable(false);
		cPopulation.setSortable(false);
		cArea.setSortable(false);
		tableCity.setEditable(true);

		cName.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Zelle editierbar for CityName
		cName.setCellFactory(TextFieldTableCell.forTableColumn());
		cName.setOnEditCommit(
				t -> ((City) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue()));

		cPopulation.setCellValueFactory(new PropertyValueFactory<>("population"));

		// Zelle editierbar for CityPopulation
		cPopulation.setCellFactory(TextFieldTableCell.<City, Integer>forTableColumn(new IntegerStringConverter()));
		cPopulation.setOnEditCommit((EventHandler<CellEditEvent<City, Integer>>) t -> ((City) t.getTableView()
				.getItems().get(t.getTablePosition().getRow())).setPopulation(t.getNewValue()));

		cArea.setCellValueFactory(new PropertyValueFactory<>("area"));

		// Zelle editierbar for CityArea
		cArea.setCellFactory(TextFieldTableCell.<City, Integer>forTableColumn(new IntegerStringConverter()));
		cArea.setOnEditCommit((EventHandler<CellEditEvent<City, Integer>>) t -> ((City) t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setArea(t.getNewValue()));

		tableCity.getColumns().add(cName);
		tableCity.getColumns().add(cPopulation);
		tableCity.getColumns().add(cArea);
		tableCity.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	// Methode die Country/Region und City in Table addiert
	public void addCity(City city) {
		this.tableCity.getItems().add(city);
	}

	public void addRegion(Region region) {
		this.tableRegion.getItems().add(region);
	}

	public void addCountry(Country country) {
		this.tableCountry.getItems().add(country);
	}

	// getter und setter
	public TableView<Region> getTableRegion() {
		return tableRegion;
	}

	public void setTableRegion(TableView<Region> tableRegion) {
		this.tableRegion = tableRegion;
	}

	public TableView<City> getTableCity() {
		return tableCity;
	}

	public void setTableCity(TableView<City> tableCity) {
		this.tableCity = tableCity;
	}

	public TableView<Country> getTableCountry() {
		return tableCountry;
	}

	public void setTableCountry(TableView<Country> tableCountry) {
		this.tableCountry = tableCountry;
	}

}
