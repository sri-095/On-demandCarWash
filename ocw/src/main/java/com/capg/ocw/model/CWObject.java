package com.capg.ocw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "CWObject")
@Data
public class CWObject {

	@Id
	private String id;
	private char lastRevision;
	private String status;
	private String name;
}
