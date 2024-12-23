package com.qa.api.pojo;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;

}
