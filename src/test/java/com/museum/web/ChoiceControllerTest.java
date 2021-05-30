package com.museum.web;

import com.museum.model.Choice;
import com.museum.repository.ChoiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.museum.testdata.MuseumTestData.MUSEUM_0;
import static com.museum.testdata.UserTestData.USER;
import static com.museum.utils.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ChoiceControllerTest {

    private static final LocalTime TIME_LIMIT = LocalTime.parse("11:00");

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ChoiceRepository choiceRepository;

    @AfterEach
    void tearDown() {
        choiceRepository.deleteAll();
    }

    @Test
    void currentIsNotFount() throws Exception {
        mockMvc.perform(get("/api/choice")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void choiceCreate() throws Exception {
        mockMvc.perform(post("/api/choice/0")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void choiceUpdate() throws Exception {
        Choice choice = new Choice(USER, MUSEUM_0, LocalDate.now());
        choiceRepository.save(choice);

        boolean limit = LocalTime.now().isAfter(TIME_LIMIT);

        mockMvc.perform(post("/api/choice/2")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(limit ? status().isConflict() : status().isOk());
    }

    @Test
    void choiceIsNotFound() throws Exception {
        mockMvc.perform(post("/api/choice/3")
                .contentType(MediaTypes.HAL_JSON_VALUE)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void currentChoice() throws Exception {
        Choice choice = new Choice(USER, MUSEUM_0, LocalDate.now());
        choiceRepository.save(choice);

        mockMvc.perform(get("/api/choice")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
