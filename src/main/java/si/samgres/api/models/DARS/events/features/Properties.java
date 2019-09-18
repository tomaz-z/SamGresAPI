package si.samgres.api.models.DARS.events.features;

public class Properties {
    private String EntityId;
    private String cesta;
    private String dodatnoPojasnilo;
    private int id;
    private String kategorija;
    private String opis;
    private String updated;
    private String vzrok;
    private double x;
    private double y;
    private String type;

    public String getEntityId() {
        return EntityId;
    }

    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public String getCesta() {
        return cesta;
    }

    public void setCesta(String cesta) {
        this.cesta = cesta;
    }

    public String getDodatnoPojasnilo() {
        return dodatnoPojasnilo;
    }

    public void setDodatnoPojasnilo(String dodatnoPojasnilo) {
        this.dodatnoPojasnilo = dodatnoPojasnilo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getVzrok() {
        return vzrok;
    }

    public void setVzrok(String vzrok) {
        this.vzrok = vzrok;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
