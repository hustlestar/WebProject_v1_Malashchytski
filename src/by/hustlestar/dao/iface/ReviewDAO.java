package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Review;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public interface ReviewDAO {

    List<Review> getReviewsForMovie(int id, String lang, int offset, int recordsPerPage) throws DAOException;

    List<Review> getReviewsForUser(String nickname) throws DAOException;

    void addReview(int intMovieID, String userNickname, String review, String lang) throws DAOException;

    void deleteReview(int intMovieID, String userNickname) throws DAOException;

    int countReviewsForMovie(int normId, String lang) throws DAOException;
}
