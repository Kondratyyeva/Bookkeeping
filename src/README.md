# Digital bookkeeping
Web application for digital bookkeeping. Librarians should be able to register readers, give them books and release books (after the reader returns the book back to the library).
# Functional:
1) Pages for adding, changing and deleting a person/book. All fields must be validated - using @Valid and Spring Validator, if required.
2) A page with a list of all people/books (people/books are clickable - when clicked, the user goes to the person's/book's page).
3) A person's page, which shows the values of his fields and a list of books that he took. If a person has not taken any books, instead of the list there should be the text "Person N haven't taken any books yet."
4) The page of the book, which shows the values of the fields of this book and the name of the person who took this book. If this book was not taken by anyone, there should be a text "This book is free".
5) On the book page, if the book is taken by a person, there should be a button next to his name "Release the book." This button is pressed by the librarian when the reader returns this book back to the library. After clicking on this button, the book becomes free again. If the book is free, there should be a drop-down list (<select>) with all people and a button "Assign a book". This button is pressed by the librarian when the reader wants to take this book home. After clicking on this button, the book should start to belong to the selected person and should appear in his list of books.
# Used technologies:
* Spring Core, Spring MVC, Spring Data JDBC
* Thymeleaf
* Hibernate-validator
* PostgreSQL database
