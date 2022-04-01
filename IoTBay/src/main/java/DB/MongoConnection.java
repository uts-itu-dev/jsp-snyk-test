package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Setup a connection to MongoDB.
 * 
 * Holds information about a connection to MongoDB.
 *
 * @author Michael Wu
 */
public class MongoConnection {

	// MongoConnection statics.
	
	public static MongoConnection makeConnection() {
		MongoClient client = MongoClients.create("mongodb+srv://admin:123qweasdzxc@isd.eyyes.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

		MongoDatabase db = client.getDatabase("Users");

		MongoCollection collection = db.getCollection("Information");

		return new MongoConnection(client, db, collection);
	}
	
	// MongoConnection instance.
	
	private final MongoClient client;
	private final MongoDatabase database;
	private final MongoCollection collection;

	public MongoConnection(MongoClient client, MongoDatabase database, MongoCollection collection) {
		this.client = client;
		this.database = database;
		this.collection = collection;
	}

	public MongoClient getClient() {
		return client;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public MongoCollection getCollection() {
		return collection;
	}
}
