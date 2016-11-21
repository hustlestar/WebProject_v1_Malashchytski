package by.hustlestar.controller;

import by.hustlestar.command.Command;
import by.hustlestar.command.impl.admin.*;
import by.hustlestar.command.impl.common.ChangeLanguage;
import by.hustlestar.command.impl.movie.*;
import by.hustlestar.command.impl.rating.AddRating;
import by.hustlestar.command.impl.review.AddReview;
import by.hustlestar.command.impl.review.LikeReview;
import by.hustlestar.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hustler on 27.10.2016.
 */
class CommandProvider {

    private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();
    private static final CommandProvider instance = new CommandProvider();


    private CommandProvider() {
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.REGISTER, new Register());
        commands.put(CommandName.MY_PROFILE, new MyProfile());
        commands.put(CommandName.LOG_OUT, new Logout());
        commands.put(CommandName.ALL_MOVIES, new ShowAllMovie());
        commands.put(CommandName.MOVIE_BY_ID, new ShowMovieByID());
        commands.put(CommandName.FIND_MOVIE_BY_TITLE, new FindMovieByTitle());
        commands.put(CommandName.ADD_MOVIE, new AddMovie());
        commands.put(CommandName.UPDATE_MOVIE, new UpdateMovie());
        commands.put(CommandName.BAN_USER, new BanUser());
        commands.put(CommandName.UNBAN_USER, new UnbanUser());
        commands.put(CommandName.DELETE_REVIEW, new DeleteReview());
        commands.put(CommandName.UPDATE_MOVIE, new UpdateMovie());
        commands.put(CommandName.MOVIES_BY_COUNTRY, new ShowMoviesByCountry());
        commands.put(CommandName.MOVIES_BY_GENRE, new ShowMoviesByGenre());
        commands.put(CommandName.MOVIES_OF_TEN_YEAR_PERIOD, new ShowMoviesOfTenYearPeriod());
        commands.put(CommandName.MOVIES_OF_YEAR, new ShowMoviesOfYear());
        commands.put(CommandName.ADD_REVIEW, new AddReview());
        commands.put(CommandName.LIKE_REVIEW, new LikeReview());
        commands.put(CommandName.ADD_RATING, new AddRating());
        commands.put(CommandName.VIEW_ALL_USERS, new ViewAllUser());
        commands.put(CommandName.VIEW_ALL_BANNED_USERS, new ViewAllBannedUser());
        commands.put(CommandName.VIEW_USER, new ViewUser());
        commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());

    }

    static CommandProvider getInstance() {
        return instance;
    }

    Command getCommand(String commandName){
        String cmd = commandName.replace("-","_").toUpperCase();
        CommandName name = CommandName.valueOf(cmd);
        Command command;
        command = commands.get(name);
        return command;
    }
}
