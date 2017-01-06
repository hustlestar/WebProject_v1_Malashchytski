package by.hustlestar.controller;

import by.hustlestar.command.Command;
import by.hustlestar.command.impl.admin.*;
import by.hustlestar.command.impl.guest.*;
import by.hustlestar.command.impl.user.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hustler on 27.10.2016.
 */
class CommandProvider {
    private static final Logger logger = LogManager.getLogger();

    private Map<CommandName, Command> adminCommands = new HashMap<>();
    private Map<CommandName, Command> userCommands = new HashMap<>();
    private Map<CommandName, Command> guestCommands = new HashMap<>();

    private static final String GUEST = "guest";
    private static final String USER = "user";
    private static final String MODER = "moder";
    private static final String ADMIN = "admin";

    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {

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

    }

    static CommandProvider getInstance() {
        return instance;
    }

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
            logger.log(Level.ERROR, "No such command", e);
            command = guestCommands.get(CommandName.ALL_MOVIES);
        }
        return command;
    }
}
