package ru.kondratyeva.springcourse.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kondratyeva.springcourse.dao.PersonDAO;
import ru.kondratyeva.springcourse.models.Person;

import java.util.Optional;

//валидация форм для редактирования и создания людей
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
      Person person=(Person) o;
      Person result =personDAO.show(person.getFullName());
      if(result!=null){
          errors.rejectValue("fullName","","Человек с таким ФИО уже существует"+result.getUser_id()+" "+person.getUser_id());
        }
    }
}
