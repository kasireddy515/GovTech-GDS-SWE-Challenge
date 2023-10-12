package com.govtech.assignment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = -239409729409755520L;

	@Id
	private String id;

	private String userName;
	private String passwordHash;
	private String passwordSalt;
	private Date createdOn;
	private Date updatedOn;

}
