package com.example.datajpa.repositories;


import com.example.datajpa.models.Book;
import com.example.datajpa.models.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.owner = :owner")
    List<Book> findAllByOwner(@Param(value = "owner") Person owner);
    @Modifying
    @Transactional
    @Query("update Book b set b.owner = null where b.id = :id")
    void releaseBook(@Param(value = "id") int id);
    @Modifying
    @Transactional
    @Query("update Book b set b.owner.id = :person_id where b.id = :book_id")
    void assignBook(@Param(value = "book_id") int book_id, @Param(value = "person_id") int person_id);
    

}
