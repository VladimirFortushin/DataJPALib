package com.example.datajpa.repositories;


import com.example.datajpa.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p join Book b on b.owner.id = p.id where b.id = :book_id")
    Person findByBookId(@Param(value = "book_id") int book_id);



}
