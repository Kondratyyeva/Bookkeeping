# Digital bookkeeping
Web application for digital bookkeeping
Librarians
should be able to register readers, give them
books and release books (after the reader returns
the book back to the library).
# Functional:
1) Pages for adding, changing and deleting a person.
2) Pages for adding, changing and deleting a book
3) A page with a list of all people (people are clickable - when clicked, the
   user goes to the person's page).
4) A page with a list of all books (books are clickable - when clicked
   , you go to the book page).
5) A person's page, which shows the values of his fields and a list of books that he
   took. If a person has not taken any books, instead of the list there should be the text "Person
   I haven't taken any books yet."
6) The page of the book, which shows the values of the fields of this book and the name of the person
   who took this book. If this book was not taken by anyone, there should be a text "This
   book is free".
7) On the book page, if the book is taken by a person, there should be a button next to his name
   "Release the book." This button is pressed by the librarian when the reader
   returns this book back to the library. After clicking on this button, the book
   becomes free again
8) On the book page, if the book is free, there should be a drop-down list (<select>) 
   with all people and a button "Assign a book". This button is pressed by the librarian
   when the reader wants to take this book home. After clicking on this button, the book
   should start to belong to the selected person and should appear in his list of books.
9) All fields must be validated - using @Valid and Spring Validator, if
   required.
# Used technologies
* Spring Core, Spring MVC, Spring Data JDBC
* Thymeleaf
* Hibernate-validator
* PostgreSQL database