package crudmongo.controllers;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import crudmongo.model.Pet;
import java.util.ArrayList;

public class PetController {

	public static ArrayList<Pet> getAll() {
		DBObject keys = new BasicDBObject("_id", 0);
		DBObject ref = new BasicDBObject();
		DBCursor cursor = MongoController.collection.find(ref, keys);
		ArrayList<Pet> pets = new ArrayList<>();
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			pets.add(gson.fromJson(cursor.next().toString(), Pet.class));
		}
		return pets;
	}

	public static void insert(Pet newPet) {
		DBObject object = MongoController.toDBObject(newPet);
		MongoController.collection.insert(object);
	}

	public static void update(Pet oldPet, Pet newPet) {
		DBObject oldObject = MongoController.toDBObject(oldPet);
		DBObject newObject = MongoController.toDBObject(newPet);
		MongoController.collection.update(oldObject, newObject);
	}

	public static void delete(Pet petToDelete) {
		DBObject object = MongoController.toDBObject(petToDelete);
		MongoController.collection.remove(object);
	}
}
