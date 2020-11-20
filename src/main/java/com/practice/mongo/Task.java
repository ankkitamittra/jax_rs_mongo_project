package com.practice.mongo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

public final class Task {
	
	private ObjectId id;
	private String title;
	
	public Task() {
		
	}
	
	public Task(String title) {
		 this.title = title;
	}
	
	public void setId(ObjectId id) {
	   this.id = id;
	}
	
	public void setTitle(String title) {
	   this.title = title;
	}
	
	@XmlElement  
	public ObjectId getId() {
	        return id;
    }

	@XmlElement  
    public String getTitle() {
        return title;
    }
	
	@Override
    public String toString(){
        return title;
    }
    
}
