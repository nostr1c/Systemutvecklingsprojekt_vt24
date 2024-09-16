package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a Country
 */
public class CountryModel {
    
    private HashMap<String, String> map;
    
    public CountryModel(String lid, String name, String language, String currency, String timezone, String politics, String economy) {
        map = new HashMap<>();
        map.put("lid", lid);
        map.put("namn", name);
        map.put("sprak", language);
        map.put("valuta", currency);
        map.put("tidszon", timezone);
        map.put("politisk_struktur", politics);
        map.put("ekonomi", economy);
    }
    
    public CountryModel(HashMap<String, String> map) {
        this.map = map;
    }
    
    public String getLid() {
        return map.get("lid");
    }
    
    public String getName() {
        return map.get("namn");
    }
    
    public String getLanguage() {
        return map.get("sprak");
    }
    
    public String getCurrency() {
        return map.get("valuta");
    }
    
    public String getTimezone() {
        return map.get("tidszon");
    }
    
    public String getPolitics() {
        return map.get("politisk_struktur");
    }
    
    public String getEconomy() {
        return map.get("ekonomi");
    }
    
    public HashMap<String, String> toHashMap() {
        return map;
    }

    @Override
    public String toString() {
        return map.get("namn");
    }
    
    // För att jämföra i exempelvis en combobox
    @Override
    public int hashCode() {
        return Objects.hash(getLid());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CountryModel that = (CountryModel) obj;
        return Objects.equals(getLid(), that.getLid());
    }
}
