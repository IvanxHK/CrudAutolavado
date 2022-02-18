package crudmongo.controllers;

import crudmongo.model.Pet;

public class Test {
	
	public static void main(String[] args) {
		MongoController.getConnection();
//		PetController pc = new PetController();
//		pc.show();
		Pet pet = new Pet("negro", "Perro", "chihuhua", 7);
		PetController.insert(pet);
		System.out.println("inserted2");
	}
}
