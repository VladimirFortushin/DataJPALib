package com.example.datajpa.implementation;


import com.example.datajpa.models.Book;
import com.example.datajpa.repositories.BookRepository;
import com.example.datajpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {

        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }
}
