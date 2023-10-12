package com.govtech.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govtech.assignment.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

}
