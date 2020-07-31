package by.sanko.bookproject.model.comparator;

import by.sanko.bookproject.model.entity.Book;

import java.util.Comparator;


public class TitleComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTittle().compareTo(o2.getTittle());
    }
}
