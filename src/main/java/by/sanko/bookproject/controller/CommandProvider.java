package by.sanko.bookproject.controller;

import by.sanko.bookproject.controller.command.ActionCommand;
import by.sanko.bookproject.controller.command.CommandType;
import by.sanko.bookproject.controller.command.impl.EmptyCommand;

public class CommandProvider {

    CommandProvider(){
    }

    public ActionCommand defineCommand(String command) {
        ActionCommand definedCommand = null;
        if(command != null && !command.isBlank()){
            try{
                definedCommand = CommandType.valueOf(command.toUpperCase()).getCurrentCommand();
            }catch (IllegalArgumentException e){
                definedCommand = new EmptyCommand();
            }
        }
        return definedCommand;
    }

}
