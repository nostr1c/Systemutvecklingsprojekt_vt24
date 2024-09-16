package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a City
 */
public class CityModel {
    
    private HashMap<String, String> map;
    
    public CityModel(HashMap<String, String> map) {
        this.map = new HashMap<>();
        this.map = map;
    }
    
    public HashMap<String, String> toHashMap() {
        return map;
    }
    
    public String getSid() {
        return map.get("sid");
    }

    @Override
    public String toString() {
        return map.get("namn");
    }
    
    // För att jämföra i exempelvis en combobox
    @Override
    public int hashCode() {
        return Objects.hash(getSid());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CityModel that = (CityModel) obj;
        return Objects.equals(getSid(), that.getSid());
    }
}
