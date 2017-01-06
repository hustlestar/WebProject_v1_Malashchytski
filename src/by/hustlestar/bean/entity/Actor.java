package by.hustlestar.bean.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 05.12.2016.
 */
public class Actor implements Serializable {
    private int id;
    private String nameRu;
    private String nameEn;
    private List<Movie> movies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (id != actor.id) return false;
        if (nameRu != null ? !nameRu.equals(actor.nameRu) : actor.nameRu != null) return false;
        if (nameEn != null ? !nameEn.equals(actor.nameEn) : actor.nameEn != null) return false;
        return movies != null ? movies.equals(actor.movies) : actor.movies == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        return result;
    }
}
