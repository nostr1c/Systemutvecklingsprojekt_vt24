package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a Project
 */
public class ProjectModel {
    
    private final HashMap<String, String> map;

    public ProjectModel(HashMap<String, String> map) {
        this.map = map;
    }

    public ProjectModel(String pid, String name, String description, String startDate, String endDate, String cost, String status, String priority, String manager, String country) {
        map = new HashMap<>();
        
        map.put("pid", pid);
        map.put("projektnamn", name);
        map.put("beskrivning", description);
        map.put("startdatum", startDate);
        map.put("slutdatum", endDate);
        map.put("kostnad", cost);
        map.put("status", status);
        map.put("prioritet", priority);
        map.put("projektchef", manager);
        map.put("land", country);
    }
    
    public String getPid() {
        return map.get("pid");
    }

    public String getName() {
        return map.get("projektnamn");
    }

    public String getDescription() {
        return map.get("beskrivning");
    }

    public String getStartDate() {
        return map.get("startdatum");
    }

    public String getEndDate() {
        return map.get("slutdatum");
    }

    public String getCost() {
        return map.get("kostnad");
    }

    public String getStatus() {
        return map.get("status");
    }

    public String getPriority() {
        return map.get("prioritet");
    }

    public String getManager() {
        return map.get("projektchef");
    }

    public String getCountry() {
        return map.get("land");
    }

    public HashMap<String, String> toHashMap() {
        return map;
    }

    @Override
    public String toString() {
        return getName();
    } 
    
    // För att jämföra i exempelvis en combobox
    @Override
    public int hashCode() {
        return Objects.hash(getPid());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProjectModel that = (ProjectModel) obj;
        return Objects.equals(getPid(), that.getPid());
    }
}
