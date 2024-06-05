package com.example.tiptop.database;

import com.example.tiptop.newUser;
import com.example.tiptop.Mitarbeiter;
import com.example.tiptop.postMitarbeiter;
import com.example.tiptop.jdbc;
import com.example.tiptop.loginMitarbeiter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// The SQL Statements for the different CRUD functions
public class MitarbeiterDAO {
	
	public void insert(postMitarbeiter ma) {
		
	    String sql = "INSERT INTO mitarbeiter (name, department, salary) VALUES (?, ?, ?);";
	    
	    try (Connection con = jdbc.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	    	
	        pstmt.setString(1, ma.getName());
	        pstmt.setString(2, ma.getDepartment());
	        pstmt.setInt(3, ma.getSalary());
	        
	        pstmt.executeUpdate();
	        con.commit();
	        System.out.print("Insertion sucess");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print("Insertion failed");
	    }
	}
    
	public void insertNewUser(newUser newUser) {
		
	    String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?);";
	    
	    try (Connection con = jdbc.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	    	
	        pstmt.setString(1, newUser.getUsername());
	        pstmt.setString(2, newUser.getPassword());
	        pstmt.setString(3, newUser.getRole());
	        
	        pstmt.executeUpdate();
	        con.commit();
	        System.out.print("Insertion sucess");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print("Insertion failed");
	    }
	}

    public static String findById( int id) {
    	
        Connection con = jdbc.getConnection();
        Mitarbeiter ma = new Mitarbeiter();

        String query = "SELECT name, department, salary FROM mitarbeiter WHERE id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            if (id == 0) {
                return null;
            } else {
                pstmt.setInt(1, id);
            }
            ResultSet rs = pstmt.executeQuery();
            if( rs.next()) {
                ma.setName(rs.getString(1));
                ma.setDepartment(rs.getString(2));
                ma.setSalary(rs.getInt(3));
                ma.setID(id);

            }
            //con.commit();
            pstmt.close();
            //con.close();
            return convertToJsonNoList(ma);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String findWithSalary( int salary) {
    	
    	Connection con = jdbc.getConnection();
        List<Mitarbeiter> employees = new ArrayList<>();

        String query = "SELECT id, name, department, salary FROM mitarbeiter Where salary = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            if (salary == 0) {
                return null;
            } else {
                pstmt.setInt(1, salary);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Mitarbeiter ma = new Mitarbeiter();
                ma.setID(rs.getInt("id"));
                ma.setName(rs.getString("name"));
                ma.setDepartment(rs.getString("department"));
                ma.setSalary(salary);
                employees.add(ma);
            }

            pstmt.close();
            return convertToJson(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
	 
    public static String findAllAsJson() {
        Connection con = jdbc.getConnection();
        List<Mitarbeiter> employees = new ArrayList<>();

        String query = "SELECT id, name, department, salary FROM mitarbeiter";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Mitarbeiter ma = new Mitarbeiter();
                ma.setID(rs.getInt("id"));
                ma.setName(rs.getString("name"));
                ma.setDepartment(rs.getString("department"));
                ma.setSalary(rs.getInt("salary"));
                employees.add(ma);
            }

            pstmt.close();
            return convertToJson(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static String findWithName(String name) {
        Connection con = jdbc.getConnection();
        List<Mitarbeiter> employees = new ArrayList<>();

        String query = "SELECT id, name, department, salary FROM mitarbeiter Where name = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            if (name == null) {
                return null;
            } else {
                pstmt.setString(1, name);
            }
            
            ResultSet rs = pstmt.executeQuery();

            
            while (rs.next()) {
                Mitarbeiter ma = new Mitarbeiter();
                ma.setID(rs.getInt("id"));
                ma.setName(name);
                ma.setDepartment(rs.getString("department"));
                ma.setSalary(rs.getInt("salary"));
                employees.add(ma);
            }
            
            pstmt.close();
            return convertToJson(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static String findWithDepartment(String department) {
        Connection con = jdbc.getConnection();
        List<Mitarbeiter> employees = new ArrayList<>();

        String query = "SELECT id, name, department, salary FROM mitarbeiter Where department = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);

            if (department == null) {
                return null;
            } else {
                pstmt.setString(1, department);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Mitarbeiter ma = new Mitarbeiter();
                ma.setID(rs.getInt("id"));
                ma.setName(rs.getString("name"));
                ma.setDepartment(department);
                ma.setSalary(rs.getInt("salary"));
                employees.add(ma);
            }

            pstmt.close();
            return convertToJson(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String convertToJson(List<Mitarbeiter> employees) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(employees);
    }
    
    private static String convertToJsonNoList(Mitarbeiter employees) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(employees);
    }
    
    public ArrayList<loginMitarbeiter> findWithStatement( String whereStatement) {
        Connection con = jdbc.getConnection();
        ArrayList<loginMitarbeiter> maList = new ArrayList<loginMitarbeiter>();
        loginMitarbeiter ma = new loginMitarbeiter();

        String sql = "SELECT username, password, role FROM users";
        if(whereStatement != null) {
            sql += whereStatement;
        }
        try {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while( rs.next()) {
                ma = new loginMitarbeiter();
                ma.setUsername(rs.getString(1));
                ma.setPassword(rs.getString(2));
                ma.setRole(rs.getString(3));
                maList.add(ma);
            }
            stmt.close();
            //con.close();
            return maList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Mitarbeiter ma) {

        Connection con = jdbc.getConnection();


        String sql = "UPDATE mitarbeiter SET name = ?, department = ?, salary = ? WHERE id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
           
            // Adds the different values in the sql String
            pstmt.setString(1,ma.getName());
            pstmt.setString(2,ma.getDepartment());
            pstmt.setInt(3, ma.getSalary());
            pstmt.setInt(4, ma.getID());
            
            pstmt.executeUpdate();
            con.commit();
            
            pstmt.close();
            con.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete( int id ) {
            Connection con = jdbc.getConnection();

            String sql = "DELETE FROM mitarbeiter WHERE id = ?";

        try {
                PreparedStatement pstmt = con.prepareStatement(sql);
                if (id == 0) {
                    return false;
                } else {
                    pstmt.setInt(1, id);
                }
            pstmt.executeUpdate();
            con.commit();
            return true;
        }
        catch( Exception e ) {
            e.printStackTrace();
            System.out.print("Deletion failed");
        }
        return true;
    }
}
