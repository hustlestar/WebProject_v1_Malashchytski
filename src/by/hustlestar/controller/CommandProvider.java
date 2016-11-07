package by.hustlestar.controller;

import by.hustlestar.command.Command;
import by.hustlestar.command.impl.*;

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
        commands.put(CommandName.MY_PROFILE, new MyProfile());
        commands.put(CommandName.LOG_OUT, new LogoutCommand());
        commands.put(CommandName.ALL_MOVIES, new ShowAllMovie());
        commands.put(CommandName.ADD_MOVIE, new AddMovie());
        commands.put(CommandName.MOVIE_BY_ID, new ShowMovieByID());
        commands.put(CommandName.MOVIES_BY_COUNTRY, new ShowMoviesByCountry());

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
