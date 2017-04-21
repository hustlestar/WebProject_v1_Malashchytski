package by.hustlestar.bean.entity;

import java.io.Serializable;

/**
 * Entity represents country.
 */
public class Country implements Serializable {
    /**
     * country name in russian
     */
    private String nameRu;
    /**
     * country name in english
     */
    private String nameEn;

    public Country() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (nameRu != null ? !nameRu.equals(country.nameRu) : country.nameRu != null) return false;
        return nameEn != null ? nameEn.equals(country.nameEn) : country.nameEn == null;
    }

    @Override
    public int hashCode() {
        int result = nameRu != null ? nameRu.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        return result;
    }
}
