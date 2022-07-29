package ru.kondratyeva.springcourse.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kondratyeva.springcourse.dao.BookDAO;
import ru.kondratyeva.springcourse.models.Book;

//валидация форм для редактирования и создания книг
@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;
    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
       Book book=(Book) o;
       Book result=bookDAO.show(book.getName());
       if(result!=null && result.getId()!=book.getId()){
           errors.rejectValue("name","","Книга с таким названием уже существует"+
                   book.getId()+" "+result.getId());
       }
    }
}
