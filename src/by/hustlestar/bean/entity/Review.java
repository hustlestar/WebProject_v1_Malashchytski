package by.hustlestar.bean.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review1 = (Review) o;

        if (movieID != review1.movieID) return false;
        if (thumbsUp != review1.thumbsUp) return false;
        if (thumbsDown != review1.thumbsDown) return false;
        if (userNickname != null ? !userNickname.equals(review1.userNickname) : review1.userNickname != null)
            return false;
        if (review != null ? !review.equals(review1.review) : review1.review != null) return false;
        if (reviewLang != null ? !reviewLang.equals(review1.reviewLang) : review1.reviewLang != null) return false;
        if (reviewDate != null ? !reviewDate.equals(review1.reviewDate) : review1.reviewDate != null) return false;
        return reviewScores != null ? reviewScores.equals(review1.reviewScores) : review1.reviewScores == null;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userNickname != null ? userNickname.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (reviewLang != null ? reviewLang.hashCode() : 0);
        result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
        result = 31 * result + (reviewScores != null ? reviewScores.hashCode() : 0);
        result = 31 * result + thumbsUp;
        result = 31 * result + thumbsDown;
        return result;
    }
}
