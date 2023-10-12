package com.govtech.assignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.govtech.assignment.entity.Session;

public interface SessionRepository extends JpaRepository<Session, String> {

	Session findByTitleIgnoreCase(String title);

	@Query("select distinct s from Session s join s.usersInvited ui where (ui.id = :userId or s.createdBy.id = :userId) and s.title like %:searchText%")
	Page<Session> findByTitleAndUserId(@Param("searchText") String searchText, @Param("userId") String userId,
			Pageable paging);

	@Query("select distinct s from Session s join s.usersInvited ui where ui.id = :userId or s.createdBy.id = :userId")
	Page<Session> findByUserId(Pageable paging, @Param("userId") String userId);

	@Query("select count(distinct s) from Session s join s.usersInvited ui where ui.id = :userId or s.createdBy.id = :userId")
	Long countByUserId(@Param("userId") String userId);

	@Query("select count(distinct s) from Session s join s.usersInvited ui where (ui.id = :userId or s.createdBy.id = :userId) and s.title like %:searchText%")
	Long countByTitleAndUserId(@Param("searchText") String searchText, @Param("userId") String userId);

}
