package by.hustlestar.bean.entity;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class Movie {
    private int id;
    private String title;
    private int year;
    private long budget;
    private long gross;

    private List<Country> countries;
    private List<Review> reviews;
    private List<Rating> ratings;
    private int averageRating;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getYear() != movie.getYear()) return false;
        if (getBudget() != movie.getBudget()) return false;
        if (getGross() != movie.getGross()) return false;
        return getTitle() != null ? getTitle().equals(movie.getTitle()) : movie.getTitle() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getYear();
        result = 31 * result + (int) (getBudget() ^ (getBudget() >>> 32));
        result = 31 * result + (int) (getGross() ^ (getGross() >>> 32));
        return result;
    }
}
