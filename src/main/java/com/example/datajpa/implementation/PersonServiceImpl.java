package com.example.datajpa.implementation;


import com.example.datajpa.models.Person;
import com.example.datajpa.repositories.PersonRepository;
import com.example.datajpa.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void savePerson(Person person) {
        personRepository.save(person);

    }

    @Transactional
    @Override
    public void updatePerson(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        personRepository.save(updatedPerson);
    }

    @Transactional
    @Override
    public void removePerson(int id) {
        personRepository.deleteById(id);
    }
}
