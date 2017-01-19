package by.hustlestar.bean.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Entity represents actor.
 */
public class Actor implements Serializable {
    /**
     * unique identifier
     */
    private int id;
    /**
     * actor name in russian
     */
    private String nameRu;
    /**
     * actor name in english
     */
    private String nameEn;
    /**
     * list of movies where actor participated
     */
    private List<Movie> movies;
    /**
     * path to actor photo
     */
    private String image;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (id != actor.id) return false;
        if (nameRu != null ? !nameRu.equals(actor.nameRu) : actor.nameRu != null) return false;
        if (nameEn != null ? !nameEn.equals(actor.nameEn) : actor.nameEn != null) return false;
        if (movies != null ? !movies.equals(actor.movies) : actor.movies != null) return false;
        return image != null ? image.equals(actor.image) : actor.image == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameRu != null ? nameRu.hashCode() : 0);
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (movies != null ? movies.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
