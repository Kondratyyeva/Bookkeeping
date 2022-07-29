package ru.kondratyeva.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kondratyeva.springcourse.dao.BookDAO;
import ru.kondratyeva.springcourse.dao.PersonDAO;
import ru.kondratyeva.springcourse.models.Book;
import ru.kondratyeva.springcourse.models.Person;
import ru.kondratyeva.springcourse.utill.BookValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,@ModelAttribute("person") Person person, Model model){
        model.addAttribute("book", bookDAO.show(id));
        Person bookOwner = bookDAO.getOwner(id);
        if(bookOwner !=null)
        {
            model.addAttribute("owner", bookOwner);
        }
        else model.addAttribute("people",personDAO.index());
        return "/books/show";
    }
    @GetMapping("/new")
    public String create(@ModelAttribute("book") Book book){
        return "/books/new";
    }


    @PostMapping()
    public String add(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "redirect:/books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "/books/edit";
    }
    @PatchMapping("/{id}")
    public String update (@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.edit(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/addOwner")
    public String addOwner(@PathVariable("id") int id,@ModelAttribute("person") Person person){
         bookDAO.addOwner(id,person);
         return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/deleteOwner")
    public String deleteOwner(@PathVariable("id") int id){
        bookDAO.deleteOwner(id);
        return "redirect:/books/"+id;
    }
}
