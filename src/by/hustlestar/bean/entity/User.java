package by.hustlestar.bean.entity;

/**
 * Created by Hustler on 31.10.2016.
 */
public class User {
    private String nickname;
    private String pass;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getNickname() != null ? !getNickname().equals(user.getNickname()) : user.getNickname() != null)
            return false;
        return getPass() != null ? getPass().equals(user.getPass()) : user.getPass() == null;

    }

    @Override
    public int hashCode() {
        int result = getNickname() != null ? getNickname().hashCode() : 0;
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }
}
