package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.RequestParam;
import by.sanko.bookproject.controller.command.param.ResponseParam;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.impl.BookServiceImpl;

import java.util.*;

public class FindByAuthorCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        String author = (String) params.get(RequestParam.AUTHOR);
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> findResult = bookService.findByAuthor(author);

        if(findResult.isEmpty()){
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }else{
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
            response.put(ResponseParam.RESPONSE_RESULT, findResult);
        }
        return response;
    }
}
