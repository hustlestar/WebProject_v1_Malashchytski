package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Review {
    private int movieID;
    private String userNickname;
    private String review;

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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
