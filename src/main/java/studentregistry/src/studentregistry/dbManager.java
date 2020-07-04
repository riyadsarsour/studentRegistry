package studentregistry;

import java.util.*;

import javax.swing.text.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

class dbManager {
	
	public String collectionName = "StudentList";
	
	MongoClient mongoClient = (MongoClient) MongoClients.create();
	Scanner input = new Scanner(System.in);
	
	// Set Database
	MongoDatabase database = mongoClient.getDatabase("studentRegistry") ;
	
	// get collection aka the table(in non mongo setting)
	MongoCollection<org.bson.Document> collection = database.getCollection(collectionName);
	// now create object for gson to convert data 
	Gson gson = new Gson();
	
	public void saveHashMap(ArrayList<org.bson.Document> documents) {
		// check if existing collection exists
		if(collection.countDocuments() > 0) {
			System.out.println(
					"The database already contains a collection, would you like to overwrite it?\n"
					+ "1. Yes\n 2. No, create a new collection" );
			
			int choice = input.nextInt();
			switch (choice) {
				case 1:
					collection.drop();
					database.createCollection(collectionName);
					collection = database.getCollection(collectionName);
					collection.insertMany(documents);
					break;
					
				case 2:
					System.out.println("Enter new collection name");
					String newName = input.next();
					
					database.createCollection(newName);
					collection = database.getCollection(newName);
					collection.insertMany(documents);
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}	
			// if collection does not exist
		}else {
			collection.insertMany(documents);
		}
		// for sake of mem management
		input.close();
	}
	
	public ArrayList<Student> loadHashMap(){
		ArrayList<Student> students = new ArrayList<Student>();
		
		if(collection.countDocuments() <= 0 ) {
			System.out.println("Cannot load data, the collection is empty");
		}else {
			MongoCursor<org.bson.Document> cursor = collection.find().iterator();
			
			try {
				while(cursor.hasNext()) {
					students.add(gson.fromJson(cursor.next().toJson(), Student.class));
				}
			} catch (Exception e){
				e.printStackTrace();				
			}
			finally {
				cursor.close();
			}
		}
		return students; 		
	}

}// end class
