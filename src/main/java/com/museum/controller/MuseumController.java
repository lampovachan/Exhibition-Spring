package com.museum.controller;

import com.museum.model.Museum;
import com.museum.service.MuseumService;
import com.museum.to.MuseumTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/museums", produces = MediaTypes.HAL_JSON_VALUE)
public class MuseumController {

    private final MuseumService museumService;

    @Autowired
    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping("/{id}/exhibitions")
    public ResponseEntity<MuseumTo> getExhibitionToday(@PathVariable("id") Museum museum) {
        return new ResponseEntity<>(
                museumService.getWithExhibitionToday(museum), HttpStatus.OK);
    }
}
