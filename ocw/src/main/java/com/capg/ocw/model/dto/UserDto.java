package com.capg.ocw.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String password;
	private List<String> roles;

}
