package org.demo.service;

import lombok.extern.java.Log;
import org.demo.domain.Person;
import org.demo.entity.PersonEntity;
import org.demo.repository.PersonRepository;
import org.demo.web.request.CreatePersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log
@Service
public class PersonService {
    private final PersonRepository repository;
    private final Converter<PersonEntity, Person> personConverter;

    @Autowired
    public PersonService(
        PersonRepository repository,
        Converter<PersonEntity, Person> personConverter) {

        this.repository = repository;
        this.personConverter = personConverter;
    }

    public Person createPerson(CreatePersonRequest request) {
        PersonEntity entity = new PersonEntity();
        entity.setFullName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setAge(request.getAge());
        entity.setAddress(request.getAddress());
        entity.setBalance(request.getBalance());
        PersonEntity persisted = repository.save(entity);
        return personConverter.convert(persisted);
    }

    public Page<Person> findPersons(Pageable pageable) {
        return repository.findAll(pageable)
            .map(personConverter::convert);
    }
}

