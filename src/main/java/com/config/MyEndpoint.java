package com.config;

import io.micrometer.core.lang.Nullable;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yunxiang.yang
 * @date 2018/12/02 13:56
 */

@Endpoint(id = "my")
@Component
public class MyEndpoint {


    @ReadOperation
    public List<Person> getAll() {
        return new ArrayList<>(this.people.values());
    }

    @ReadOperation
    public Person getPerson(@Selector String person) {
        return this.people.get(person);
    }

    @WriteOperation
    public void updatePerson(@Selector String name, String person) {
        this.people.put(name, new Person(person));
    }

    private final Map<String, Person> people = new HashMap<>();

    MyEndpoint() {
        this.people.put("mike", new Person("Michael Redlich"));
        this.people.put("rowena", new Person("Rowena Redlich"));
        this.people.put("barry", new Person("Barry Burd"));
    }
    public static class Person {
        private String name;

        Person(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
