package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.RequestParam;
import by.sanko.bookproject.controller.command.param.ResponseParam;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindByCountOfPageCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        long countOfPage = (long) params.get(RequestParam.COUNT_OF_PAGE);
        BookServiceImpl bookService = new BookServiceImpl();
        List<Book> findResult = bookService.findByCountOfPages(countOfPage);
        if(findResult.isEmpty()){
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }else{
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
            response.put(ResponseParam.RESPONSE_RESULT, findResult);
        }
        return response;
    }
}
