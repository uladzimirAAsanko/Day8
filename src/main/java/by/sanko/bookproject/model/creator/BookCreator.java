package by.sanko.bookproject.model.creator;

import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.validator.BookValidator;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class BookCreator {
    private static final String LINE_DELIMITER = ";";
    private static final String ELEMENT_DELIMITER = ":";

    public Optional<Book> createBook(String data) {
        BookValidator validator = new BookValidator();
        String[] bookElements = data.split(LINE_DELIMITER);
        String tittle = "";
        List<String> authors = null;
        int yearPublication = 0;
        int countOfPages = 0;
        Optional<Book> createdBook = Optional.empty();

        for(String bookElement : bookElements){
            String[] elementParts = bookElement.split(ELEMENT_DELIMITER);
            String fieldName = elementParts[0].trim();
            String fieldValue = elementParts[1].trim();

            if (fieldName.contains("tittle")) {
                tittle = fieldValue;
            }
            if (fieldName.contains("authors")) {
                authors = parseAuthors(fieldValue);
            }
            if (fieldName.contains("yearOfPublication")) {
                yearPublication = Integer.parseInt(fieldValue);
            }
            if (fieldName.contains("countOfPages")) {
                countOfPages = Integer.parseInt(fieldValue);
            }
        }

        if (validator.validateAllBookElements(tittle, authors, yearPublication, countOfPages)) {
            createdBook = Optional.of(new Book(tittle, authors, yearPublication, countOfPages));
        }
        return createdBook;
    }

    private List<String> parseAuthors(String data) {
        List<String> authors = null;
        StringTokenizer tokenizer = new StringTokenizer(data);
        for(int i = 0; i < tokenizer.countTokens(); i++){
            String tmp = tokenizer.nextToken();
            if(tmp != null){
                authors.add(tmp);
            }
        }
        return authors;
    }
}
