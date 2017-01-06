package by.hustlestar.bean.entity;

import java.util.List;

/**
 * Created by Hustler on 01.11.2016.
 */
public class Movie {
    private int id;
    private String titleRu;
    private String titleEn;
    private int year;
    private long budget;
    private long gross;
    private Actor director;

    private List<Country> countries;
    private List<Review> reviews;
    private List<Rating> ratings;
    private List<Genre> genres;
    private List<Actor> actors;

    private double avgRating;
    private int ratingVotes;

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getRatingVotes() {
        return ratingVotes;
    }

    public void setRatingVotes(int ratingVotes) {
        this.ratingVotes = ratingVotes;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
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

    public Actor getDirector() {
        return director;
    }

    public void setDirector(Actor director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (year != movie.year) return false;
        if (budget != movie.budget) return false;
        if (gross != movie.gross) return false;
        if (Double.compare(movie.avgRating, avgRating) != 0) return false;
        if (ratingVotes != movie.ratingVotes) return false;
        if (titleRu != null ? !titleRu.equals(movie.titleRu) : movie.titleRu != null) return false;
        if (titleEn != null ? !titleEn.equals(movie.titleEn) : movie.titleEn != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (countries != null ? !countries.equals(movie.countries) : movie.countries != null) return false;
        if (reviews != null ? !reviews.equals(movie.reviews) : movie.reviews != null) return false;
        if (ratings != null ? !ratings.equals(movie.ratings) : movie.ratings != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        return actors != null ? actors.equals(movie.actors) : movie.actors == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (titleRu != null ? titleRu.hashCode() : 0);
        result = 31 * result + (titleEn != null ? titleEn.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (int) (budget ^ (budget >>> 32));
        result = 31 * result + (int) (gross ^ (gross >>> 32));
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (countries != null ? countries.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        temp = Double.doubleToLongBits(avgRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + ratingVotes;
        return result;
    }
}
