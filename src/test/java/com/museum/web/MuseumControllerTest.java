package com.museum.web;

import org.junit.jupiter.api.Test;
import static com.museum.testdata.MuseumTestData.*;
import static com.museum.testdata.UserTestData.ADMIN;
import static com.museum.testdata.UserTestData.USER;
import static com.museum.utils.TestUtil.userHttpBasic;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MuseumControllerTest extends AbstractControllerTest {
    @Override
    @Test
    void getAll() throws Exception {
        testGetAll(MUSEUM_URL, USER);
    }

    @Override
    @Test
    void getIsNotFound() throws Exception {
        testGetIsNotFound(MUSEUM_URL + 100, USER);
    }

    @Test
    void getMuseumByName() throws Exception {
        mockMvc.perform(get(MUSEUM_URL + "search/by-name")
                .param("name", MUSEUM_0.getName())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(MUSEUM_0.getName())));
    }

    @Override
    @Test
    void create() throws Exception {
        testCreate(MUSEUM_URL, ADMIN,
                objectMapper.writeValueAsString(getStringObjectMapMuseum("Kyiv Museum")));
    }

    @Override
    @Test
    void createIsConflict() throws Exception {
        testCreateIsConflict(MUSEUM_URL, ADMIN,
                objectMapper.writeValueAsString(getStringObjectMapMuseum(MUSEUM_2.getName())));
    }

    @Override
    @Test
    void createIsForbidden() throws Exception {
        testCreateIsForbidden(MUSEUM_URL, USER,
                objectMapper.writeValueAsString(getStringObjectMapMuseum("Kyiv Museum")));
    }

    @Override
    @Test
    void update() throws Exception {
        testUpdate(MUSEUM_URL + MUSEUM_0.getId(), ADMIN,
                objectMapper.writeValueAsString(getStringObjectMapMuseum("Kyiv Museum")));
    }

    @Override
    @Test
    void updatedIsConflict() throws Exception {
        testUpdateIsConflict(MUSEUM_URL + MUSEUM_0.getId(), ADMIN,
                objectMapper.writeValueAsString(getStringObjectMapMuseum(MUSEUM_2.getName())));

    }

    @Override
    @Test
    void updateIsForbidden() throws Exception {
        testUpdateIsForbidden(MUSEUM_URL + MUSEUM_0.getId(), USER,
                objectMapper.writeValueAsString(getStringObjectMapMuseum("Kyiv Museum")));
    }

    @Override
    @Test
    void deleted() throws Exception {
        testDelete(MUSEUM_URL + MUSEUM_1.getId(), ADMIN);
    }

    @Override
    @Test
    void deletedIsForbidden() throws Exception {
        testDeleteIsForbidden(MUSEUM_URL + MUSEUM_1.getId(), USER);
    }
}
