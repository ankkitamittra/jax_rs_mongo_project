
package com.practice.mongo;

import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


/** Example resource class hosted at the URI path "/api"
 */
@Path("/api")
public class TaskControlller {
    
	
	 DBInstance minstance = DBInstance.getInstance();
	 MongoDatabase database = minstance.getMongoDB();
	 MongoCollection<Task> collection = database.getCollection("tasks", Task.class);
	 
    @GET
    @Path("/tasks")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPosts() {
    	List<Task> result = collection.find().into(new ArrayList<Task>());
        return Response.status(200).entity(result).build();
    }
    
    @POST
    @Path("/tasks/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response createTask(Task _task) {
    	 collection.insertOne(new Task( _task.getTitle()));
	   	 List<Task> result = collection.find().into(new ArrayList<Task>());
	     return Response.status(200).entity(result).build();
    }
    
    @PUT
    @Path("/tasks/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateTask(@PathParam("id") String id, Task _task) {
	   	 collection.updateOne(Filters.eq("_id", new ObjectId(id)), set("title",  _task.getTitle()));
	   	 List<Task> result = collection.find().into(new ArrayList<Task>());
	     return Response.status(200).entity(result).build();
    }
    
    @DELETE
    @Path("/tasks/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteTask(@PathParam("id") String id) {
    	 collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    	 List<Task> result = collection.find().into(new ArrayList<Task>());
        return Response.status(200).entity(result).build();
    }
}
