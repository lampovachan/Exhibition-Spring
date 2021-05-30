package com.museum.to;

import com.museum.model.Museum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MuseumTo {
    private List<ExhibitionTo> exhibitions;
    private String name;

    public MuseumTo(Museum museum, List<ExhibitionTo> exhibitions) {
        this.name = museum.getName();
        this.exhibitions = exhibitions;
    }
}

