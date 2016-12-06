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
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getYear() != movie.getYear()) return false;
        if (getBudget() != movie.getBudget()) return false;
        if (getGross() != movie.getGross()) return false;
        return getTitleRu() != null ? getTitleRu().equals(movie.getTitleRu()) : movie.getTitleRu() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitleRu() != null ? getTitleRu().hashCode() : 0;
        result = 31 * result + getYear();
        result = 31 * result + (int) (getBudget() ^ (getBudget() >>> 32));
        result = 31 * result + (int) (getGross() ^ (getGross() >>> 32));
        return result;
    }
}
