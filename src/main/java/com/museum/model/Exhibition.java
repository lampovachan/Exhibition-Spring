package com.museum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "exhibition", uniqueConstraints = {@UniqueConstraint(columnNames = {"museum_id", "name"}, name = "unique_exhibition_name_idx")})
public class Exhibition extends AbstractNamedEntity {

    @NotNull
    @Column(name = "exhibition_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "museum_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Museum museum;

    public Exhibition(Long id, String name, @NotNull LocalDate date, @NotNull Integer price, @NotNull Museum museum) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.museum = museum;
    }

    public Exhibition(Exhibition exhibition) {
        this(exhibition.getId(), exhibition.getName(), exhibition.getDate(), exhibition.getPrice(), exhibition.getMuseum());
    }
}
