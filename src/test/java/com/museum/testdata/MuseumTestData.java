package com.museum.testdata;

import com.museum.model.Museum;

import java.util.HashMap;
import java.util.Map;

import static com.museum.web.AbstractControllerTest.REST_URL;

public class MuseumTestData {
    public static final String MUSEUM_URL = REST_URL + "museums/";

    public static final Museum MUSEUM_0 = new Museum(0L, "Geek Museum");
    public static final Museum MUSEUM_1 = new Museum(1L, "Kyiv Museum of Arts");
    public static final Museum MUSEUM_2 = new Museum(2L, "Museum of Creative Craft");

    public static Map<String, Object> getStringObjectMapMuseum(String name) {
        return new HashMap<>() {{
            put("name", name);
        }};
    }
}
