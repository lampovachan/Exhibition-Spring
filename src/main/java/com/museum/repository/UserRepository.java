package com.museum.repository;

import com.museum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "user", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(path = "by-email")
    @Transactional(readOnly = true)
    @Query("SELECT u FROM User u LEFT JOIN u.roles WHERE u.email=:email")
    User findByEmail(@Param("email") String login);
}
