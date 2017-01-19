package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 10.11.2016.
 */
public class ReviewScore {
    /**
     * id of movie
     */
    private int movieID;
    /**
     * nickname of a reviewer
     */
    private String userNicknameWhoReviewed;
    /**
     * +1 -1 value
     */
    private int score;
    /**
     * nickname of one, who liked
     */
    private String userNicknameWhoScoredReview;

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

    public String getUserNicknameWhoScoredReview() {
        return userNicknameWhoScoredReview;
    }

    public void setUserNicknameWhoScoredReview(String userNicknameWhoScoredReview) {
        this.userNicknameWhoScoredReview = userNicknameWhoScoredReview;
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
        return userNicknameWhoScoredReview != null ? userNicknameWhoScoredReview.equals(that.userNicknameWhoScoredReview) : that.userNicknameWhoScoredReview == null;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + (userNicknameWhoReviewed != null ? userNicknameWhoReviewed.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (userNicknameWhoScoredReview != null ? userNicknameWhoScoredReview.hashCode() : 0);
        return result;
    }
}
