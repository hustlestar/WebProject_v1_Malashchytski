package by.hustlestar.controller;

import by.hustlestar.command.Command;
import by.hustlestar.command.impl.LoginCommand;
import by.hustlestar.command.impl.LogoutCommand;
import by.hustlestar.command.impl.RegisterCommand;
import by.hustlestar.command.impl.ShowAllMovie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hustler on 27.10.2016.
 */
class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
    private static final CommandProvider instance = new CommandProvider();


    private CommandProvider() {
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.ALL_MOVIES, new ShowAllMovie());
        commands.put(CommandName.LOG_OUT, new LogoutCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName){
        String cmd = commandName.replace("-","_").toUpperCase();
        CommandName name = CommandName.valueOf(cmd);
        Command command;
        command = commands.get(name);
        return command;
    }
}
