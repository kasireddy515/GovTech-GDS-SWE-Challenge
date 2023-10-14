package com.govtech.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govtech.assignment.entity.UserNotification;

public interface UserNotificationRepository  extends JpaRepository<UserNotification, String> {

	List<UserNotification> findByReceiverIdAndRead(String userId, Boolean false1);

}
