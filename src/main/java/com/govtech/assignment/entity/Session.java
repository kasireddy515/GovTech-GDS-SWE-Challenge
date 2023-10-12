package com.govtech.assignment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Session implements Serializable {

	private static final long serialVersionUID = 4809871490761808446L;

	@Id
	private String id;
	private String title;
	private String description;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@ManyToMany
	@JoinTable(name = "session_users_invited", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> usersInvited = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "session_restaurants", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
	private Set<Restaurant> submittedRestaurants = new HashSet<>();

	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "SELECTED_RESTAURANT_ID")
	private Restaurant selectedRestaurant;

	private Date createdOn;
	private Date updatedOn;
}
