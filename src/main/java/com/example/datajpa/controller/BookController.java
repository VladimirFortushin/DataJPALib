package com.example.datajpa.controller;


import com.example.datajpa.implementation.BookServiceImpl;
import com.example.datajpa.implementation.PersonServiceImpl;
import com.example.datajpa.models.Book;
import com.example.datajpa.models.Person;
import com.example.datajpa.repositories.BookRepository;
import com.example.datajpa.repositories.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final PersonServiceImpl personService;
    private final BookServiceImpl bookService;
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(PersonServiceImpl personService, BookServiceImpl bookService, PersonRepository personRepository, BookRepository bookRepository) {
        this.personService = personService;
        this.bookService = bookService;
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String listOfBook(@NotNull Model model){
        model.addAttribute("bookList", bookService.getAllBooks());
        return "/book/bookList";
    }

    @GetMapping("/{id}")
        public String showBook(@PathVariable("id") int id, @NotNull Model model){
        model.addAttribute("book", bookService.getBookById(id));

        Person owner = personRepository.findByBookId(id);
        if(owner != null){
            model.addAttribute("owner", owner);

        }else {
            model.addAttribute("person", new Person());
            model.addAttribute("peopleList", personService.getAllPeople());
        }
        return "/book/show-book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){

        return "/book/add-book";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book book,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "/book/add-book";}
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String changeBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.getBookById(id));
        return "/book/edit-book";
    }

    @PostMapping("/{id}/change")
    public String editBook(@ModelAttribute("book") Book book,
                           BindingResult bindingResult,
                           @PathVariable int id){

        if(bindingResult.hasErrors()){return "/book/edit-book";}
        bookService.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("non-occupied")
    public String listOfNonOccupiedBooks(Model model){
        model.addAttribute("nonOccupiedBooks", bookRepository.findAllByOwner(null));
        return "/book/bookList";
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable int id){

       bookRepository.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable int id, @ModelAttribute Person person){

        bookRepository.assignBook(id, person.getId());
        return "redirect:/books/{id}";
    }


  @PostMapping("/{id}/remove")
  public String deleteBook(@PathVariable int id){
      bookService.deleteBook(id);

        return "redirect:/books";
  }


}
