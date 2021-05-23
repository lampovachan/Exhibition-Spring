package com.museum.service;

import com.museum.model.Museum;
import com.museum.repository.ExhibitionRepository;
import com.museum.repository.MuseumRepository;
import com.museum.to.ExhibitionTo;
import com.museum.to.MuseumTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class MuseumService {
    private static final LocalDate LOCAL_CURRENT_DATE = LocalDate.now();
    private final Logger log = LoggerFactory.getLogger(ChoiceService.class);
    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public MuseumService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public MuseumTo getWithExhibitionToday(Museum museum) {
        log.debug("Museum {} with exhibition at {}", museum.getId(), LOCAL_CURRENT_DATE.toString());
        return new MuseumTo(museum,
                exhibitionRepository.findAllByMuseumAndDate(museum, LOCAL_CURRENT_DATE)
                        .stream().map(ExhibitionTo::new).collect(Collectors.toList()));
    }
}
