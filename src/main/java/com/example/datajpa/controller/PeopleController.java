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

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonServiceImpl personService;
    private final BookServiceImpl bookService;
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonServiceImpl personService, BookServiceImpl bookService, BookRepository bookRepository,
                            PersonRepository personRepository) {
        this.personService = personService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    @GetMapping
    public String listOfPeople(@NotNull Model model){
        model.addAttribute("peopleList", personService.getAllPeople());

        return "/people/peopleList";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, @NotNull Model model){
        model.addAttribute("person", personService.getPersonById(id));
        model.addAttribute("personBookList", bookRepository.findAllByOwner(personService.getPersonById(id)));
        return "/people/show-person";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "/people/add-person";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person")  Person person,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){return "/people/add-person";}
        personService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String changePerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.getPersonById(id));
        return "/people/edit-person";
    }

    @PostMapping("/{id}/change")
    public String editPerson(@ModelAttribute("person") Person person,
                           BindingResult bindingResult,
                           @PathVariable int id){

        if(bindingResult.hasErrors()){return "/people/edit-person";}
        personService.updatePerson(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/remove")
    public String deletePerson(@PathVariable int id){
        personService.removePerson(id);

        return "redirect:/people";
    }




}
