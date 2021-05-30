package com.museum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table(name = "museum", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_museum_name_idx")})
public class Museum extends AbstractNamedEntity {
    public Museum(Long id, String name) {
        super(id, name);
    }
}
