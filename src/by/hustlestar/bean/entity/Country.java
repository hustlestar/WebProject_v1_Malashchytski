package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 07.11.2016.
 */
public class Country {
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
