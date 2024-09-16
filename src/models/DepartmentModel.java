package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a Department
 */
public class DepartmentModel {

    private final HashMap<String, String> map;
    
    public DepartmentModel(HashMap<String, String> map) {
        this.map = map;
    }
    
    public DepartmentModel(String avdid, String name, String description, String address, String email, String phone, String city, String manager) {
        map = new HashMap<>();
        
        map.put("avdid", avdid);
        map.put("namn", name);
        map.put("beskrivning", description);
        map.put("adress", address);
        map.put("epost", email);
        map.put("telefon", phone);
        map.put("stad", city);
        map.put("chef", manager);
    }

    public String getAvdid() {
        return map.get("avdid");
    }
    
    public String getName() {
        return map.get("namn");
    }
    
    public String getDescription() {
        return map.get("beskrivning");
    }
    
    public String getAddress() {
        return map.get("adress");
    }
    
    public String getEmail() {
        return map.get("epost");
    }
    
    public String getPhone() {
        return map.get("telefon");
    }
    
    public String getCity() {
        return map.get("stad");
    }
    
    public String getManager() {
        return map.get("chef");
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
        return Objects.hash(getAvdid());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DepartmentModel that = (DepartmentModel) obj;
        return Objects.equals(getAvdid(), that.getAvdid());
    }
}
