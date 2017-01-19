package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Rating;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * RatingDAO interface represents methods to interact with Rating bean mainly.
 */
public interface RatingDAO {
    /**
     * This method is used to get Rating for a movie.
     *
     * @param id id of movie
     * @return list of filled rating beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Rating> getRatingsForMovie(int id) throws DAOException;

    /**
     * This method is used to get rating user gave to any movies.
     *
     * @param nickname of user
     * @return list of filled movie beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Rating> getRatingsOfUser(String nickname) throws DAOException;

    /**
     * This method is used to check if there is an entry of this user for this movie.
     *
     * @param intMovieID   movie id
     * @param userNickname user nickname
     * @return filled rating bean
     * @throws DAOException if some error occurred while processing data.
     */
    Rating checkRating(int intMovieID, String userNickname) throws DAOException;

    /**
     * This method is used to add rating of some user for some mvoie.
     *
     * @param intMovieID   id of movie
     * @param userNickname nickname of user
     * @param rating       user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void addRating(int intMovieID, String userNickname, int rating) throws DAOException;

    /**
     * This method is used to update rating some user gave to some movie in data source.
     *
     * @param intMovieID   id of movie
     * @param userNickname user nickname
     * @param intRating    rating user gave
     * @throws DAOException if some error occurred while processing data.
     */
    void updateRating(int intMovieID, String userNickname, int intRating) throws DAOException;

    /**
     * This method is used to remove rating some user gave for some movie and used only for tests!!!
     *
     * @param movieID      id of movie
     * @param userNickname nickname of user
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteRating(int movieID, String userNickname) throws DAOException;
}
