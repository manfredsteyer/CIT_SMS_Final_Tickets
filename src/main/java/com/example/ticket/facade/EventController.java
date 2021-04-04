package com.example.ticket.facade;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.example.ticket.model.Event;
import com.example.ticket.model.EventRepository;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventRepository repo;

    public EventController(EventRepository repo) {
        this.repo = repo;
    }

    @GetMapping("{id}")
    public Event Get(@PathVariable long id) {
        return this.repo.findWithTicketTypesByEventId(id);
    }

    @GetMapping()
    public List<Event> Get() {
        return this.repo.findAll(Sort.by("start").descending());
    }

}
