package com.capg.ocw.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Document(collection = "Users")
@Data
public class User {
	
	@Id
	private String id;
	private String userId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private List<String> roles;

}
