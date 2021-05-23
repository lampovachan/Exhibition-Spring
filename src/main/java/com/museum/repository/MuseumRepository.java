package com.museum.repository;

import com.museum.model.Museum;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "museum", path = "museums")
public interface MuseumRepository extends AdminSecurityRepository<Museum> {

    @RestResource(path = "by-name")
    @Transactional(readOnly = true)
    Museum findByName(@Param("name") String name);
}

