package by.hustlestar.bean.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Entity represents review.
 */
public class Review {
    /**
     * id of movie
     */
    private int movieID;
    /**
     * nickname of reviewer
     */
    private String userNickname;
    /**
     * review text
     */
    private String review;
    /**
     * language of review
     */
    private String reviewLang;
    /**
     * path to reviewer avatar
     */
    private String image;
    /**
     * date of review posted
     */
    private Timestamp reviewDate;
    /**
     * list of likes/dislikes for review
     */
    private List<ReviewScore> reviewScores;
    /**
     * number of likes
     */
    private int thumbsUp;
    /**
     * number of dislikes
     */
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        if (image != null ? !image.equals(review1.image) : review1.image != null) return false;
        if (reviewDate != null ? !reviewDate.equals(review1.reviewDate) : review1.reviewDate != null) return false;
        return reviewScores != null ? reviewScores.equals(review1.reviewScores) : review1.reviewScores == null;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userNickname != null ? userNickname.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (reviewLang != null ? reviewLang.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
        result = 31 * result + (reviewScores != null ? reviewScores.hashCode() : 0);
        result = 31 * result + thumbsUp;
        result = 31 * result + thumbsDown;
        return result;
    }
}
