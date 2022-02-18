package crudmongo.controllers;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoController {

	public static Mongo mongo;
	public static DB db;
	public static DBCollection collection;

	public static void getConnection() {
		try {
			MongoController.mongo = new Mongo("localhost", 27017);
			db = mongo.getDB("Autolavado");
			collection = db.getCollection("coches");
		} catch (UnknownHostException ex) {
			Logger.getLogger(MongoController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static <T> DBObject toDBObject(T object) {
		Gson gson = new Gson();
		String bson = gson.toJson(object);
		DBObject dbObject = (DBObject) JSON.parse(bson);
		return dbObject;
	}

}
