package com.museum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PreAuthorize;

@NoRepositoryBean
public interface AdminSecurityRepository<T> extends JpaRepository<T, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends T> S save(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(T entity);
}
