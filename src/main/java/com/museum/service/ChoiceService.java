package com.museum.service;

import com.museum.exceptions.DataNotFoundException;
import com.museum.exceptions.MuseumNotFoundException;
import com.museum.exceptions.ValidationLimitException;
import com.museum.model.Choice;
import com.museum.model.Exhibition;
import com.museum.model.Museum;
import com.museum.model.User;
import com.museum.repository.ChoiceRepository;
import com.museum.repository.ExhibitionRepository;
import com.museum.utility.ChoiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChoiceService {

    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalTime TIME_LIMIT = LocalTime.parse("11:00");
    private final Logger log = LoggerFactory.getLogger(ChoiceService.class);
    private final ChoiceRepository choiceRepository;
    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public ChoiceService(ChoiceRepository choiceRepository, ExhibitionRepository exhibitionRepository) {
        this.choiceRepository = choiceRepository;
        this.exhibitionRepository = exhibitionRepository;
    }

    public Optional<Choice> getForUserAndDate(Long userId, LocalDate date) {
        log.debug("Authorized {}", userId);
        return choiceRepository.getForUserAndDate(userId, date);
    }

    public List<Exhibition> getForMuseumAndDate(Museum museum, LocalDate date) {
        return exhibitionRepository.findAllByMuseumAndDate(museum, date);
    }

    @Transactional
    public ChoiceStatus save(User user, Museum museum) {
        ChoiceStatus choiceStatus = choiceRepository.getForUserAndDate(user.getId(), TODAY)
                .map(c -> {
                    c.setMuseum(museum);
                    return new ChoiceStatus(c, false);
                })
                .orElseGet(() -> new ChoiceStatus(
                        new Choice(user, museum, TODAY), true));
        choiceRepository.save(choiceStatus.getChoice());

        return choiceStatus;
    }

    @Transactional
    public ChoiceStatus saveAfterLimitTime(User user, Museum museum) {
        return choiceRepository.getForUserAndDate(user.getId(), TODAY)
                .map(c -> new ChoiceStatus(c, false))
                .orElseGet(() -> new ChoiceStatus(choiceRepository.save(new Choice(user, museum, TODAY)), true));
    }

    public Museum getCurrent(User user) throws MuseumNotFoundException {
        return getForUserAndDate(user.getId(), TODAY).map(Choice::getMuseum).orElseThrow(() -> new MuseumNotFoundException("The museum was not chosen"));
    }

    public ChoiceStatus choiceStatus(User user, Museum museum) {

        if (museum == null) {
            log.info("Museum not fount");
            throw new ResourceNotFoundException("Museum not found");
        }

        if (getForMuseumAndDate(museum, TODAY).isEmpty()) {
            log.info("The museum {} does not have exhibition for choice", museum.getId());
            throw new DataNotFoundException("The museum does not have exhibition for choice");
        }

        boolean limit = LocalTime.now().isAfter(TIME_LIMIT);
        ChoiceStatus choiceStatus = limit
                ? saveAfterLimitTime(user, museum)
                : save(user, museum);

        if (!choiceStatus.isCreated() && limit) {
            log.info("Choices time expired. Current time is {}", LocalTime.now().toString());
            throw new ValidationLimitException("Choices time expired");
        }

        return choiceStatus;
    }
}
