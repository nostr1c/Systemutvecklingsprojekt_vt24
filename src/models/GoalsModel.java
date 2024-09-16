package models;

import java.util.Objects;
import java.util.HashMap;

/**
 * A model for the containing data regarding a Goals
 */
public class GoalsModel {
    
    private final HashMap<String, String> map;
    
    public GoalsModel(String hid, String name) {
        map = new HashMap<>();
        map.put("hid", hid);
        map.put("namn", name);
    }
    
    public GoalsModel(HashMap<String, String> map) {
        this.map = map;
    }
    
    public GoalsModel(String hid, String name, String number, String description, String priority){
        map = new HashMap<String, String>();
        map.put("hid", hid);
        map.put("namn", name);
        map.put("malnummer", number);
        map.put("beskrivning", description);
        map.put("prioritet", priority);
    }
    
    public String getHid() {
        return map.get("hid");
    }
    
    public String getName(){
        return map.get("namn");
    }
    
    public String getNumber(){
        return map.get("malnummer");
    }
    
    public String getDescription(){
        return map.get("beskrivning");
    }
    
    public String getPriority(){
        return map.get("prioritet");
    }
    
    @Override
    public String toString() {
        return map.get("malnummer") + " - " + map.get("namn");
    }
    
    // För att jämföra i exempelvis en combobox
    @Override
    public int hashCode() {
        return Objects.hash(getHid());
    }
    
    public HashMap<String, String> toHashMap(){
        return map;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GoalsModel that = (GoalsModel) obj;
        return Objects.equals(getHid(), that.getHid());
    }
}
