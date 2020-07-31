package by.sanko.bookproject.validator;

import java.time.Year;
import java.util.List;

public class BookValidator {
    private static final int MAX_TITLE_LENGTH = 255;
    private static final int MAX_AUTHOR_LENGTH = 30;
    private static final int MAX_AUTHORS_NUMBER = 5;

    public boolean validateAllBookElements(String tittle, List<String> authors, int yearPublication, long countOfPage) {
        return validateTittle(tittle) && validateAuthors(authors) &&
                validateYearPublication(yearPublication) && validateCountOfPages(countOfPage);
    }

    public boolean validateTittle(String tittle){
        boolean isValid = false;

        if(tittle != null && !tittle.isBlank()){
            if(tittle.length() > 0 && tittle.length() <= MAX_TITLE_LENGTH ){
                isValid = true;
            }
        }

        return isValid;
    }

    public boolean validateAuthors(List<String> authors) {
        boolean isValid = false;
        int correctAuthors = 0;
        if(authors != null && !authors.isEmpty() && authors.size() <= MAX_AUTHORS_NUMBER){
            for(String author : authors){
                if(author != null && author.length() <= MAX_AUTHOR_LENGTH){
                    correctAuthors++;
                }
            }
            isValid = correctAuthors == authors.size();
        }
        return isValid;
    }

    public boolean validateYearPublication (int yearPublication){

        return (yearPublication > 0) && (yearPublication <= Year.now().getValue());
    }

    public boolean validateCountOfPages(long countOfPages) {
        return countOfPages > 0;
    }
}
