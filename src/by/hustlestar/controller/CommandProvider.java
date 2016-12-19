package by.hustlestar.controller;

import by.hustlestar.command.Command;
import by.hustlestar.command.impl.actor.*;
import by.hustlestar.command.impl.admin.*;
import by.hustlestar.command.impl.common.ChangeLanguage;
import by.hustlestar.command.impl.movie.*;
import by.hustlestar.command.impl.news.ViewLatestNews;
import by.hustlestar.command.impl.news.ViewNews;
import by.hustlestar.command.impl.rating.AddRating;
import by.hustlestar.command.impl.review.AddReview;
import by.hustlestar.command.impl.review.LikeReview;
import by.hustlestar.command.impl.review.ViewLatestReviews;
import by.hustlestar.command.impl.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hustler on 27.10.2016.
 */
class CommandProvider {
    private static final Logger LOGGER = LogManager.getLogger();

    //private Map<CommandName, Map<CommandName, Command>> allCommands = new HashMap<>();
    //private Map<CommandName, Command> commands = new HashMap<>();
    private Map<CommandName, Command> adminCommands = new HashMap<>();
    private Map<CommandName, Command> userCommands = new HashMap<>();
    private Map<CommandName, Command> guestCommands = new HashMap<>();

    private static final String GUEST = "guest";
    private static final String USER = "user";
    private static final String MODER = "moder";
    private static final String ADMIN = "admin";

    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
/*
        allCommands.put(CommandName.LOGIN, guestCommands);
        allCommands.put(CommandName.REGISTER, guestCommands);
        allCommands.put(CommandName.VIEW_USER, guestCommands);
        allCommands.put(CommandName.CHANGE_LANGUAGE, guestCommands);
        allCommands.put(CommandName.ALL_MOVIES, guestCommands);
        allCommands.put(CommandName.MOVIE_BY_ID, guestCommands);
        allCommands.put(CommandName.FIND_MOVIE_BY_TITLE, guestCommands);
        allCommands.put(CommandName.MOVIES_BY_COUNTRY, guestCommands);
        allCommands.put(CommandName.MOVIES_BY_GENRE, guestCommands);
        allCommands.put(CommandName.MOVIES_OF_TEN_YEAR_PERIOD, guestCommands);
        allCommands.put(CommandName.MOVIES_OF_YEAR, guestCommands);
        allCommands.put(CommandName.VIEW_ACTOR, guestCommands);
        allCommands.put(CommandName.VIEW_ALL_USERS, guestCommands);

        allCommands.put(CommandName.ADD_REVIEW, userCommands);
        allCommands.put(CommandName.LIKE_REVIEW, userCommands);
        allCommands.put(CommandName.ADD_RATING, userCommands);
        allCommands.put(CommandName.MY_PROFILE, userCommands);
        allCommands.put(CommandName.LOG_OUT, userCommands);

        allCommands.put(CommandName.ADD_MOVIE, adminCommands);
        allCommands.put(CommandName.ADD_COUNTRY_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.DELETE_COUNTRY_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.ADD_GENRE_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.DELETE_GENRE_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.UPDATE_MOVIE, adminCommands);
        allCommands.put(CommandName.BAN_USER, adminCommands);
        allCommands.put(CommandName.UNBAN_USER, adminCommands);
        allCommands.put(CommandName.DELETE_REVIEW, adminCommands);
        allCommands.put(CommandName.UPDATE_MOVIE, adminCommands);
        allCommands.put(CommandName.VIEW_ALL_BANNED_USERS, adminCommands);
        allCommands.put(CommandName.ADD_ACTOR, adminCommands);
        allCommands.put(CommandName.UPDATE_ACTOR, adminCommands);
        allCommands.put(CommandName.ADD_ACTOR_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.DELETE_ACTOR_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.ADD_DIRECTOR_FOR_MOVIE, adminCommands);
        allCommands.put(CommandName.DELETE_DIRECTOR_FOR_MOVIE, adminCommands);
*/

        guestCommands.put(CommandName.LOGIN, new Login());
        guestCommands.put(CommandName.REGISTER, new Register());
        guestCommands.put(CommandName.VIEW_USER, new ViewUser());
        guestCommands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
        guestCommands.put(CommandName.ALL_MOVIES, new ShowAllMovie());
        guestCommands.put(CommandName.MOVIE_BY_ID, new ShowMovieByID());
        guestCommands.put(CommandName.FIND_MOVIE_BY_TITLE, new FindMovieByTitle());
        guestCommands.put(CommandName.MOVIES_BY_COUNTRY, new ShowMoviesByCountry());
        guestCommands.put(CommandName.MOVIES_BY_GENRE, new ShowMoviesByGenre());
        guestCommands.put(CommandName.MOVIES_OF_TEN_YEAR_PERIOD, new ShowMoviesOfTenYearPeriod());
        guestCommands.put(CommandName.MOVIES_OF_YEAR, new ShowMoviesOfYear());
        guestCommands.put(CommandName.VIEW_ACTOR, new ViewActor());
        guestCommands.put(CommandName.VIEW_ALL_USERS, new ViewAllUser());
        guestCommands.put(CommandName.VIEW_NEWS, new ViewNews());
        guestCommands.put(CommandName.VIEW_LATEST_NEWS, new ViewLatestNews());
        guestCommands.put(CommandName.VIEW_LATEST_REVIEWS, new ViewLatestReviews());
        guestCommands.put(CommandName.LATEST_MOVIES, new ShowLatestMovies());
        guestCommands.put(CommandName.VIEW_TOP_USERS, new ViewTopUsers());


        userCommands.putAll(guestCommands);
        userCommands.put(CommandName.ADD_REVIEW, new AddReview());
        userCommands.put(CommandName.LIKE_REVIEW, new LikeReview());
        userCommands.put(CommandName.ADD_RATING, new AddRating());
        userCommands.put(CommandName.MY_PROFILE, new MyProfile());
        userCommands.put(CommandName.LOG_OUT, new Logout());


        adminCommands.putAll(userCommands);
        adminCommands.put(CommandName.ADD_MOVIE, new AddMovie());
        adminCommands.put(CommandName.ADD_COUNTRY_FOR_MOVIE, new AddCountryForMovie());
        adminCommands.put(CommandName.DELETE_COUNTRY_FOR_MOVIE, new DeleteCountryForMovie());
        adminCommands.put(CommandName.ADD_GENRE_FOR_MOVIE, new AddGenreForMovie());
        adminCommands.put(CommandName.DELETE_GENRE_FOR_MOVIE, new DeleteGenreForMovie());
        adminCommands.put(CommandName.UPDATE_MOVIE, new UpdateMovie());
        adminCommands.put(CommandName.BAN_USER, new BanUser());
        adminCommands.put(CommandName.UNBAN_USER, new UnbanUser());
        adminCommands.put(CommandName.DELETE_REVIEW, new DeleteReview());
        adminCommands.put(CommandName.UPDATE_MOVIE, new UpdateMovie());
        adminCommands.put(CommandName.VIEW_ALL_BANNED_USERS, new ViewAllBannedUser());
        adminCommands.put(CommandName.ADD_ACTOR, new AddActor());
        adminCommands.put(CommandName.UPDATE_ACTOR, new UpdateActor());
        adminCommands.put(CommandName.ADD_ACTOR_FOR_MOVIE, new AddActorForMovie());
        adminCommands.put(CommandName.DELETE_ACTOR_FOR_MOVIE, new DeleteActorForMovie());
        adminCommands.put(CommandName.ADD_DIRECTOR_FOR_MOVIE, new AddDirectorForMovie());
        adminCommands.put(CommandName.DELETE_DIRECTOR_FOR_MOVIE, new DeleteDirectorForMovie());
        adminCommands.put(CommandName.ADD_NEWS, new AddNews());
        adminCommands.put(CommandName.UPDATE_NEWS, new UpdateNews());
        adminCommands.put(CommandName.ADD_ACTOR_FOR_NEWS, new AddActorForNews());
        adminCommands.put(CommandName.DELETE_ACTOR_FOR_NEWS, new DeleteActorForNews());
        adminCommands.put(CommandName.ADD_MOVIE_FOR_NEWS, new AddMovieForNews());
        adminCommands.put(CommandName.DELETE_MOVIE_FOR_NEWS, new DeleteMovieForNews());
        adminCommands.put(CommandName.VIEW_ALL_ACTORS, new ViewAllActors());

        /*
        commands.put(CommandName.LOGIN, new Login());
        commands.put(CommandName.REGISTER, new Register());
        commands.put(CommandName.MY_PROFILE, new MyProfile());
        commands.put(CommandName.LOG_OUT, new Logout());
        commands.put(CommandName.ALL_MOVIES, new ShowAllMovie());
        commands.put(CommandName.MOVIE_BY_ID, new ShowMovieByID());
        commands.put(CommandName.FIND_MOVIE_BY_TITLE, new FindMovieByTitle());
        commands.put(CommandName.ADD_MOVIE, new AddMovie());
        commands.put(CommandName.ADD_COUNTRY_FOR_MOVIE, new AddCountryForMovie());
        commands.put(CommandName.DELETE_COUNTRY_FOR_MOVIE, new DeleteCountryForMovie());
        commands.put(CommandName.ADD_GENRE_FOR_MOVIE, new AddGenreForMovie());
        commands.put(CommandName.DELETE_GENRE_FOR_MOVIE, new DeleteGenreForMovie());
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
        */
    }

    static CommandProvider getInstance() {
        return instance;
    }

    /*
    Command getCommand(String commandName){
        String cmd = commandName.replace("-","_").toUpperCase();
        CommandName name = null;
        Command command;
        try {
            name = CommandName.valueOf(cmd);
            command = commands.get(name);
        } catch (IllegalArgumentException e) {
            LOGGER.error("No such command", e);
            command = commands.get(CommandName.ALL_MOVIES);
        }
        return command;
    }
    */

    Command getCommandForUser(String type, String commandName) {
        String cmd = commandName.replace("-", "_").toUpperCase();
        CommandName name = null;
        Command command;
        try {
            name = CommandName.valueOf(cmd);
            switch (type) {
                case GUEST:
                    return guestCommands.get(name);
                case USER:
                    return userCommands.get(name);
                case MODER:
                    return adminCommands.get(name);
                case ADMIN:
                    return adminCommands.get(name);
                default:
                    return guestCommands.get(name);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("No such command", e);
            command = guestCommands.get(CommandName.ALL_MOVIES);
        }
        return command;
    }
}
