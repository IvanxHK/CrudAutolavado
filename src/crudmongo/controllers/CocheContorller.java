package crudmongo.controllers;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import crudmongo.model.Coche;
import crudmongo.model.Pet;
import java.util.ArrayList;

public class CocheContorller {

	public static ArrayList<Coche> getAll() {
		DBObject keys = new BasicDBObject("_id", 0);
		DBObject ref = new BasicDBObject();
		DBCursor cursor = MongoController.collection.find(ref, keys);
		ArrayList<Coche> pets = new ArrayList<>();
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			pets.add(gson.fromJson(cursor.next().toString(), Coche.class));
		}
		return pets;
	}

	public static void insert(Coche newPet) {
		DBObject object = MongoController.toDBObject(newPet);
		MongoController.collection.insert(object);
	}

	public static void update(Coche oldPet, Coche newPet) {
		DBObject oldObject = MongoController.toDBObject(oldPet);
		DBObject newObject = MongoController.toDBObject(newPet);
		MongoController.collection.update(oldObject, newObject);
	}

	public static void delete(Coche petToDelete) {
		DBObject object = MongoController.toDBObject(petToDelete);
		MongoController.collection.remove(object);
	}
}

