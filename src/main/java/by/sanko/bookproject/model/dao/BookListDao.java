package by.sanko.bookproject.model.dao;

import by.sanko.bookproject.exception.DAOException;
import by.sanko.bookproject.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookListDao extends BaseDao<Book,Integer> {


    List<Book> readByTitle(String title) throws DAOException;

    List<Book> readByAuthor(String author) throws DAOException;

    List<Book> readByYearPublication(int yearPublication) throws DAOException;

    List<Book> readByCountOfPages(long countOfPages) throws DAOException;

    List<Book> sortByTitle() throws DAOException;

    List<Book> sortByAuthor() throws DAOException;

    List<Book> sortByYearPublication() throws DAOException;

    List<Book> sortByCountOfPages() throws DAOException;

    List<Book> sortById() throws DAOException;
    Book readById(long findingBookId) throws DAOException;
}
