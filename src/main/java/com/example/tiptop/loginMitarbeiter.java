package com.example.tiptop;

// Initializes new Employees
public class loginMitarbeiter {
    // Attribute
    private String username;
    private String password;
    private String role;
    
    public loginMitarbeiter() {
    }
    
    public loginMitarbeiter(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role_new) {
        this.role = role_new;
        System.out.print(role + "\ntestplease\n");
    }
    
    public String getRole() {
        System.out.println(role + " getRoles");

        return role;
    }


}
