package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Actor;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * ActorDAO interface represents all methods for working with Actor beans mainly.
 */
public interface ActorDAO {
    /**
     * This method gets list of Actors from data source.
     *
     * @param normId id of actor
     * @return list of filled Actor beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Actor> getActorsForMovie(int normId) throws DAOException;

    /**
     * This method gets director for any movie from data source.
     *
     * @param normId id of actor
     * @return filled Actor bean
     * @throws DAOException if some error occurred while processing data.
     */
    Actor getDirectorForMovie(int normId) throws DAOException;

    /**
     * This method is used to retrieve actor by id from data source.
     *
     * @param normId id of actor
     * @return filled Actor bean
     * @throws DAOException if some error occurred while processing data.
     */
    Actor getActor(int normId) throws DAOException;

    /**
     * This method is used to insert data about new actor into data source.
     *
     * @param nameRu name of actor in russian
     * @param nameEn name of actor in english
     * @throws DAOException if some error occurred while processing data.
     */
    void addActor(String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to update data about any actor in data source.
     *
     * @param actorID actor id
     * @param nameRu  name of actor in russian
     * @param nameEn  name of actor in english
     * @throws DAOException if some error occurred while processing data.
     */
    void updateActor(int actorID, String nameRu, String nameEn) throws DAOException;

    /**
     * This method is used to add connection between some Movie and Actor into data source.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void addActorForMovie(int actorID, int movieID) throws DAOException;

    /**
     * This method is used to delete connection between some Movie and Actor from data source.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteActorForMovie(int actorID, int movieID) throws DAOException;

    /**
     * This method is used to add connection between some Movie and Actor as director into data source.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void addDirectorForMovie(int actorID, int movieID) throws DAOException;

    /**
     * This method is used to remove connection between some Movie and Actor as director from data source.
     *
     * @param actorID id of actor
     * @param movieID id of movie
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteDirectorForMovie(int actorID, int movieID) throws DAOException;

    /**
     * This method is used to retrieve list of all actors connected with this news
     *
     * @param id id of news
     * @return list of filled Actor beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Actor> getActorsForNews(int id) throws DAOException;

    /**
     * This method is used to get list of all actors from data source.
     *
     * @return list of filled Actor beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Actor> getAllActors() throws DAOException;

    /**
     * This method is used to retrieve the most recently added actor from data source.
     *
     * @return filled Actor bean
     * @throws DAOException if some error occurred while processing data.
     */
    Actor getLastInsertedActor() throws DAOException;

    /**
     * This method is used to delete actor from data source, used only for tests.
     *
     * @param id of actor
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteActor(int id) throws DAOException;

    /**
     * This method is used to update information about image path
     *
     * @param id   of actor
     * @param path path to image
     * @throws DAOException if some error occurred while processing data.
     */
    void updateImage(int id, String path) throws DAOException;
}
