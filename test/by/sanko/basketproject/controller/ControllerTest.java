package by.sanko.basketproject.controller;

import by.sanko.bookproject.controller.Controller;
import by.sanko.bookproject.exception.DAOException;
import by.sanko.bookproject.model.dao.impl.BookListDaoImpl;
import by.sanko.bookproject.model.entity.Book;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

public class ControllerTest {
    private final Controller controller = new Controller();
    private final BookListDaoImpl bookListDao = new BookListDaoImpl();

    @BeforeTest
    public void init(){
       try {
            bookListDao.create(new Book( "JAVA_Methods_Prog", Collections.singletonList("Blinov"),2020 , 400));
            bookListDao.create(new Book( "Idiot", Collections.singletonList("Dostoevsky"),1869 , 667));
            bookListDao.create(new Book( "1984", Collections.singletonList("George Orwell"),1949 , 328));
            bookListDao.create(new Book( "The Master and Margarita", Collections.singletonList("Mikhail Bulgakov"),1967 , 384));


        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoRequestAddBook() throws DAOException {
        Map<String, Object> params = new HashMap<>();
        params.put("tittle", "HolyWar");
        params.put("countOfPage", 1000);
        params.put("yearOfPublication", 20);
        params.put("author", new String[]{"Ivanov", "Sodorov"});
        controller.doBookAction("add_Book", params);
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Ivanov");
        authors.add("Sodorov");
        Book expected = new Book(5, "HolyWar", authors, 20, 1000);
        Book actual = null;
        actual = bookListDao.read(5);
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestAddBook")
    public void testDoRequestFindById() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 5);
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Ivanov");
        authors.add("Sodorov");
        Optional<Book> expected = Optional.of(new Book(5, "HolyWar", authors, 20, 1000));
        Optional<Book> actual = (Optional<Book>) controller.doBookAction("find_by_id", params).get("result");
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestFindById")
    public void testDoRequestSortByName() {
        ArrayList<Book> actual = new ArrayList<>();
        actual.add(new Book(3, "1984", Collections.singletonList("GeorgeOrwell"),1949 , 328));
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Ivanov");
        authors.add("Sodorov");
        actual.add(new Book(5, "HolyWar", authors, 20, 1000));
        actual.add(new Book( 2,"Idiot", Collections.singletonList("Dostoevsky"),1869 , 667));
        actual.add(new Book( 1,"JAVA_Methods_Prog", Collections.singletonList("Blinov"),2020 , 400));
        actual.add(new Book( 4,"The Master and Margarita", Collections.singletonList("MikhailBulgakov"),1967 , 384));
        List<Book> expected = (List<Book>)controller.doBookAction("sort_BY_tittle", new HashMap<>()).get("result");
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestSortByName")
    public void testDoRequestRemove() throws DAOException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 5);
        params.put("tittle", "HolyWar");
        params.put("countOfPage", 1000);
        params.put("yearOfPublication", 20);
        params.put("author", new String[]{"Ivanov", "Sodorov"});
        controller.doBookAction("remove_Book", params);
        Optional<Book> expected = Optional.of(bookListDao.read(5));
        Optional<Book> actual = Optional.empty();
        assertEquals(actual, expected);
    }
}
