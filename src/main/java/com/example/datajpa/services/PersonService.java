package com.example.datajpa.services;


import com.example.datajpa.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> getAllPeople();
    Person getPersonById(int id);
    void savePerson(Person person);
    void updatePerson(int id, Person updatedPerson);
    void removePerson(int id);

}
