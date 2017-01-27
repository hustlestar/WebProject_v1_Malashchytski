package by.hustlestar.bean.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Entity represents news.
 */
public class News implements Serializable {
    /**
     * unique identifier of news
     */
    private int id;
    /**
     * news title in russian
     */
    private String titleRu;
    /**
     * news title in english
     */
    private String titleEn;
    /**
     * news text in russian
     */
    private String textRu;
    /**
     * news text in english
     */
    private String textEn;
    /**
     * date of news
     */
    private Timestamp newsDate;
    /**
     * path to image for news
     */
    private String image;

    /**
     * list of connected movies
     */
    private List<Movie> newsMovies;
    /**
     * list of connected actors
     */
    private List<Actor> newsActors;

    public News() {
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

    public String getTextRu() {
        return textRu;
    }

    public void setTextRu(String textRu) {
        this.textRu = textRu;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public Timestamp getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Timestamp newsDate) {
        this.newsDate = newsDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Movie> getNewsMovies() {
        return newsMovies;
    }

    public void setNewsMovies(List<Movie> newsMovies) {
        this.newsMovies = newsMovies;
    }

    public List<Actor> getNewsActors() {
        return newsActors;
    }

    public void setNewsActors(List<Actor> newsActors) {
        this.newsActors = newsActors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (titleRu != null ? !titleRu.equals(news.titleRu) : news.titleRu != null) return false;
        if (titleEn != null ? !titleEn.equals(news.titleEn) : news.titleEn != null) return false;
        if (textRu != null ? !textRu.equals(news.textRu) : news.textRu != null) return false;
        if (textEn != null ? !textEn.equals(news.textEn) : news.textEn != null) return false;
        if (newsDate != null ? !newsDate.equals(news.newsDate) : news.newsDate != null) return false;
        if (image != null ? !image.equals(news.image) : news.image != null) return false;
        if (newsMovies != null ? !newsMovies.equals(news.newsMovies) : news.newsMovies != null) return false;
        return newsActors != null ? newsActors.equals(news.newsActors) : news.newsActors == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titleRu != null ? titleRu.hashCode() : 0);
        result = 31 * result + (titleEn != null ? titleEn.hashCode() : 0);
        result = 31 * result + (textRu != null ? textRu.hashCode() : 0);
        result = 31 * result + (textEn != null ? textEn.hashCode() : 0);
        result = 31 * result + (newsDate != null ? newsDate.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (newsMovies != null ? newsMovies.hashCode() : 0);
        result = 31 * result + (newsActors != null ? newsActors.hashCode() : 0);
        return result;
    }
}
