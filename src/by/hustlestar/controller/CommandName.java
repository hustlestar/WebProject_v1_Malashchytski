package by.hustlestar.controller;

/**
 * List of all available commands.
 */
enum CommandName {
    LOGIN, REGISTER, LOG_OUT, MY_PROFILE,
    LATEST_MOVIES, ALL_MOVIES, MOVIE_BY_ID, FIND_MOVIE_BY_TITLE,
    ADD_MOVIE, UPDATE_MOVIE,
    ADD_COUNTRY_FOR_MOVIE, DELETE_COUNTRY_FOR_MOVIE, ADD_GENRE_FOR_MOVIE, DELETE_GENRE_FOR_MOVIE,
    BAN_USER, UNBAN_USER, DELETE_REVIEW,
    MOVIES_BY_COUNTRY, MOVIES_BY_GENRE,
    MOVIES_OF_TEN_YEAR_PERIOD, MOVIES_OF_YEAR,
    ADD_REVIEW, LIKE_REVIEW, VIEW_TOP_REVIEWS, VIEW_LATEST_REVIEWS,
    ADD_RATING,
    VIEW_ALL_USERS, VIEW_USER, VIEW_ALL_BANNED_USERS, VIEW_TOP_USERS,
    CHANGE_LANGUAGE,
    VIEW_ALL_ACTORS,VIEW_ACTOR, ADD_ACTOR, UPDATE_ACTOR, ADD_ACTOR_FOR_MOVIE, DELETE_ACTOR_FOR_MOVIE, ADD_DIRECTOR_FOR_MOVIE, DELETE_DIRECTOR_FOR_MOVIE,
    VIEW_LATEST_NEWS, VIEW_NEWS, ADD_NEWS, UPDATE_NEWS, ADD_ACTOR_FOR_NEWS, DELETE_ACTOR_FOR_NEWS, ADD_MOVIE_FOR_NEWS, DELETE_MOVIE_FOR_NEWS,
    UPLOAD_PHOTO,
}
