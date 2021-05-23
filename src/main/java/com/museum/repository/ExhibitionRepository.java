package com.museum.repository;

import com.museum.model.Exhibition;
import com.museum.model.Museum;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "exhibition", path = "exhibition")
public interface ExhibitionRepository extends AdminSecurityRepository<Exhibition> {

    @RestResource(path = "by-date")
    @Transactional(readOnly = true)
    List<Exhibition> findAllByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @RestResource(path = "by-museum")
    @Transactional(readOnly = true)
    List<Exhibition> findAllByMuseum(@Param("museum") Museum museum);

    @RestResource(path = "by-museum-and-date")
    @Transactional(readOnly = true)
    List<Exhibition> findAllByMuseumAndDate(@Param("museum") Museum museum, @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);
}
