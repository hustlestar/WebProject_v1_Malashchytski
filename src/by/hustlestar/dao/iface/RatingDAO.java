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

    void addRating(int intMovieID, String userNickname, int rating) throws DAOException;

}
