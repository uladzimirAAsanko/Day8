package by.sanko.bookproject.model.service;

import by.sanko.bookproject.exception.ProjectException;
import by.sanko.bookproject.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    boolean addBook(Book book) throws ProjectException;

    boolean removeBook(Book book) throws ProjectException;

    Optional<Book> findById(long bookId) throws ProjectException;

    List<Book> findByTitle(String title) throws ProjectException;

    List<Book> findByAuthor(String author) throws ProjectException;

    List<Book> findByYearPublication(int yearPublication) throws ProjectException;

    List<Book> findByCountOfPages(long countOfPages) throws ProjectException;

    List<Book> sortById() throws ProjectException;

    List<Book> sortByTitle() throws ProjectException;

    List<Book> sortByAuthor() throws ProjectException;

    List<Book> sortByYearPublication() throws ProjectException;

}
