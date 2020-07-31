package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.ResponseParam;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByTittleCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> sortResult = bookService.sortByTitle();
        response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
        response.put(ResponseParam.RESPONSE_RESULT, sortResult);
        return response;
    }
}
