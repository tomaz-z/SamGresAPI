package si.samgres.api.models.DARS.storms;

import si.samgres.api.models.DARS.events.properties.Properties;
import si.samgres.api.models.DARS.storms.crs.CRS;
import si.samgres.api.models.DARS.storms.features.Feature;

public class FeatureCollection {
    private CRS crs;
    private Feature[] features;
    private Properties properties;

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
}
