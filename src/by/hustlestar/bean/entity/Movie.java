package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 01.11.2016.
 */
public class Movie {
    private String title;
    private int year;
    private long budget;
    private long gross;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getGross() {
        return gross;
    }

    public void setGross(long gross) {
        this.gross = gross;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getYear() != movie.getYear()) return false;
        if (getBudget() != movie.getBudget()) return false;
        if (getGross() != movie.getGross()) return false;
        return getTitle() != null ? getTitle().equals(movie.getTitle()) : movie.getTitle() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + getYear();
        result = 31 * result + (int) (getBudget() ^ (getBudget() >>> 32));
        result = 31 * result + (int) (getGross() ^ (getGross() >>> 32));
        return result;
    }
}
