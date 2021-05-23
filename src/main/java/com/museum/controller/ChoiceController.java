package com.museum.controller;

import com.museum.exceptions.MuseumNotFoundException;
import com.museum.model.Museum;
import com.museum.service.ChoiceService;
import com.museum.userdetails.UserPrincipal;
import com.museum.utility.ChoiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/choice", produces = MediaTypes.HAL_JSON_VALUE)
public class ChoiceController {
    private final ChoiceService choiceService;

    @Autowired
    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping
    public ResponseEntity<Museum> current(@AuthenticationPrincipal UserPrincipal userPrincipal) throws MuseumNotFoundException {
        return new ResponseEntity<>(choiceService.getCurrent(userPrincipal.getUser()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Museum> choice(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("id") Museum museum) {
        ChoiceStatus choiceStatus = choiceService.choiceStatus(userPrincipal.getUser(), museum);
        return new ResponseEntity<>(choiceStatus.getChoice().getMuseum(), choiceStatus.isCreated() ? HttpStatus.CREATED : HttpStatus.OK);
    }
}
