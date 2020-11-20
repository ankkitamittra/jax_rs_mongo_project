package com.practice.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;



import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
public class DBInstance {

	private static DBInstance minstance;
	private DBInstance(){};
	
	public static DBInstance getInstance(){
		if(minstance == null){
			minstance = new DBInstance();
		}
		return minstance;
	} 
	
	public MongoDatabase getMongoDB(){
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoClientSettings settings = MongoClientSettings.builder()
		        .codecRegistry(pojoCodecRegistry)
		        .build();
		com.mongodb.client.MongoClient mongoClient = MongoClients.create(settings);
		//MongoClient client = MongoClien
		//MongoClient mongoClient = new MongoClient("localhost" , 27017);
		MongoDatabase db =  mongoClient.getDatabase("postDB");
		return db;
	}
}
