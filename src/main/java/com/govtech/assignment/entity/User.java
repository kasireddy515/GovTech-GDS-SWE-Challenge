package com.govtech.assignment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "USER_MASTER")
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 4996826070283556078L;

	@Id
	private String id;
	private String firstName;
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	private Date createdOn;
	private Date updatedOn;

	
}
