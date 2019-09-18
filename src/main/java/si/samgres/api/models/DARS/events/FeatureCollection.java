package si.samgres.api.models.DARS.events;

import si.samgres.api.models.DARS.events.crs.CRS;
import si.samgres.api.models.DARS.events.features.Feature;
import si.samgres.api.models.DARS.events.properties.Properties;

public class FeatureCollection {
    private CRS crs;
    private Feature[] features;
    private Properties properties;
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

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
