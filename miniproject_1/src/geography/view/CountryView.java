package geography.view;

import geography.GeographyApp;
import geography.model.Country;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CountryView extends GridPane {
	
	// model
	private Country country;
	
	// Elemente für controller
	public TextField txtCountry;
	public TextField txtArea;
	public TextField txtPopulation;
	public ComboBox<String> government;
	public Button help;
	public Button back;
	public Button addCountry;
	public Button deleteCountry;

	// Elemente für ChangeListener
	private boolean nameValid;
	private boolean populationVaild;
	private boolean areaValid;

	// Icons
	private ImageView helpImage;
	private Image helpIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Help_Icon.png"));

	private ImageView backImage;
	private Image backIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Back_Icon.png"));

	private ImageView addCountryImage;
	private Image addIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Add_Icon.png"));

	private ImageView deleteCountryImage;
	private Image deleteIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Delete_Icon.png"));

	public CountryView() {

		Label lblTitle = new Label("Create And Read About Countries");
		Label lblCountry = new Label("Name Of Country:");					this.txtCountry = new TextField();
		Label lblPopulation = new Label("Enter Population:");				this.txtPopulation = new TextField();
		Label lblArea = new Label("Enter Area:");							this.txtArea = new TextField();
		Label lblGovernment = new Label("Choose Government:");				this.government = new ComboBox<String>();
		government.getItems().addAll("Demokratie", "Diktatur", "Monarchie");
		government.setValue("Demokratie");

		this.help = new Button();
		this.helpImage = new ImageView(this.helpIcon);
		helpImage.setFitHeight(40);
		helpImage.setFitWidth(40);
		help.setGraphic(helpImage);

		this.back = new Button();
		this.backImage = new ImageView(this.backIcon);
		backImage.setFitHeight(20);
		backImage.setFitWidth(30);
		back.setGraphic(backImage);

		this.addCountry = new Button();
		this.addCountryImage = new ImageView(this.addIcon);
		addCountryImage.setFitHeight(20);
		addCountryImage.setFitWidth(18);
		addCountry.setGraphic(addCountryImage);
		addCountry.setDisable(true);

		this.deleteCountry = new Button();
		this.deleteCountryImage = new ImageView(this.deleteIcon);
		deleteCountryImage.setFitHeight(20);
		deleteCountryImage.setFitWidth(18);
		deleteCountry.setGraphic(deleteCountryImage);
		deleteCountry.setDisable(false);

		add(help, 0, 0);
		add(lblTitle, 0, 1);
		add(lblCountry, 0, 2);			add(txtCountry, 1, 2);
		add(lblPopulation, 0, 3);		add(txtPopulation, 1, 3);
		add(lblArea, 0, 4);				add(txtArea, 1, 4);
		add(lblGovernment, 0, 5);		add(government, 1, 5);
		add(deleteCountry, 0, 6);		add(addCountry, 1, 6);
		add(GeographyApp.tableContainer.getTableCountry(), 0, 7);
		add(back, 0, 8);

		GridPane.setHalignment(lblTitle, HPos.CENTER);
		GridPane.setHalignment(addCountry, HPos.RIGHT);
		GridPane.setHalignment(help, HPos.CENTER);
		GridPane.setColumnSpan(lblTitle, 2);
		GridPane.setColumnSpan(GeographyApp.tableContainer.getTableCountry(), 2);
		GridPane.setColumnSpan(help, 2);

		help.setId("circleButton");
		back.setId("ovalButton");
		back.setId("ovalButton");
		addCountry.setId("circleButton");
		deleteCountry.setId("circleButton");
		setId("countryPane");
		lblTitle.setId("countryPaneTitle");

		setAlignment(Pos.CENTER);
		setPadding(new Insets(20));
		setVgap(29);
		setHgap(100);

		// ChangeListener TextField
		this.txtCountry.textProperty().addListener((observable, oldvalue, newvalue) -> validateName(newvalue));
		this.txtPopulation.textProperty().addListener((observable, oldvalue, newvalue) -> validatePopulation(newvalue));
		this.txtArea.textProperty().addListener((observable, oldvalue, newvalue) -> validateArea(newvalue));
	}

	//Getter für TextFields
	public TextField getTxtArea() {
		return txtArea;
	}

	public TextField getTxtCountry() {
		return txtCountry;
	}

	public TextField getTxtPopulation() {
		return txtPopulation;
	}
	
	// Getter Country Objekt
	public String getCountryName() {
		return this.txtCountry.getText();
	}

	public int getCountryPopulation() {
		return Integer.parseInt(this.txtPopulation.getText());
	}

	public int getCountryArea() {
		return Integer.parseInt(this.txtArea.getText());
	}

	public String getGovernment() {
		return this.government.getValue();
	}

	//Getter für Buttons
	public Button getHelp() {
		return help;
	}

	public Button getBack() {
		return back;
	}

	public Button getAddCountry() {
		return addCountry;
	}
	
	public Button getDeleteCountryButton() {
		return deleteCountry;
	}

	//Validate für ChangeListener
	private void validatePopulation(String newValue) {
		boolean valid = isValidPopulation(newValue);

		this.txtPopulation.getStyleClass().remove("valid");
		this.txtPopulation.getStyleClass().remove("invalid");

		if (valid) {
			this.txtPopulation.getStyleClass().add("valid");
		} else {
			this.txtPopulation.getStyleClass().add("invalid");
		}

		this.populationVaild = valid;
		enableDisableButtonAdd();
	}

	private void validateName(String newValue) {
		boolean valid = isValidName(newValue);

		this.txtCountry.getStyleClass().remove("valid");
		this.txtCountry.getStyleClass().remove("invalid");

		if (valid) {
			this.txtCountry.getStyleClass().add("valid");
		} else {
			this.txtCountry.getStyleClass().add("invalid");
		}

		this.nameValid = valid;
		enableDisableButtonAdd();
	}

	private boolean isValidName(String newValue) {
		String name = newValue;
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c) && !Character.isSpace(c)) {
				return false;
			}
		}
		return true;
	}

	private void enableDisableButtonAdd() {

		boolean valid = false;
		if (nameValid && populationVaild && areaValid)
			this.addCountry.setDisable(valid);
		else
			this.addCountry.setDisable(!valid);
	}

	private boolean isValidPopulation(String newValue) {
		String population = newValue;
		char[] chars = population.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidArea(String newValue) {
		String area = newValue;
		char[] chars = area.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private void validateArea(String newValue) {
		boolean valid = isValidArea(newValue);

		this.txtArea.getStyleClass().remove("valid");
		this.txtArea.getStyleClass().remove("invalid");

		if (valid) {
			this.txtArea.getStyleClass().add("valid");
		} else {
			this.txtArea.getStyleClass().add("invalid");
		}
		this.areaValid = valid;
		enableDisableButtonAdd();
	}

	public void clearFieldCountry() {
		this.txtCountry.clear();
		this.txtPopulation.clear();
		this.txtArea.clear();
		this.txtCountry.requestFocus();
	}

}
