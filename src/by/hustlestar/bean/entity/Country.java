package by.hustlestar.bean.entity;

import java.io.Serializable;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Country implements Serializable {
    private String nameRu;
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
