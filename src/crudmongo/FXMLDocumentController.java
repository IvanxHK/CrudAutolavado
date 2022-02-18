package crudmongo;

import crudmongo.controllers.CocheContorller;
import crudmongo.controllers.MongoController;
import crudmongo.controllers.PetController;
import crudmongo.model.Coche;
import crudmongo.model.Pet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class FXMLDocumentController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Coche> tbPet;

	@FXML
	private TableColumn<Pet, String> clName;

	@FXML
	private TableColumn<Pet, String> clType;

	@FXML
	private TableColumn<Pet, String> clBreed;

	@FXML
	private TableColumn<Pet, Integer> clAge;

	@FXML
	private TableColumn<Pet, HBox> clActions;

	@FXML
	private TextField txtName;

	@FXML
	private ComboBox<String> txtType;

	@FXML
	private TextField txtBreed;

	@FXML
	private TextField txtAge;

	@FXML
	private Button btnAdd;

	private Coche oldPet;

	@FXML
	void initialize() {
		initTable();
		initCombos();
		MongoController.getConnection();
		setPetsInTable();
		btnAdd.setOnAction(e -> {
			add();
		});
	}

	private void add() {
		if (inputsFilled()) {
			if (oldPet == null) {
				CocheContorller.insert(getPet());
			} else {
				CocheContorller.update(oldPet, getPet());
				oldPet = null;
			}
			setPetsInTable();
			clearInputs();
		}
	}

	private Coche getPet() {
		Coche pet = new Coche();
		String name = txtName.getText();
		String type = txtType.getValue();
		String breed = txtBreed.getText();
		String modelo = txtAge.getText();

		pet.setMarca(name);
		pet.setTipo(type);
		pet.setColor(breed);
		pet.setModelo(modelo);

		return pet;
	}

	private void putActions() {
		tbPet.getItems().forEach(pet -> {
			// Modificar
			((Button) pet.getActions().getChildren().get(0)).setOnAction(event -> {
				modify(pet, event);
			});

			// Eliminar
			((Button) pet.getActions().getChildren().get(1)).setOnAction(event -> {
				delete(pet, event);
			});
		});
	}

	private void modify(Coche pet, ActionEvent event) {
		oldPet = pet;
		setPetInForm();
	}

	private void delete(Coche pet, ActionEvent event) {
		CocheContorller.delete(pet);
		setPetsInTable();
	}

	private void setPetInForm() {
		txtName.setText(oldPet.getMarca());
		txtBreed.setText(oldPet.getColor());
		txtType.setValue(oldPet.getTipo());
		txtAge.setText(oldPet.getModelo());
	}

	private void clearInputs(){
		txtName.setText("");
		txtBreed.setText("");
		txtType.setValue("");
		txtAge.setText("");
}

	private void initTable() {
		clName.setCellValueFactory(new PropertyValueFactory<>("marca"));
		clType.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		clBreed.setCellValueFactory(new PropertyValueFactory<>("color"));
		clAge.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		clActions.setCellValueFactory(new PropertyValueFactory<>("actions"));
	}

	private void setPetsInTable() {
		tbPet.getItems().setAll(CocheContorller.getAll());
		putActions();
	}

	private void initCombos() {
		final String[] types = {"Coche", "Camioneta"};
		txtType.getItems().setAll(types);
	}

	private boolean inputsFilled() {
		return txtName.getText().length() > 0
				&& txtAge.getText().length() > 0
				&& txtBreed.getText().length() > 0
				&& txtType.getValue().length() > 0;
	}
}
