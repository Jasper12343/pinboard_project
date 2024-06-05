package com.example.tiptop;

// Initializes new Employees
public class Mitarbeiter {
    // Attribute
    private int id;
    private String name;
    private String abteilung;
    private int gehalt;
    
    public Mitarbeiter() {
    }
    
    public Mitarbeiter(int id, String name, String abteilung, int gehalt) {
        this.id = id;
        this.name = name;
        this.abteilung = abteilung;
        this.gehalt = gehalt;
    }
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return abteilung;
    }

    public void setDepartment(String abteilung) {
        this.abteilung = abteilung;
    }

    public int getSalary() {
        return gehalt;
    }

    public void setSalary(int gehalt) {
        this.gehalt = gehalt;
    }
}
    
