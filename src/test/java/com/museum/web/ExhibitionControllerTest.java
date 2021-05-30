package com.museum.web;

import org.junit.jupiter.api.Test;

import static com.museum.testdata.ExhibitionTestData.*;
import static com.museum.testdata.MuseumTestData.MUSEUM_0;
import static com.museum.testdata.MuseumTestData.MUSEUM_2;
import static com.museum.testdata.UserTestData.ADMIN;
import static com.museum.testdata.UserTestData.USER;

class ExhibitionControllerTest extends AbstractControllerTest {

    @Override
    @Test
    void getAll() throws Exception {
        testGetAll(EXHIBITION_URL, USER);
    }

    @Override
    @Test
    void getIsNotFound() throws Exception {
        testGetIsNotFound(EXHIBITION_URL + 100, USER);
    }

    @Override
    @Test
    void create() throws Exception {
        testCreate(EXHIBITION_URL, ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_0, LOCAL_DATE, "New exhibition", 200)));
    }

    @Override
    @Test
    void createIsConflict() throws Exception {
        testCreateIsConflict(EXHIBITION_URL, ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_0, LOCAL_DATE, "Illusion exhibition", 200)
                ));
    }

    @Override
    @Test
    void createIsForbidden() throws Exception {
        testCreateIsForbidden(EXHIBITION_URL, USER,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_0, LOCAL_DATE, "New exhibition", 200)));
    }

    @Override
    @Test
    void update() throws Exception {
        testUpdate(EXHIBITION_URL + EXHIBITION_0.getId(), ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_2, LOCAL_DATE, "Update exhibition", 100)
                ));
    }

    @Override
    @Test
    void updatedIsConflict() throws Exception {
        testUpdateIsConflict(EXHIBITION_URL + EXHIBITION_0.getId(), ADMIN,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_0, LOCAL_DATE, "Illusion exhibition", 100)
                ));
    }

    @Override
    @Test
    void updateIsForbidden() throws Exception {
        testUpdateIsForbidden(EXHIBITION_URL + EXHIBITION_0.getId(), USER,
                objectMapper.writeValueAsString(
                        getStringObjectMapExhibition(MUSEUM_2, LOCAL_DATE, "Illusion exhibition", 100)
                ));
    }

    @Override
    @Test
    void deleted() throws Exception {
        testDelete(EXHIBITION_URL + EXHIBITION_0.getId(), ADMIN);
    }

    @Override
    @Test
    void deletedIsForbidden() throws Exception {
        testDeleteIsForbidden(EXHIBITION_URL + EXHIBITION_0.getId(), USER);
    }
}
