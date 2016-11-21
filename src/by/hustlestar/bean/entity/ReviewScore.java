package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 10.11.2016.
 */
public class ReviewScore {
    private int movieID;
    private String userNicknameWhoReviewed;
    private int score;
    private String userNicknameWheScoredReview;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getUserNicknameWhoReviewed() {
        return userNicknameWhoReviewed;
    }

    public void setUserNicknameWhoReviewed(String userNicknameWhoReviewed) {
        this.userNicknameWhoReviewed = userNicknameWhoReviewed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserNicknameWheScoredReview() {
        return userNicknameWheScoredReview;
    }

    public void setUserNicknameWheScoredReview(String userNicknameWheScoredReview) {
        this.userNicknameWheScoredReview = userNicknameWheScoredReview;
    }

}
