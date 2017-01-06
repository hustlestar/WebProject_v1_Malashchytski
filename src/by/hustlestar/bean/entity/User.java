package by.hustlestar.bean.entity;

import java.sql.Date;
import java.util.List;

/**
 * Created by Hustler on 31.10.2016.
 */
public class User {
    private String nickname;
    private String email;
    private String type;
    private String sex;
    private Date registred;
    private List<Review> reviews;
    private List<Rating> ratings;
    private int reputation;

    public User() {
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegistred() {
        return registred;
    }

    public void setRegistred(Date registred) {
        this.registred = registred;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (reputation != user.reputation) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (type != null ? !type.equals(user.type) : user.type != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (registred != null ? !registred.equals(user.registred) : user.registred != null) return false;
        if (reviews != null ? !reviews.equals(user.reviews) : user.reviews != null) return false;
        return ratings != null ? ratings.equals(user.ratings) : user.ratings == null;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (registred != null ? registred.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        result = 31 * result + reputation;
        return result;
    }
}
