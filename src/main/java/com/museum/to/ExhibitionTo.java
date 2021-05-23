package com.museum.to;

import com.museum.model.Exhibition;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExhibitionTo {
    private String name;
    private Integer price;

    public ExhibitionTo(Exhibition exhibition) {
        this.name = exhibition.getName();
        this.price = exhibition.getPrice();
    }
}
