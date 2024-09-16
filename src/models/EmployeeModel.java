package models;

import java.util.HashMap;
import java.util.Objects;

/**
 * A model for the containing data regarding a Employee
 */
public class EmployeeModel {
    
    private final HashMap<String, String> map;
    
    public EmployeeModel(HashMap<String, String> map) {
        this.map = map;
    }
    
    public EmployeeModel(String aid, String firstName, String lastName, String address, String email, String phone, String date, String password, String department) {
        map = new HashMap<>();
        map.put("aid", aid);
        map.put("fornamn", firstName);
        map.put("efternamn", lastName);
        map.put("adress", address);
        map.put("epost", email);
        map.put("telefon", phone);
        map.put("anstallningsdatum", date);
        map.put("losenord", password);
        map.put("avdelning", department);
    }
    
    public EmployeeModel(String aid, String firstName, String lastName, String address, String email, String phone, String date, String password, String department, String responsibility, String mentor) {
        map = new HashMap<>();
        map.put("aid", aid);
        map.put("fornamn", firstName);
        map.put("efternamn", lastName);
        map.put("adress", address);
        map.put("epost", email);
        map.put("telefon", phone);
        map.put("anstallningsdatum", date);
        map.put("losenord", password);
        map.put("avdelning", department);
        map.put("ansvarighetsomrade", responsibility);
        map.put("mentor", mentor);
    }
    
    public EmployeeModel() {
        map = new HashMap<>();
        map.put("aid", "");
        map.put("fornamn", "Ingen");
        map.put("efternamn", "");
        map.put("mentor", "asd");
    }
    
    public String getAid() {
        return map.get("aid");
    }
    
    public String getFirstName() {
        return map.get("fornamn");
    }
    
    public String getLastName() {
        return map.get("efternamn");
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
    
    public String getDate() {
        return map.get("anstallningsdatum");
    }
    
    public String getPassword() {
        return map.get("losenord");
    }
    
    public String getDepartment() {
        return map.get("avdelning");
    }
    
    private String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    public String getResponsibility() {
        return map.get("ansvarighetsomrade");
    }

    public void setResponsibility(String rensponsibility) {
        map.put("ansvarighetsomrade", rensponsibility);
    }
    
    public String getMentor() {
        return map.get("mentor");
    }
    
    public void setMentor(String mentor) {
        map.put("mentor", mentor);
    }
    
    @Override
    public String toString() {
        return getFullName();
    }
    
    public HashMap<String, String> toHashMap() {
        return map;
    }
    
    // För att jämföra i exempelvis en combobox
    @Override
    public int hashCode() {
        return Objects.hash(getAid());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeModel that = (EmployeeModel) obj;
        return Objects.equals(getAid(), that.getAid());
    }
}
