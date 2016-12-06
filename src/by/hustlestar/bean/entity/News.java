package by.hustlestar.bean.entity;

import java.sql.Timestamp;

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

    private int newsActorID;
    private int newsMovieID;

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

    public int getNewsActorID() {
        return newsActorID;
    }

    public void setNewsActorID(int newsActorID) {
        this.newsActorID = newsActorID;
    }

    public int getNewsMovieID() {
        return newsMovieID;
    }

    public void setNewsMovieID(int newsMovieID) {
        this.newsMovieID = newsMovieID;
    }
}
