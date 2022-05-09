package geography.view;

import geography.GeographyApp;
import geography.model.Country;
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

public class RegionView extends GridPane {
	
	// model
	private Country country;

	// Elemente für controller
	public Button deleteRegionButton;
	public Button addRegion;
	public TextField txtNameRegion;
	public TextField txtAreaRegion;
	public TextField txtPopulationRegion;

	// Elemente für ChangeListener
	private boolean rNameValid;
	private boolean rPopulationVaild;
	private boolean rAreaValid;

	// Icons
	private ImageView addRegionImage;
	private Image addIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Add_Icon.png"));

	private ImageView deleteRegionImage;
	private Image deleteIcon = new Image(getClass().getResourceAsStream("/geography/view/images/Delete_Icon.png"));

	public RegionView() {
		Label lblTitle = new Label("About Your Regions");
		Label lblNameRegion = new Label("Name Of Region:");						this.txtNameRegion = new TextField();
		Label lblPopulationRegion = new Label("Enter Population:");				this.txtPopulationRegion = new TextField();
		Label lblAreaRegion = new Label("Enter Area:");							this.txtAreaRegion = new TextField();

		this.addRegion = new Button();
		addRegion.setDisable(true);
		this.addRegionImage = new ImageView(this.addIcon);
		addRegionImage.setFitHeight(20);
		addRegionImage.setFitWidth(18);
		addRegion.setGraphic(addRegionImage);

		this.deleteRegionButton = new Button();
		this.deleteRegionImage = new ImageView(this.deleteIcon);
		deleteRegionImage.setFitHeight(20);
		deleteRegionImage.setFitWidth(18);
		deleteRegionButton.setGraphic(deleteRegionImage);

		
		add(lblTitle, 0, 1);
		add(lblNameRegion, 0, 2);			add(txtNameRegion, 1, 2);
		add(lblPopulationRegion, 0, 3);		add(txtPopulationRegion, 1, 3);
		add(lblAreaRegion, 0, 4);			add(txtAreaRegion, 1, 4);
		add(deleteRegionButton, 0, 5);		add(addRegion, 1, 5);
		add(GeographyApp.tableContainer.getTableRegion(), 0, 6);
	
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		GridPane.setHalignment(addRegion, HPos.RIGHT);
		GridPane.setColumnSpan(lblTitle, 2);
		GridPane.setColumnSpan(GeographyApp.tableContainer.getTableRegion(), 2);


		setDisable(true);
		addRegion.setId("circleButton");
		deleteRegionButton.setId("circleButton");
		setId("regionPane");
		lblTitle.setId("regionPaneTitle");

		setAlignment(Pos.CENTER);
		setPadding(new Insets(20));
		setVgap(36);
		setHgap(100);

		// ChangeListener TextField
		txtNameRegion.textProperty()
				.addListener((observable, oldValue, newValue) -> rValidateName(newValue));
		txtPopulationRegion.textProperty()
				.addListener((observable, oldValue, newValue) -> rValidatePopulation(newValue));
		txtAreaRegion.textProperty()
				.addListener((observable, oldValue, newValue) -> rValidateArea(newValue));
	}

	//Getter für Buttons
	public Button getDeleteRegionButton() {
		return deleteRegionButton;
	}

	public Button getAddRegion() {
		return addRegion;
	}

	// Getter - Setter für Objekte
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	// Getter TextFields Region
	public String getRegionName() {
		return this.txtNameRegion.getText();
	}

	public int getRegionPopulation() {
		return Integer.parseInt(this.txtPopulationRegion.getText());
	}

	public int getRegionArea() {
		return Integer.parseInt(this.txtAreaRegion.getText());
	}
	
	//Validate für ChangeListener
	private void rValidateName(String newValue) {
		boolean valid = isValidNameR(newValue);

		this.txtNameRegion.getStyleClass().remove("valid");
		this.txtNameRegion.getStyleClass().remove("invalid");

		if (valid) {
			this.txtNameRegion.getStyleClass().add("valid");
		} else {
			this.txtNameRegion.getStyleClass().add("invalid");
		}

		this.rNameValid = valid;
		enableDisableButtonAddRegion();
	}
	
	private boolean isValidNameR(String newValue) {
		String name = newValue;
		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c) && !Character.isSpace(c)) {
				return false;
			}
		}
		return true;
	}

	private void rValidatePopulation(String newValue) {
		boolean valid = isValidPopulationR(newValue);

		this.txtPopulationRegion.getStyleClass().remove("valid");
		this.txtPopulationRegion.getStyleClass().remove("invalid");

		if (valid) {
			this.txtPopulationRegion.getStyleClass().add("valid");
		} else {
			this.txtPopulationRegion.getStyleClass().add("invalid");
		}

		this.rPopulationVaild = valid;
		enableDisableButtonAddRegion();
	}

	private boolean isValidPopulationR(String newValue) {
		String population = newValue;
		char[] chars = population.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private void rValidateArea(String newValue) {
		boolean valid = isValidAreaR(newValue);

		this.txtAreaRegion.getStyleClass().remove("valid");
		this.txtAreaRegion.getStyleClass().remove("invalid");

		if (valid) {
			this.txtAreaRegion.getStyleClass().add("valid");
		} else {
			this.txtAreaRegion.getStyleClass().add("invalid");
		}
		this.rAreaValid = valid;
		enableDisableButtonAddRegion();
	}

	private boolean isValidAreaR(String newValue) {
		String area = newValue;
		char[] chars = area.toCharArray();

		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	public void clearFieldRegion() {
		this.txtNameRegion.clear();
		this.txtPopulationRegion.clear();
		this.txtAreaRegion.clear();
		this.txtNameRegion.requestFocus();
	}

	// Aktivierung - Deaktivierung "Add"
	private void enableDisableButtonAddRegion() {

		boolean valid = false;
		if (rNameValid && rPopulationVaild && rAreaValid)
			this.addRegion.setDisable(valid);
		else
			this.addRegion.setDisable(!valid);
	}



}
