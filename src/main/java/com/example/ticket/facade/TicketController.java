package com.example.ticket.facade;

import java.util.Optional;

import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/ticket")
public class TicketController {
   
    TicketRepository repo;

    TicketController(TicketRepository repo) {
        this.repo = repo;
    }

    @GetMapping("{id}")
    public Optional<Ticket> Get(@PathVariable long id) {
        return repo.findById(id);
    }

    @PostMapping
    public ResponseEntity<Ticket> Post(@RequestBody Ticket t) {
        this.repo.save(t);

        var location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(t.getTicketId())
                        .toUri();

        return ResponseEntity.created(location).body(t);
    }

}