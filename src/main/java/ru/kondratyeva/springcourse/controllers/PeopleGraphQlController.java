package ru.kondratyeva.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.kondratyeva.springcourse.dao.BookDAO;
import ru.kondratyeva.springcourse.dao.PersonDAO;
import ru.kondratyeva.springcourse.models.Book;
import ru.kondratyeva.springcourse.models.Person;

import java.util.List;
/*Контроллер для обработки graphql-запросов к сущности Person*/
@Controller
public class PeopleGraphQlController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public  PeopleGraphQlController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @QueryMapping()
    public Person getPerson (@Argument int id){
        return personDAO.show(id);
    }
    @QueryMapping()
    public List<Person> getPeople(){
        return personDAO.index();
    }

    @MutationMapping()
    public Person addPerson(@Argument String fullName,@Argument int yearOfBirth){
        Person person = new Person(fullName,yearOfBirth);
        personDAO.save(person);
        return personDAO.show(person.getFullName());
    }

    @MutationMapping()
    public Person editPerson(@Argument int user_id,@Argument String fullName,@Argument int yearOfBirth){
        Person person = new Person(fullName,yearOfBirth);
        personDAO.update(user_id, person);
        return personDAO.show(user_id);
    }

    @MutationMapping()
    public List<Person> deletePerson(@Argument int user_id){
        personDAO.delete(user_id);
        return personDAO.index();
    }

    @QueryMapping()
    public List<Book> getPersonBooks(@Argument int user_id){
        return personDAO.getBooks(user_id);
    }
}
