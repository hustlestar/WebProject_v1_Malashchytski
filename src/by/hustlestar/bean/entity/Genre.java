package by.hustlestar.bean.entity;

import java.io.Serializable;

/**
 * Entity represents genre.
 */
public class Genre implements Serializable {
    /**
     * genre name in russian
     */
    private String nameRu;
    /**
     * genre name in english
     */
    private String nameEn;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (nameRu != null ? !nameRu.equals(genre.nameRu) : genre.nameRu != null) return false;
        return nameEn != null ? nameEn.equals(genre.nameEn) : genre.nameEn == null;
    }

    @Override
    public int hashCode() {
        int result = nameRu != null ? nameRu.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        return result;
    }
}
