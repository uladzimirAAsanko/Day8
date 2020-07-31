package by.sanko.bookproject.model.dao.impl;

import by.sanko.bookproject.exception.ConnectionDatabaseException;
import by.sanko.bookproject.exception.DAOException;
import by.sanko.bookproject.model.connection.ConnectionPool;
import by.sanko.bookproject.model.creator.BookCreator;
import by.sanko.bookproject.model.dao.BookListDao;
import by.sanko.bookproject.model.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BookListDaoImpl implements BookListDao {

    private static final String BOOK_PARAM_ID = "bookId";
    private static final String BOOK_PARAM_TITTLE = "tittle";
    private static final String BOOK_PARAM_AUTHORS = "authors";
    private static final String BOOK_PARAM_YEAR_OF_PUBLICATION = "yearOfPublication";
    private static final String BOOK_PARAM_COUNT_OF_PAGES = "countOfPages";
    private static final String SQL_ADD_BOOK = "INSERT INTO bookStorage(bookid,tittle, authors, yearOfPublication, countOfPages)" +
            "VALUES (?,?, ?, ?, ?)";
    private static final String SQL_SELECT_DATABASE = "SELECT * FROM bookStorage";
    private static final String SQL_FIND = SQL_SELECT_DATABASE + " WHERE ?=?";
    private static final String SQL_FIND_BY_ID = SQL_SELECT_DATABASE + " WHERE bookId=?";
    private static final String SQL_FIND_BY_TITTLE = SQL_SELECT_DATABASE + " WHERE tittle=?";
    private static final String SQL_FIND_BY_AUTHORS = SQL_SELECT_DATABASE + " WHERE authors=?";
    private static final String SQL_FIND_BY_YEAR_OF_PUBLICATION = SQL_SELECT_DATABASE + " WHERE yearOfPublication=?";
    private static final String SQL_FIND_BY_COUNT_OF_PAGES = SQL_SELECT_DATABASE + " WHERE countOfPages=?";
    private static final String SQL_DELETE_BOOK = "DELETE FROM bookStorage WHERE bookId = ?";
    private static final String SQL_UPDATE_BOOK = "UPDATE bookStorage SET tittle=?, authors=?, yearOfPublication=?, " +
            "countOfPages=? WHERE bookId = ?";
    private static final String SQL_SORT = SQL_SELECT_DATABASE + " order by ?";
    private static final String SQL_SORT_BY_ID = SQL_SELECT_DATABASE + " order by bookId";
    private static final String SQL_SORT_BY_TITTLE = SQL_SELECT_DATABASE + " order by tittle";
    private static final String SQL_SORT_BY_AUTHORS = SQL_SELECT_DATABASE + " order by authors";
    private static final String SQL_SORT_BY_YEAR_OF_PUBLICATION = SQL_SELECT_DATABASE + " order by yearOfPublication";
    private static final String SQL_SORT_BY_COUNT_OF_PAGES = SQL_SELECT_DATABASE + " order by countOfPages";


    @Override
    public List<Book> readByTitle(String title) throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND);
            statement.setString(1,BOOK_PARAM_TITTLE);
            statement.setString(2,title);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }



    @Override
    public List<Book> readByAuthor(String author) throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_AUTHORS);
            statement.setString(1,author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> readByYearPublication(int yearPublication) throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_YEAR_OF_PUBLICATION);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> readByCountOfPages(long countOfPages) throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_COUNT_OF_PAGES);
            statement.setString(1,""+countOfPages);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> sortByTitle() throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SORT_BY_TITTLE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> sortByAuthor() throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SORT_BY_AUTHORS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> sortByYearPublication() throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SORT_BY_YEAR_OF_PUBLICATION);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> sortByCountOfPages() throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SORT_BY_COUNT_OF_PAGES);
            statement.setString(1,BOOK_PARAM_COUNT_OF_PAGES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public List<Book> sortById() throws DAOException {
        List<Book> resultOfSearch = new ArrayList<>();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SORT_BY_ID);
            statement.setString(1,BOOK_PARAM_ID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = createBook(resultSet);
                resultOfSearch.add(book);
            }
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return resultOfSearch;
    }

    @Override
    public Book readById(long findingBookId) throws DAOException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, findingBookId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Book book = createBook(resultSet);
            return book;
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public boolean create(Book item) throws DAOException {
        boolean isCreated = false;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_BOOK);
            statement.setString(1,""+item.getBookId());
            statement.setString(2,item.getTittle());
            statement.setString(3, item.getAuthors().toString());
            statement.setInt(4, item.getYearOfPublication());
            statement.setLong(5, item.getCountOfPages());
            isCreated = statement.executeUpdate() > 0;
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public Book read(Integer tag) throws DAOException {
        Book book = readById(tag);
        return book;
    }

    @Override
    public boolean update(Book item) throws DAOException {
        boolean isUpdated = false;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BOOK);
            statement.setString(1,item.getTittle());
            statement.setString(2, item.getAuthors().toString());
            statement.setInt(3, item.getYearOfPublication());
            statement.setLong(4, item.getCountOfPages());
            statement.setLong(5, item.getBookId());
            isUpdated = statement.executeUpdate() != 0;
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Book item) throws DAOException {
        boolean isDeleted = false;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOK);
            statement.setString(1,"" + item.getBookId());
            isDeleted = statement.executeUpdate() != 0;
        } catch (ConnectionDatabaseException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return isDeleted;
    }

    private Book createBook(ResultSet resultSet) throws SQLException {
        long bookId = resultSet.getLong(BOOK_PARAM_ID);
        String title = resultSet.getString(BOOK_PARAM_TITTLE);
        ArrayList<String> authors = new ArrayList<>();
        String dataAuthors = resultSet.getString(BOOK_PARAM_AUTHORS);
        dataAuthors = dataAuthors.replaceAll("\\[","");
        dataAuthors = dataAuthors.replaceAll("]","");
        dataAuthors = dataAuthors.replaceAll(" ","");
        StringTokenizer tokenizer = new StringTokenizer(dataAuthors,",");
        String tmp = "";
        tmp = tmp.replaceAll("","");
        authors.add(tokenizer.nextToken());
        for(int i = 0; i < tokenizer.countTokens(); i++){
            //tmp = tokenizer.nextToken();
            tmp = tmp.replaceAll(" ","");
            authors.add(tokenizer.nextToken());
        }
        int yearOfPublication = resultSet.getInt(BOOK_PARAM_YEAR_OF_PUBLICATION);
        int countOfPages = resultSet.getInt(BOOK_PARAM_COUNT_OF_PAGES);
        Book book = new Book( bookId,title, authors, yearOfPublication, countOfPages);
        return book;
    }
}
