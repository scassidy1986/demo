package org.demo.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {

    private String name;
    private Integer age;
    private Integer balance;
    private String email;
    private String address;
}
