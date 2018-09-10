package org.demo.service;

import org.demo.domain.Person;
import org.demo.entity.PersonEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityConverter implements Converter<PersonEntity, Person> {

    @Override
    public Person convert(PersonEntity source) {
        return Person.builder()
            .name(source.getFullName())
            .age(source.getAge())
            .email(source.getEmail())
            .balance(source.getBalance())
            .address(source.getAddress())
            .build();
    }
}
