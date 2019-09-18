package si.samgres.api.models.DARS.events.properties;

public class Properties {
    private String Language;
    private String LastUpdateTS;
    private String PeriodFrom;
    private String PeriodTo;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getLastUpdateTS() {
        return LastUpdateTS;
    }

    public void setLastUpdateTS(String lastUpdateTS) {
        LastUpdateTS = lastUpdateTS;
    }

    public String getPeriodFrom() {
        return PeriodFrom;
    }

    public void setPeriodFrom(String periodFrom) {
        PeriodFrom = periodFrom;
    }

    public String getPeriodTo() {
        return PeriodTo;
    }

    public void setPeriodTo(String periodTo) {
        PeriodTo = periodTo;
    }
}
