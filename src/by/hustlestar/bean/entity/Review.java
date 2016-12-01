package by.hustlestar.bean.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Review {
    private int movieID;
    private String userNickname;
    private String review;
    private String reviewLang;
    private Timestamp reviewDate;
    private List<ReviewScore> reviewScores;
    private int thumbsUp;
    private int thumbsDown;

    public List<ReviewScore> getReviewScores() {
        return reviewScores;
    }

    public void setReviewScores(List<ReviewScore> reviewScores) {
        this.reviewScores = reviewScores;
    }

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

    public String getReviewLang() {
        return reviewLang;
    }

    public void setReviewLang(String reviewLang) {
        this.reviewLang = reviewLang;
    }

    public int getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(int thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public int getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(int thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }
}
