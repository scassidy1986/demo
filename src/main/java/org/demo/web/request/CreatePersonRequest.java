package org.demo.web.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CreatePersonRequest {
    @NotNull
    private String name;
    @NotNull
    @Min(0L)
    private Integer age;
    @Min(0L)
    private Integer balance;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String address;
}
