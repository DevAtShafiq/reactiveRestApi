package com.example.person;

import com.example.person.domain.Person;
import com.example.person.service.PersonService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {
    @Autowired
    PersonService personService;

    @Test
    public void  contextLoads()
    {

    }
@Test
    public void testSave()
{
    Person p =new Person();
    p.setName("shafiq");
    Long id  = personService.savePerson(p);
    assertNotNull(id);
}
}
