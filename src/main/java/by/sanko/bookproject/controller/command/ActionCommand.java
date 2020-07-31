package by.sanko.bookproject.controller.command;

import java.util.Map;

public interface ActionCommand {
    Map<String, Object> execute(Map<String, Object> params);
}
