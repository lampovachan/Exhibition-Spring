package com.museum.testdata;

import com.museum.model.Exhibition;
import com.museum.model.Museum;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.museum.testdata.MuseumTestData.*;
import static com.museum.web.AbstractControllerTest.REST_URL;

public class ExhibitionTestData {
    public static final String EXHIBITION_URL = REST_URL + "/exhibition/";

    public static final Exhibition EXHIBITION_0 = new Exhibition(0L, "Illusion Exhibition", LocalDate.now(), 100, MUSEUM_0);
    public static final Exhibition EXHIBITION_1 = new Exhibition(1L, "Asian Culture", LocalDate.of(2021, 5, 31), 75, MUSEUM_0);
    public static final Exhibition EXHIBITION_2 = new Exhibition(2L, "Playstation Exhibition", LocalDate.now(), 110, MUSEUM_1);
    public static final Exhibition EXHIBITION_3 = new Exhibition(3L, "Ukrainian National Clothes Exhibition", LocalDate.of(2021, 6, 3), 150, MUSEUM_1);
    public static final Exhibition EXHIBITION_4 = new Exhibition(4L, "Aquariam Exhibition", LocalDate.now(), 120, MUSEUM_2);
    public static final Exhibition EXHIBITION_5 = new Exhibition(5L, "Wine Craft Exhibition", LocalDate.of(2021, 5, 31), 80, MUSEUM_2);

    public static Map<String, Object> getStringObjectMapExhibition(Museum museum, LocalDate date, String name, int price) {
        return new HashMap<>() {{
            put("museum", MUSEUM_URL + museum.getId());
            put("date", date);
            put("name", name);
            put("price", price);
        }};
    }
}
