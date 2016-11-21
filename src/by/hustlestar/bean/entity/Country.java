package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Country {
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
    public String toString() {
        return nameRu;
    }
}
