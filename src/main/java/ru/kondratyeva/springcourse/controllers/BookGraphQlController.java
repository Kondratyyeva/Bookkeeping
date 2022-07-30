package ru.kondratyeva.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.kondratyeva.springcourse.dao.BookDAO;
import ru.kondratyeva.springcourse.dao.PersonDAO;
import ru.kondratyeva.springcourse.models.Book;
import ru.kondratyeva.springcourse.models.Person;

import java.util.Collections;
import java.util.List;
/*Контроллер для обработки graphql-запросов к сущности Book*/
@Controller
public class BookGraphQlController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public  BookGraphQlController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @QueryMapping()
    public List<Book> getBooks(){
        return bookDAO.index();
    }

    @QueryMapping()
    public Book getBook(@Argument int id){
        return bookDAO.show(id);
    }

    @MutationMapping()
    public Book addBook(@Argument String name,@Argument String author,@Argument int year){
        Book book= new Book(name,author,year);
        bookDAO.save(book);
        return bookDAO.show(book.getName());
    }

    @MutationMapping()
    public Book editBook(@Argument int id, @Argument String name,@Argument String author,@Argument int year){
        Book book= new Book(name,author,year);
        bookDAO.edit(id, book);
        return bookDAO.show(id);
    }

    @MutationMapping()
    public List<Book> deleteBook(@Argument int id){
        bookDAO.delete(id);
        return bookDAO.index();
    }

    @MutationMapping()
    public Book deleteOwner(@Argument int id){
        Book book = bookDAO.show(id);
        bookDAO.deleteOwner(id);
        return book;
    }

    @MutationMapping()
    public Book addOwner(@Argument int id, @Argument int user_id){
        Person person = personDAO.show(user_id);
        Book book = bookDAO.show(id);
        bookDAO.addOwner(id,person);
        return book;
    }
}
