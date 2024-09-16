package mysql;

import java.util.ArrayList;
import java.util.HashMap;
import models.*;
import oru.inf.*;

/**
 * A class containing multiple reused SQL queries
 */
public class Queries {
    
    private static final InfDB db = MySQL.getInstance().getDB();
    
    private Queries() {}
    
    public static void doMultiple(String ...queries) throws InfException {
        for (String query : queries) {
            if (query.contains("DELETE")) {
                db.delete(query);
            } else if (query.contains("UPDATE"))  {
                db.update(query);
            } else if (query.contains("INSERT")) {
                db.insert(query);
            }
        }
    }
    
    // --- Anställda start ---
    
    public static String getRoleByAid(String aid) {
        String query = """
                       SELECT
                          CASE
                              WHEN admin.aid IS NOT NULL THEN 'Admin'
                              WHEN handlaggare.aid IS NOT NULL THEN 'Handläggare'
                              ELSE 'employee'
                          END AS Role
                       FROM anstalld
                       LEFT JOIN admin ON anstalld.aid = admin.aid
                       LEFT JOIN handlaggare ON anstalld.aid = handlaggare.aid
                       WHERE anstalld.aid = %s;
                       """.formatted(aid);
        
        try {
            return db.fetchSingle(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static boolean employeeExists(String aid) {
        
        String query = "SELECT aid FROM anstalld WHERE aid = %s".formatted(aid);
        
        try {
            return db.fetchSingle(query) != null;
        } catch (InfException ignored) {
            return false;
        }
    }
    
    public static HashMap<String, String> getLoginInformation(String email) {
        
        String query = "SELECT aid, losenord from anstalld where epost = '%s'".formatted(email);
        
        try {
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String, String> getEmployeeByAid(String aid) {
        
        String query = "SELECT * from anstalld where aid = %s".formatted(aid);
        
        try {
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static String getMentorByAid(String aid) {
        String query = "SELECT mentor FROM handlaggare WHERE aid = %s".formatted(aid);
        
        try {
            return db.fetchSingle(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String, String> getAllEmployeeInformationByAid(String aid) {
        String query = """
                       SELECT anstalld.*,  ansvarighetsomrade, mentor, behorighetsniva
                       FROM anstalld
                       LEFT JOIN handlaggare ON anstalld.aid = handlaggare.aid
                       LEFT JOIN admin ON anstalld.aid = admin.aid
                       WHERE anstalld.aid = %s;
                       """.formatted(aid);
  
        try {
            return db.fetchRow(query);
            
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getManagers() {
        String query = "SELECT * from anstalld join handlaggare on anstalld.aid = handlaggare.aid";
        
        try {
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addEmployee(EmployeeModel employee) throws InfException {
        String query = "INSERT INTO anstalld VALUES (%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s)".formatted(
            employee.getAid(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getAddress(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getDate(),
            employee.getPassword(),
            employee.getDepartment()
        );
        db.insert(query);
    }
    
    public static void addManager(EmployeeModel manager) throws InfException {
        String query = "";
        if (!manager.getMentor().isBlank()) {
            query = "INSERT INTO handlaggare VALUES (%s, '%s', %s)".formatted(manager.getAid(), manager.getResponsibility(), manager.getMentor());
        } else {
            query = "INSERT INTO handlaggare VALUES (%s, '%s', null)".formatted(manager.getAid(), manager.getResponsibility());
        }
        
        db.insert(query);
    }
    
    public static void addAdmin(EmployeeModel admin) throws InfException {
        String query = "INSERT INTO admin VALUES (%s, 1)".formatted(admin.getAid());
        db.insert(query);
    }
     
    // --- Hållbarhet start ---
    
    public static ArrayList<HashMap<String, String>> getGoals() {
        
        String query = "SELECT * FROM hallbarhetsmal";
        
        try {
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String, String> getGoalByHid(String hid) {
        
        String query = "SELECT * FROM hallbarhetsmal WHERE hid = %s".formatted(hid);
        
        try {
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addGoal(GoalsModel goal) throws InfException {
        String query = "INSERT INTO hallbarhetsmal VALUES (%s, '%s', %s, '%s', '%s')".formatted(
            goal.getHid(),
            goal.getName(),
            goal.getNumber(),
            goal.getDescription(),
            goal.getPriority()
        );
        db.insert(query);
    }
    
    // --- Avdelning start ---
    
    public static ArrayList<HashMap<String,String>> getDepartments() { 
        try {
            return db.fetchRows("SELECT * FROM avdelning");
            
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String,String> getDepartmentByAvdid(String avdid) { 
        String query = "SELECT * FROM avdelning WHERE avdid = %s".formatted(avdid);
        
        try {
            return db.fetchRow(query);
            
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addDepartment(DepartmentModel department) throws InfException { 
        String query = "INSERT INTO avdelning VALUES (%s, '%s', '%s', '%s', '%s', '%s', %s, %s)".formatted(
            department.getAvdid(),
            department.getName(),
            department.getDescription(),
            department.getAddress(),
            department.getEmail(),
            department.getPhone(),
            department.getCity(),
            department.getManager()
        );
        db.insert(query);   
    }
    
    // --- Projekt start ---
    
    public static ArrayList<HashMap<String,String>> getProjects() { 
        try {
            return db.fetchRows("SELECT * FROM projekt");
            
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String,String> getProjectByPid(String pid) { 
        String query = "SELECT * FROM projekt WHERE pid = %s".formatted(pid);
        
        try {
            return db.fetchRow(query);
            
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addProject(ProjectModel project) throws InfException { 
        String query = "INSERT INTO projekt VALUES (%s, '%s', '%s', '%s', '%s', %s, '%s', '%s', %s, %s)".formatted(
                project.getPid(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCost(),
                project.getStatus(),
                project.getPriority(),
                project.getManager(),
                project.getCountry());
        
        db.insert(query);   
    }
    
    public static boolean isProjectManager(String aid, String pid) {
        try {
            String query = """
                           SELECT projektchef FROM projekt
                           JOIN anstalld on projektchef = aid
                           where projektchef = aid
                           and pid = %s
                           and aid = %s
                           """.formatted(pid, aid);
            
            return db.fetchSingle(query) != null;
            
        } catch (InfException ignored) {
            return false;
        }
    }
    
    public static boolean isProjectManagerAny(String aid) {
        try {
            String query = """
                            SELECT projekt.projektchef FROM projekt
                            join anstalld on projekt.projektchef = anstalld.aid
                            WHERE anstalld.aid = %s;
                           """.formatted(aid);
            return db.fetchRows(query).size() > 0;
        } catch (InfException ignored) {
            return false;
        }
    }
    
    // --- Land start ---
    
    public static HashMap<String, String> getCountryByLid(String lid) {
        
        String query = "SELECT * from land where lid = %s".formatted(lid);
        
        try {
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getCountries() {
        try {
            return db.fetchRows("SELECT * FROM land");
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addCountry(CountryModel country) throws InfException {
        String query = "INSERT INTO land VALUES (%s, '%s', '%s', %s, '%s', '%s', '%s')".formatted(
            country.getLid(),
            country.getName(),
            country.getLanguage(),
            country.getCurrency(),
            country.getTimezone(),
            country.getPolitics(),
            country.getEconomy()
        );
        System.out.println(query);
        db.insert(query);
    }
    
    // --- Land end ---
    
    // --- Stats start ---
    
    public static String getAvgCostAllProjects() {
        try {
            return db.fetchSingle("SELECT ROUND(AVG(kostnad)) FROM projekt");
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static String getAvgCostAllProjectsMyDepartment(String aid) {
        try {
            String query = """
                           SELECT DISTINCT ROUND(AVG(kostnad)) FROM projekt
                           JOIN ans_proj ON projekt.pid = ans_proj.pid
                           JOIN anstalld ON ans_proj.aid = anstalld.aid
                           WHERE avdelning IN (SELECT avdelning FROM anstalld WHERE aid = %s)
                           """.formatted(aid);
            
            return db.fetchSingle(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static String getAvgCostAllProjectsManager(String aid) {
        try {
            String query = """
                           SELECT ROUND(AVG(kostnad)) FROM projekt
                           join anstalld on projekt.projektchef = anstalld.aid
                           where anstalld.aid = %s;
                           """.formatted(aid);
            
            return db.fetchSingle(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getAvgCostPerCountry() {
        try {
            String query = """
                           SELECT namn, ROUND(AVG(kostnad)) AS cost FROM projekt
                           join land on projekt.land = land.lid
                           GROUP BY land
                           """;
            
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getManagerCountries(String aid) {
        try {
            String query = """
                           SELECT namn from land
                           join projekt on land.lid = projekt.land
                           join anstalld on projekt.projektchef = anstalld.aid
                           WHERE anstalld.aid = 1;
                           """.formatted(aid);
            
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getManagerPartners(String aid) {
        try {
            String query = """
                           SELECT partner.namn AS namn from partner
                           join projekt_partner on partner.pid = projekt_partner.partner_pid
                           join projekt on projekt_partner.pid = projekt.pid
                           WHERE projektchef = %s;
                           """.formatted(aid);
            
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static ArrayList<HashMap<String, String>> getPartners() {
        try {
            String query = "SELECT * FROM partner";
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String, String> getPartnerByPid(String pid) {
        try {
            String query = "SELECT * FROM partner WHERE pid = %s".formatted(pid);
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static void addPartner(PartnerModel partner) {
        try {
            String query = "INSERT INTO partner VALUES (%s, '%s', '%s', '%s', '%s', '%s', '%s', %s)".formatted(
                partner.getPid(), 
                partner.getName(), 
                partner.getContactName(), 
                partner.getContactEmail(), 
                partner.getPhone(), 
                partner.getAddress(), 
                partner.getBranch(), 
                partner.getCity()
            );
            db.insert(query);
        } catch (InfException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<HashMap<String, String>> getCities() {
        try {
            String query = "SELECT * FROM stad";
            return db.fetchRows(query);
        } catch (InfException ignored) {
            return null;
        }
    }
    
    public static HashMap<String, String> getCityBySid(String sid) {
        try {
            String query = "SELECT * FROM stad WHERE sid = %s".formatted(sid);
            return db.fetchRow(query);
        } catch (InfException ignored) {
            return null;
        }
    }
}
