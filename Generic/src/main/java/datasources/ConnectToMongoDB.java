package datasources;//package datasources;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ConnectToMongoDB {

    public static MongoDatabase mongoDatabase = null;

    public static MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("students");
        System.out.println("Database Connected");

        return mongoDatabase;
    }

    public static String insertIntoMongoDB1(Object [][] list, String profileName) {
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection myCollection = mongoDatabase.getCollection(profileName);
        boolean collectionExists = mongoDatabase.listCollectionNames().into(new ArrayList<String>())
                .contains(profileName);
        if (collectionExists) {
            myCollection.drop();
        }
        int row=list.length;
        int column=list[0].length;
        for (int i = 1; i < row; i++) {
                MongoCollection<Document> collection = mongoDatabase.getCollection(profileName);
                Document document = new Document().append("Name", list[i][0]).
                        append("Email",list[i][1]).append("Password",list[i][2]);
                collection.insertOne(document);
        }
        return "Different account information has been registered";
    }

    public static FindIterable<Document> readAccountProfileFromMongoDB(String profileName) {
        List<Object> accountList = new ArrayList<>();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection(profileName);
        BasicDBObject basicDBObject = new BasicDBObject();
        FindIterable<Document> iterable = collection.find(basicDBObject);
        return iterable;
    }

}
//MongoDB methods to insert into database and read from database is dependent upon the objects of your modules.
//Need to create your own methods. Ill add mines and can see as a template later.
