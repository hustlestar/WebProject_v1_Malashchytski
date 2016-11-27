package by.hustlestar.dao.iface;

import by.hustlestar.bean.entity.Rating;
import by.hustlestar.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Hustler on 15.11.2016.
 */
public interface RatingDAO {

    List<Rating> getRatingsForMovie(int id) throws DAOException;

    List<Rating> getRatingsOfUser(String nickname) throws DAOException;

    Rating checkRating(int intMovieID, String userNickname) throws DAOException;

    void addRating(int intMovieID, String userNickname, int rating) throws DAOException;

    void updateRating(int intMovieID, String userNickname, int intRating) throws DAOException;
}
