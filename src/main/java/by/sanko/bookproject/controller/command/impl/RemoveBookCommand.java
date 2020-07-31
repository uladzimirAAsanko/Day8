package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.RequestParam;
import by.sanko.bookproject.controller.command.param.ResponseParam;
import by.sanko.bookproject.exception.ProjectException;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.impl.BookServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RemoveBookCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        Book book = new Book((int) params.get(RequestParam.ID), (String) params.get(RequestParam.BOOK_TITTLE),
                Arrays.asList((String[]) params.get(RequestParam.AUTHOR)),
                (int)params.get(RequestParam.YEAR_OF_PUBLICATION), (int) params.get(RequestParam.COUNT_OF_PAGE));
        try{
            new BookServiceImpl().removeBook(book);
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
        } catch (ProjectException e) {
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }
        return response;
    }
}
