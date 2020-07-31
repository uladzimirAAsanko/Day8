package by.sanko.bookproject.model.comparator;

import by.sanko.bookproject.model.entity.Book;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        o1.getAuthors().sort(String::compareTo);
        o2.getAuthors().sort(String::compareTo);
        return o1.getAuthors().get(0).compareTo(o2.getAuthors().get(0));
    }
}
