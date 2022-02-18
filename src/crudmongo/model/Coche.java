package crudmongo.model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Coche {
	private String marca;
	private String tipo;
	private String color;
	private String modelo;
	private transient HBox actions;

	public Coche() {
	}

	public Coche(String marca, String tipo, String color, String modelo) {
		this.marca = marca;
		this.tipo = tipo;
		this.color = color;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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
