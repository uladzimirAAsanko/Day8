package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.RequestParam;
import by.sanko.bookproject.controller.command.param.ResponseParam;
import by.sanko.bookproject.model.entity.Book;
import by.sanko.bookproject.model.service.impl.BookServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindByIdCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        long id = (int) params.get(RequestParam.ID);
        BookServiceImpl bookService = new BookServiceImpl();
        Optional<Book> findResult = bookService.findById(id);
        if(findResult.isEmpty()){
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        }else{
            response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_SUCCESS);
            response.put(ResponseParam.RESPONSE_RESULT, findResult);
        }
        return response;
    }
}
