package by.sanko.bookproject.model.entity;

import by.sanko.bookproject.util.IdCreator;

import java.util.List;
import java.util.Objects;

public class Book {
    private long bookId;
    private String tittle;
    private List<String> authors;
    private int yearOfPublication;
    private long countOfPages;

    public Book(String tittle, List<String> authors, int yearOfPublication, long countOfPages) {
        this.bookId = IdCreator.generateId();
        this.tittle = tittle;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        this.countOfPages = countOfPages;
    }

    public Book(long bookId, String tittle, List<String> authors, int yearOfPublication, long countOfPages) {

        this.bookId = bookId;
        this.tittle = tittle;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        this.countOfPages = countOfPages;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public long getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(long countOfPages) {
        this.countOfPages = countOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId() &&
                getYearOfPublication() == book.getYearOfPublication() &&
                getTittle().equals(book.getTittle()) &&
                getAuthors().equals(book.getAuthors()) &&
                getCountOfPages() == book.getCountOfPages();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 17 * result + (int) bookId;
        result = 17 * result + (tittle == null ? 0 : tittle.hashCode());
        result = 17 * result + (authors == null ? 0 : authors.hashCode());
        result = 17 * result + yearOfPublication;
        result = 17 * result + (int)countOfPages;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("bookId: ").append(bookId).append("; tittle: ").append(tittle).append("; authors: ");
        for(String author : authors){
            builder.append(author).append(" ");
        }
        builder.append("; yearOfPublication: ").append(yearOfPublication).append("; countOfPages: ").append(countOfPages);
        return builder.toString();
    }
}
