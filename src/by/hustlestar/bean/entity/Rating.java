package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Rating {
    private int movieID;
    private String userNickname;
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
}
