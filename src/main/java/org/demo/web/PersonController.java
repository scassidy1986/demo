package org.demo.web;

import javax.validation.Valid;
import org.demo.domain.Person;
import org.demo.service.PersonService;
import org.demo.web.request.CreatePersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/people")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid CreatePersonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.createPerson(request));
    }

    @GetMapping
    public ResponseEntity<Page<Person>> findPersons(@RequestBody Pageable pageable) {
        Page<Person> persons = service.findPersons(pageable);
        return ResponseEntity.ok(persons);
    }
}
