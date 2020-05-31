package com.capg.message.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CWObject")
public class CWObject {

	@Id
	private String id;
	private char lastRevision;
	private String status;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public char getLastRevision() {
		return lastRevision;
	}
	public void setLastRevision(char lastRevision) {
		this.lastRevision = lastRevision;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
