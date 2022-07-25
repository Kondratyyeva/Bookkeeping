package ru.kondratyeva.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kondratyeva.springcourse.models.Book;
import ru.kondratyeva.springcourse.models.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
         return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public Book show(String name){
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book (name, author, year ) VALUES (?,?,?)",
                book.getName(),book.getAuthor(), book.getYear());
    }
    public void edit(int id, Book book){
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE id=?",
                book.getName(),book.getAuthor(),book.getYear(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?",id);
    }

    public Person getOwner(int id){
        return jdbcTemplate.query("SELECT person.* FROM Book JOIN Person on book.user_id=person.user_id"+
                " WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);

    }

    public void addOwner(int id, Person person){
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE id=?", person.getUser_id(),id);
    }
    public void deleteOwner(int id){
        jdbcTemplate.update("UPDATE Book SET user_id=null WHERE id=?", id);
    }
}
