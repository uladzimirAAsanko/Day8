package by.sanko.bookproject.model.service.impl;

import by.sanko.bookproject.exception.DAOException;
import by.sanko.bookproject.exception.ProjectException;
import by.sanko.bookproject.model.dao.impl.BookListDaoImpl;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.BookService;
import by.sanko.bookproject.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public boolean addBook(Book book) throws ProjectException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        boolean answer;

        try {
            answer = bookListDao.create(book);
        }catch (DAOException e){
            throw new ProjectException("Error while adding book to storage");
        }
        return answer;
    }

    @Override
    public boolean removeBook(Book book) throws ProjectException {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        boolean result;

        try {
            result = bookListDao.delete(book);
        }catch (DAOException e){
            throw new ProjectException("Error while deleting book from storage");
        }
        return result;
    }

    @Override
    public Optional<Book> findById(long bookId) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        Optional<Book> targetBook = Optional.empty();
        try {
            targetBook = Optional.of(bookListDao.read((int)bookId));
        } catch (DAOException e) {
            targetBook = Optional.empty();
        }

        return targetBook;
    }

    @Override
    public List<Book> findByTitle(String title) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator validator = new BookValidator();
        List<Book> answer = new ArrayList<>();
        if(validator.validateTittle(title)){
            try {
                answer = bookListDao.readByTitle(title);
            } catch (DAOException e) {
                answer = new ArrayList<>();
            }
        }
        return answer;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator validator = new BookValidator();
        List<Book> answer = new ArrayList<>();
        List<String> authors = new ArrayList<>();
        authors.add(author);
        if(validator.validateAuthors(authors)){
            try {
                answer = bookListDao.readByAuthor(author);
            } catch (DAOException e) {
                answer = new ArrayList<>();
            }
        }
        return answer;
    }

    @Override
    public List<Book> findByYearPublication(int yearPublication) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator validator = new BookValidator();
        List<Book> answer = new ArrayList<>();
        if(validator.validateYearPublication(yearPublication)){
            try {
                answer = bookListDao.readByYearPublication(yearPublication);
            } catch (DAOException e) {
                answer = new ArrayList<>();
            }
        }
        return answer;
    }

    @Override
    public List<Book> findByCountOfPages(long countOfPages) {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        BookValidator validator = new BookValidator();
        List<Book> answer = new ArrayList<>();
        if(validator.validateCountOfPages(countOfPages)){
            try {
                answer = bookListDao.readByCountOfPages(countOfPages);
            } catch (DAOException e) {
                answer = new ArrayList<>();
            }
        }
        return answer;
    }

    @Override
    public List<Book> sortById() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = null;
        try {
            sortedList = bookListDao.sortById();
        } catch (DAOException e) {
            sortedList = null;
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByTitle() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = null;
        try {
            sortedList = bookListDao.sortByTitle();
        } catch (DAOException e) {
            sortedList = null;
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByAuthor() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = null;
        try {
            sortedList = bookListDao.sortByAuthor();
        } catch (DAOException e) {
            sortedList = null;
        }

        return sortedList;
    }

    @Override
    public List<Book> sortByYearPublication() {
        BookListDaoImpl bookListDao = new BookListDaoImpl();
        List<Book> sortedList = null;
        try {
            sortedList = bookListDao.sortByYearPublication();
        } catch (DAOException e) {
            sortedList = null;
        }

        return sortedList;
    }
}
