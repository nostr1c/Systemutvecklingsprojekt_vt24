package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a Partner
 */
public class PartnerModel {
    
    private HashMap<String, String> map;
    
    public PartnerModel(String pid, String name) {
        map = new HashMap<>();
        map.put("pid", pid);
        map.put("namn", name);
    }
    
    public PartnerModel(String pid, String name, String contactName, String ContactEmail, String phone, String address, String branch, String city) {
        map = new HashMap<>();
        map.put("pid", pid);
        map.put("namn", name);
        map.put("kontaktperson", contactName);
        map.put("kontaktepost", ContactEmail);
        map.put("telefon", phone);
        map.put("adress", address);
        map.put("branch", branch);
        map.put("stad", city);
    }
    
    public PartnerModel(HashMap<String, String> map) {
        this.map = map;
    }
    
    public HashMap<String, String> toHashMap() {
        return map;
    }
    
    public String getPid() {
        return map.get("pid");
    }
    
    public String getName() {
        return map.get("namn");
    }
    
    public String getContactName() {
        return map.get("kontaktperson");
    }
    
    public String getContactEmail() {
        return map.get("kontaktepost");
    }
    
    public String getPhone() {
        return map.get("telefon");
    }
    
    public String getAddress() {
        return map.get("adress");
    }
    
    public String getBranch() {
        return map.get("branch");
    }
    
    public String getCity() {
        return map.get("stad");
    }
    
    @Override
    public String toString() {
        return map.get("namn");
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
        PartnerModel that = (PartnerModel) obj;
        return Objects.equals(getPid(), that.getPid());
    }
}
