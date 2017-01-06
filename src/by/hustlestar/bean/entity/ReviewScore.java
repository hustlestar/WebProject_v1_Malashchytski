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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewScore that = (ReviewScore) o;

        if (movieID != that.movieID) return false;
        if (score != that.score) return false;
        if (userNicknameWhoReviewed != null ? !userNicknameWhoReviewed.equals(that.userNicknameWhoReviewed) : that.userNicknameWhoReviewed != null)
            return false;
        return userNicknameWheScoredReview != null ? userNicknameWheScoredReview.equals(that.userNicknameWheScoredReview) : that.userNicknameWheScoredReview == null;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userNicknameWhoReviewed != null ? userNicknameWhoReviewed.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (userNicknameWheScoredReview != null ? userNicknameWheScoredReview.hashCode() : 0);
        return result;
    }
}
