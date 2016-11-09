package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 08.11.2016.
 */
public class Genre {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
