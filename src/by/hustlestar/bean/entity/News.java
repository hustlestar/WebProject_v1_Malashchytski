package by.hustlestar.bean.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public class News {
    private int id;
    private String titleRu;
    private String titleEn;
    private String textRu;
    private String textEn;
    private Timestamp newsDate;

    private List<Movie> newsMovies;
    private List<Actor> newsActors;

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
}
