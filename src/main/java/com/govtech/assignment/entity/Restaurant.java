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
@Entity
@NoArgsConstructor
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 5184788863044821210L;

	@Id
	private String id;
	private String title;
	private String location;

	@ManyToOne
	@JoinColumn(name = "suggested_by")
	private User suggestedUser;

	private Date createdOn;
	private Date updatedOn;
}
