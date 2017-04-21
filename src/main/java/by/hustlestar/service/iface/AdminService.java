package by.hustlestar.service.iface;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.bean.entity.User;
import by.hustlestar.service.exception.ServiceException;

import java.util.List;

/**
 * AdminService interface represents tools available only for moderators and
 * admins to perform any kind of actions with any bean entities represented
 * in the system.
 */
public interface AdminService {
    /**
     * This method is used to ban and block any user.
     *
     * @param userNickname user nickname
     * @throws ServiceException if any error occurred while processing method.
     */
    void banUser(String userNickname) throws ServiceException;

    /**
     * This method is used to give back access to the system for previously
     * banned users.
     *
     * @param userNickname user nickname
     * @throws ServiceException if any error occurred while processing method.
     */
    void unbanUser(String userNickname) throws ServiceException;

    /**
     * This method is used to show all users registered in the system.
     *
     * @return List of User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * This method is used to show all banned users in the system.
     *
     * @return List of banned User beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<User> getAllBannedUsers() throws ServiceException;

    /**
     * This method is used to add new movie to the system.
     *
     * @param titleRu movie title in russian
     * @param titleEn movie title in english
     * @param year    movie year
     * @param budget  movie budget
     * @param gross   movie gross
     * @throws ServiceException if any error occurred while processing method.
     */
    void addMovie(String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException;

    /**
     * This method is used to update any movie information.
     *
     * @param id      of movie
     * @param titleRu movie title in russian
     * @param titleEn movie title in english
     * @param year    of movie
     * @param budget  of movie
     * @param gross   of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateMovie(String id, String titleRu, String titleEn, String year, String budget, String gross) throws ServiceException;

    /**
     * This method is used to delete any user review.
     *
     * @param movieID      id of movie
     * @param userNickname nickname of user
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteReview(String movieID, String userNickname) throws ServiceException;

    /**
     * This method is used to add a country for any movie in the system
     *
     * @param movieID id of movie
     * @param nameRu  name of country
     * @param nameEn  name of country
     * @throws ServiceException if any error occurred while processing method.
     */
    void addCountryForMovie(String movieID, String nameRu, String nameEn) throws ServiceException;

    /**
     * This method is used to delete any country for any movie.
     *
     * @param movieID id of movie
     * @param nameEn  name in english of country
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteCountryForMovie(String movieID, String nameEn) throws ServiceException;

    /**
     * This method is used to add any genre for any movie.
     *
     * @param movieID id of movie
     * @param nameRu  name of genre in russian
     * @param nameEn  name of genre in english
     * @throws ServiceException if any error occurred while processing method.
     */
    void addGenreForMovie(String movieID, String nameRu, String nameEn) throws ServiceException;

    /**
     * This method is used to delete genre for movie.
     *
     * @param movieID id of movie
     * @param nameEn  name of genre for movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteGenreForMovie(String movieID, String nameEn) throws ServiceException;

    /**
     * This method is used to add new actor to the system.
     *
     * @param nameRu name of actor in russian
     * @param nameEn name of actor in english
     * @throws ServiceException if any error occurred while processing method.
     */
    void addActor(String nameRu, String nameEn) throws ServiceException;

    /**
     * This method is used to update any actor information.
     *
     * @param actorID id of actor
     * @param nameRu  name of actor in russian
     * @param nameEn  name of actor in english
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateActor(String actorID, String nameRu, String nameEn) throws ServiceException;

    /**
     * This method is used to add any actor for any movie.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void addActorForMovie(String actorID, String movieID) throws ServiceException;

    /**
     * This method is used to add director for any movie.
     *
     * @param actorID id of director
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void addDirectorForMovie(String actorID, String movieID) throws ServiceException;

    /**
     * This method is used to delete actor from any movie.
     *
     * @param actorID actor id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteActorForMovie(String actorID, String movieID) throws ServiceException;

    /**
     * This method is used to remove director from any movie.
     *
     * @param actorID id of director
     * @param movieID id of movie
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteDirectorForMovie(String actorID, String movieID) throws ServiceException;

    /**
     * This method is used to add new news to the system.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @throws ServiceException if any error occurred while processing method.
     */
    void addNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn) throws ServiceException;

    /**
     * This method is used to update any news information.
     *
     * @param newsTitleRu news title in russian
     * @param newsTitleEn news title in english
     * @param newsTextRu  news text in russian
     * @param newsTextEn  news text in english
     * @param newsID      id of news
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateNews(String newsTitleRu, String newsTitleEn, String newsTextRu, String newsTextEn, String newsID) throws ServiceException;

    /**
     * This method is used to add any actor to any news.
     *
     * @param actorID id of actor
     * @param newsID  id of news
     * @throws ServiceException if any error occurred while processing method.
     */
    void addActorForNews(String actorID, String newsID) throws ServiceException;

    /**
     * This method is used to delete actor from news.
     *
     * @param actorID actor id
     * @param newsID  news id
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteActorForNews(String actorID, String newsID) throws ServiceException;

    /**
     * This method is used to add movie for any news.
     *
     * @param newsID  news id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
    void addMovieForNews(String newsID, String movieID) throws ServiceException;

    /**
     * This method is used to delete movie from any news.
     *
     * @param newsID  news id
     * @param movieID movie id
     * @throws ServiceException if any error occurred while processing method.
     */
    void deleteMovieForNews(String newsID, String movieID) throws ServiceException;

    /**
     * This method is used to show list of all actors in the system.
     *
     * @return List of Actor beans with filled in fields.
     * @throws ServiceException if any error occurred while processing method.
     */
    List<Actor> showAllActors() throws ServiceException;

    /**
     * This method is used to update image path for any entity type.
     *
     * @param entity   type of bean
     * @param filename name of file
     * @param path     path
     * @throws ServiceException if any error occurred while processing method.
     */
    void updateImage(String entity, String filename, String path) throws ServiceException;
}
