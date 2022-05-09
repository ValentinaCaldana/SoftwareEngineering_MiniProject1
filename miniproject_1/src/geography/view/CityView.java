package geography.view;

import geography.GeographyApp;
import geography.model.Region;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CityView extends GridPane {

	// model
	private Region region;

	// Elemente für controller
	public Button help;
	public Button addCity;
	public Button deleteCity;
	public Button next;
	public TextField txtNameCity;
	public TextField txtAreaCity;
	public TextField txtPopulationCity;

	// Elemente für ChangeListener
	private boolean cNameValid;
	private boolean cPopulationVaild;
	private boolean cAreaValid;

	// Icons
	private ImageView helpImage;
	private Image helpIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Help_Icon.png"));

	private ImageView addCityImage;
	private Image addIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Add_Icon.png"));

	private ImageView deleteCityImage;
	private Image deleteIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Delete_Icon.png"));

	private ImageView nextImage;
	private Image nextIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Next_Icon.png"));

	public CityView() {
		Label lblTitle = new Label("About Your Cities");
		Label lblNameCity = new Label("Name Of City:");
		this.txtNameCity = new TextField();
		Label lblPopulationCity = new Label("Enter Population:");
		this.txtPopulationCity = new TextField();
		Label lblAreaCity = new Label("Enter Area:");
		this.txtAreaCity = new TextField();

		this.addCity = new Button();
		addCity.setDisable(true);
		this.addCityImage = new ImageView(this.addIcon);
		addCityImage.setFitHeight(20);
		addCityImage.setFitWidth(18);
		addCity.setGraphic(addCityImage);

		this.deleteCity = new Button();
		this.deleteCityImage = new ImageView(this.deleteIcon);
		deleteCityImage.setFitHeight(20);
		deleteCityImage.setFitWidth(18);
		deleteCity.setGraphic(deleteCityImage);

		this.next = new Button();
		next.setDisable(true);
		this.nextImage = new ImageView(this.nextIcon);
		nextImage.setFitHeight(20);
		nextImage.setFitWidth(20);
		next.setGraphic(nextImage);

		add(lblTitle, 0, 3);
		add(lblNameCity, 0, 5);					add(txtNameCity, 1, 5);								
		add(lblPopulationCity, 0, 6);			add(txtPopulationCity, 1, 6);									
		add(lblAreaCity, 0, 7);					add(txtAreaCity, 1, 7);
		add(deleteCity, 0, 8);					add(addCity, 1, 8);							
		add(GeographyApp.tableContainer.getTableCity(), 0, 9);
		add(next, 1, 10);
		GridPane.setColumnSpan(lblTitle, 2);
		GridPane.setRowSpan(lblTitle, 2);
		GridPane.setColumnSpan(GeographyApp.tableContainer.getTableCity(), 2);
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		GridPane.setHalignment(addCity, HPos.RIGHT);
		GridPane.setHalignment(next, HPos.RIGHT);

		deleteCity.setId("circleButton");
		addCity.setId("circleButton");
		next.setId("ovalButton");
		setId("cityPane");
		lblTitle.setId("cityPaneTitle");

		setAlignment(Pos.CENTER);
		setPadding(new Insets(20));
		setDisable(true);
		setVgap(35);
		setHgap(100);

		// ChangeListener TextField
		txtNameCity.textProperty().addListener((observable, oldValue, newValue) -> cValidateName(newValue));
		txtPopulationCity.textProperty().addListener((observable, oldValue, newValue) -> cValidatePopulation(newValue));
		txtAreaCity.textProperty().addListener((observable, oldValue, newValue) -> cValidateArea(newValue));

	}

	// Getter - Setter für Region Objekte
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	// Getter für Buttons
	public Button getNext() {
		return next;
	}

	public Button getAddCity() {
		return addCity;
	}

	public Button getDeleteCityButton() {
		return deleteCity;
	}

	// Getter für TextFields
	public String getCityName() {
		return this.txtNameCity.getText();
	}

	public int getCityPopulation() {
		return Integer.parseInt(this.txtPopulationCity.getText());
	}

	public int getCityArea() {
		return Integer.parseInt(this.txtAreaCity.getText());
	}

	// Validate für ChangeListener
	private boolean isValidNameC(String newValue) {
		String name = newValue;
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c) && !Character.isSpace(c)) {
				return false;
			}
		}
		return true;
	}

	private void cValidateArea(String newValue) {
		boolean valid = isValidAreaC(newValue);

		this.txtAreaCity.getStyleClass().remove("valid");
		this.txtAreaCity.getStyleClass().remove("invalid");

		if (valid) {
			this.txtAreaCity.getStyleClass().add("valid");
		} else {
			this.txtAreaCity.getStyleClass().add("invalid");
		}
		this.cAreaValid = valid;
		enableDisableButtonNext();
		enableDisableButtonAddCity();
	}

	private boolean isValidAreaC(String newValue) {
		String area = newValue;
		char[] chars = area.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private void cValidateName(String newValue) {
		boolean valid = isValidNameC(newValue);

		this.txtNameCity.getStyleClass().remove("valid");
		this.txtNameCity.getStyleClass().remove("invalid");

		if (valid) {
			this.txtNameCity.getStyleClass().add("valid");
		} else {
			this.txtNameCity.getStyleClass().add("invalid");
		}

		this.cNameValid = valid;
		enableDisableButtonNext();
		enableDisableButtonAddCity();
	}

	private void cValidatePopulation(String newValue) {
		boolean valid = isValidPopulationC(newValue);

		this.txtPopulationCity.getStyleClass().remove("valid");
		this.txtPopulationCity.getStyleClass().remove("invalid");

		if (valid) {
			this.txtPopulationCity.getStyleClass().add("valid");
		} else {
			this.txtPopulationCity.getStyleClass().add("invalid");
		}

		this.cPopulationVaild = valid;
		enableDisableButtonNext();
		enableDisableButtonAddCity();
	}

	private boolean isValidPopulationC(String newValue) {
		String population = newValue;
		char[] chars = population.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public void clearFieldCity() {
		this.txtNameCity.clear();
		this.txtPopulationCity.clear();
		this.txtAreaCity.clear();
		this.txtNameCity.requestFocus();
	}

	// Aktivierung - Deaktivierung "Add"
	private void enableDisableButtonAddCity() {
		boolean valid = false;
		if (cNameValid && cPopulationVaild && cAreaValid)
			this.addCity.setDisable(valid);
		else
			this.addCity.setDisable(!valid);
	}

	// Aktivierung - Deaktivierung "Next"
	private void enableDisableButtonNext() {

		boolean valid = false;
		if (cNameValid && cPopulationVaild && cAreaValid)
			this.next.setDisable(valid);
		else
			this.next.setDisable(!valid);
	}

	public void deleteCityFromTable() {

		int row = GeographyApp.tableContainer.tableCity.getSelectionModel().getSelectedIndex();
		if (row >= 0) {
			GeographyApp.tableContainer.tableCity.getItems().remove(row);
			this.region.getCityCollection().remove(row);
		}
	}

}
