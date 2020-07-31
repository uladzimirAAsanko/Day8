package by.sanko.bookproject.controller.command.impl;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.param.ResponseParam;

import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements ActionCommand {
    @Override
    public Map<String, Object> execute(Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        response.put(ResponseParam.RESPONSE_STATUS, ResponseParam.RESPONSE_STATUS_FAIL);
        return response;
    }
}
