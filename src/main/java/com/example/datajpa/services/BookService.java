package com.example.datajpa.services;


import com.example.datajpa.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void saveBook(Book book);
    void updateBook(int id, Book updatedBook);
    void deleteBook(int id);

}
