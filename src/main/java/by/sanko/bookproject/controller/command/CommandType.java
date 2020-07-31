package by.sanko.bookproject.controller.command;

import by.sanko.bookproject.controller.command.impl.*;

public enum CommandType {

    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_BY_ID(new FindByIdCommand()),
    FIND_BY_TITTLE(new FindByTittleCommand()),
    FIND_BY_YEAR_OF_PUBLICATION(new FindByYearOfPublication()),
    FIND_BY_COUNT_OF_PAGE(new FindByCountOfPageCommand()),
    FIND_BY_AUTHOR(new FindByAuthorCommand()),
    SORT_BY_TITTLE(new SortByTittleCommand()),
    SORT_BY_AUTHOR(new SortByAuthorCommand()),
    SORT_BY_YEAR_OF_PUBLICATION(new SortByYearPublication()),
    SORT_BY_ID(new SortByIdCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
