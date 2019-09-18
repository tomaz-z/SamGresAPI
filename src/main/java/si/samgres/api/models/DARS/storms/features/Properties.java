package si.samgres.api.models.DARS.storms.features;

public class Properties {
    private String burja_sunki;
    private String burja_veter;
    private int id;
    private String summary;
    private String title;
    private String updated;
    private String type;

    public String getBurja_sunki() {
        return burja_sunki;
    }

    public void setBurja_sunki(String burja_sunki) {
        this.burja_sunki = burja_sunki;
    }

    public String getBurja_veter() {
        return burja_veter;
    }

    public void setBurja_veter(String burja_veter) {
        this.burja_veter = burja_veter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
