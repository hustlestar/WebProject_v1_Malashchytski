package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * ReviewDAO interface describes methods to work with Review bean stored in data source.
 */
public interface ReviewDAO {
    /**
     * This method is used to get reviews for some movie from data source
     *
     * @param id             of movie
     * @param lang           language of review
     * @param offset         of first entry
     * @param recordsPerPage records to get
     * @return list of filled Review beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Review> getReviewsForMovie(int id, String lang, int offset, int recordsPerPage) throws DAOException;

    /**
     * This method is used to get reviews for some particular user from data source
     *
     * @param nickname of user
     * @return list of filled Review beans
     * @throws DAOException if some error occurred while processing data.
     */
    List<Review> getReviewsForUser(String nickname) throws DAOException;

    /**
     * This method is used to add review of some user to data source.
     *
     * @param intMovieID   movie id
     * @param userNickname nickname of user
     * @param review       review text
     * @param lang         language of review
     * @throws DAOException if some error occurred while processing data.
     */
    void addReview(int intMovieID, String userNickname, String review, String lang) throws DAOException;

    /**
     * This method is used to remove review from data source.
     *
     * @param intMovieID   movie id
     * @param userNickname nickname of reviewer
     * @throws DAOException if some error occurred while processing data.
     */
    void deleteReview(int intMovieID, String userNickname) throws DAOException;

    /**
     * This method is used to count number of reviews for a particular movie.
     *
     * @param normId id of movie
     * @param lang   language of reviews
     * @return number of reviews
     * @throws DAOException if some error occurred while processing data.
     */
    int countReviewsForMovie(int normId, String lang) throws DAOException;
}
