package com.example.person.service;

import com.example.person.domain.Person;
import com.mysql.cj.xdevapi.InsertResult;
import com.mysql.cj.xdevapi.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    @Autowired
    @Qualifier("people")
    Table people;


    public Flux<Person>findAll()
    {
    return Mono.fromFuture(people.select().executeAsync())
            .flatMapIterable(rows -> rows.fetchAll())
            .map(row ->
            {
                return new Person(row.getLong("id"),
                        row.getString("name"));

            });
    }

    public Flux<Person>findByName(String name)
    {
        return Mono.fromFuture(people.select("id","name")
                .where("name like: name")
                .orderBy("name")
                .bind("name", name).executeAsync())
                .flatMapIterable(rows -> rows.fetchAll()).map(
                        row -> {
                            return new Person(row.getLong("id"), row.getString("name"));
                        });
    }

    public Long savePerson (Person person)
    {
        InsertResult insertResult = people.insert("id","name")
                .values(person.getId(),
                        person.getName()).execute();
        return insertResult.getAutoIncrementValue();
    }

    public Long deletePerson(Long id)
    {
        return people.delete().where("id:id").bind("id",id).execute().getAffectedItemsCount();
    }

    public Long updatePerson(Person person)
    {
        return people.update().where("id=:id").bind("id",person.getId()).set("name",person.getName()).execute().getAffectedItemsCount();

    }
}
