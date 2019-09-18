package si.samgres.api.models.authentication;

import si.samgres.api.models.User;

import java.util.Date;

public class AuthenticatedUser {
    private User user;
    private Date date;

    public AuthenticatedUser() {}

    public AuthenticatedUser(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
