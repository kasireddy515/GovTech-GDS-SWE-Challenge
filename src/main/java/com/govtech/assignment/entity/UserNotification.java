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
@Entity(name = "USER_NOTIFICATION")
@NoArgsConstructor
public class UserNotification implements Serializable {
	private static final long serialVersionUID = 591856908504980069L;

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "sender_user_id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver_user_id")
	private User receiver;

	@ManyToOne
	@JoinColumn(name = "session_id")
	private Session session;

	private Boolean read;
	private String message;

	private Date createdOn;
	private Date updatedOn;
}
