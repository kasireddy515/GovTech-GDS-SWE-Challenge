package com.govtech.assignment.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoggedInUserAccountDTO implements Serializable{
	
	private static final long serialVersionUID = -3745487817750611159L;
	
	private String accountId;
	private String userId;
	private String accountType;
}
