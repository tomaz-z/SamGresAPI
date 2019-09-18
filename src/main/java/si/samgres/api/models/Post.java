package si.samgres.api.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    int id;
    String description;
    String category;
    String cause;
    double x;
    double y;
    String region;
    Date date;

    public Post() {}

    public Post(int id, String description, String category, String cause, double x, double y, String region, Date date)
    {
        this.id = id;
        this.description = description;
        this.category = category;
        this.cause = cause;
        this.x = x;
        this.y = y;
        this.region = region;
        this.date = date;
    }

    public Post(String descriptio, String category, String cause, double x, double y, String region, Date date)
    {
        this.description = descriptio;
        this.category = category;
        this.cause = cause;
        this.x = x;
        this.y = y;
        this.region = region;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
