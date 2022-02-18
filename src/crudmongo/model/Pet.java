package crudmongo.model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Pet {

	private String name;
	private String type;
	private String breed;
	private int age;
	private transient HBox actions;

	public Pet() {
	}

	public Pet(String name, String type, String breed, int age) {
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private void setActions() {
		try {
			this.actions = (HBox) FXMLLoader.load(getClass().getResource("Actions.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public HBox getActions() {
		if (this.actions == null) {
			setActions();
		}
		return this.actions;
	}

}
