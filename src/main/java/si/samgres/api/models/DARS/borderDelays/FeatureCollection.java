package si.samgres.api.models.DARS.borderDelays;

import si.samgres.api.models.DARS.borderDelays.crs.CRS;
import si.samgres.api.models.DARS.borderDelays.features.Feature;

public class FeatureCollection {
    private CRS crs;
    private Feature[] features;
    private String type;

    public CRS getCrs() {
        return crs;
    }

    public void setCrs(CRS crs) {
        this.crs = crs;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public void setFeatures(Feature[] features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
