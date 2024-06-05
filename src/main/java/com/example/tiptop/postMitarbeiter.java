package com.example.tiptop;

/* 
  Class purpose: 
  This class is for the POST function. It saves the data from the input.
  Reason for this class:
  No id input necessary.
*/
public class postMitarbeiter {
    // Attribute
    private String name;
    private String abteilung;
    private int gehalt;
    
    public postMitarbeiter() {
    }
    
    public postMitarbeiter(String name, String abteilung, int gehalt) {
        this.name = name;
        this.abteilung = abteilung;
        this.gehalt = gehalt;
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
    
