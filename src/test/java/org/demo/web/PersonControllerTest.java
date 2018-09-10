package org.demo.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.demo.domain.Person;
import org.demo.service.PersonService;
import org.demo.web.request.CreatePersonRequest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PersonService mockPersonService;

    @Test
    public void shouldReturnExpectedResponseWhenListingPersons() throws Exception {
        Person person = Person.builder()
            .name("Steven Cassidy")
            .age(20)
            .address("Belfast")
            .email("stephen@gmail.com")
            .build();

        List<Person> persons = createPersonsList(person);
        Page<Person> response = new PageImpl<>(persons);

        doReturn(response).when(mockPersonService).findPersons(PageRequest.of(1, 100));

        mockMvc.perform(get("/people")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$content", hasSize(1)))
            .andExpect(jsonPath("$content.[0].name", is(person.getName())));
    }

    private List<Person> createPersonsList(Person... values) {
        return Arrays.asList(values);
    }

    @Test
    public void shouldReturnExpectedResponseWhenAddingPerson() throws Exception {
        Person person = Person.builder()
            .name("Steven Cassidy")
            .age(20)
            .build();

        Page<Person> response = new PageImpl<>(new ArrayList<>());

        CreatePersonRequest request = CreatePersonRequest.builder()
            .name(person.getName())
            .age(person.getAge())
            .build();

        doReturn(person).when(mockPersonService).createPerson(request);

        mockMvc.perform(post("/people")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$name", is(person.getName())))
            .andExpect(jsonPath("age", is(person.getAge())));
    }
}
