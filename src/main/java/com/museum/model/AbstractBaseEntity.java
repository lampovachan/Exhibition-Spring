package com.museum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity extends AbstractPersistable<Long> {

    AbstractBaseEntity() {
    }

    AbstractBaseEntity(Long id) {
        setId(id);
    }

    @JsonIgnore
    public boolean isNew() {
        return super.isNew();
    }

}
