package by.hustlestar.bean.entity;

import java.io.Serializable;

/**
 * Entity represents rating.
 */
public class Rating implements Serializable {
    /**
     * id of movie
     */
    private int movieID;
    /**
     * nickname of user
     */
    private String userNickname;
    /**
     * 1-10 score
     */
    private int ratingScore;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (movieID != rating.movieID) return false;
        if (ratingScore != rating.ratingScore) return false;
        return userNickname != null ? userNickname.equals(rating.userNickname) : rating.userNickname == null;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userNickname != null ? userNickname.hashCode() : 0);
        result = 31 * result + ratingScore;
        return result;
    }
}
