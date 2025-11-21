package Main;

import config.config;
import java.sql.*;

public class authentication {
    
    config con = new config();
    
    public void login(){
        
        System.out.println("\n-------------");
        System.out.println("LOGIN");
        System.out.println("-------------");
        
        System.out.print("Enter Email: ");
        String email = Main.inp.nextLine();

        System.out.print("Enter Password: ");
        String pass = Main.inp.nextLine();

        String role = con.login(email, pass);

        if (role != null) {
            Main.currentUserID = con.lastLoginUserId;
            if (role.equalsIgnoreCase("Admin")) {
                Main.adminDashborad();
            } else {
                Main.customerDashboard();
            }
        } else {
            Main.main(null);
            return;
        }
        
    }
    
    public void register(){
        
        System.out.println("\n-------------");
        System.out.println("REGISTER");
        System.out.println("-------------");
        
        System.out.print("Enter Name: ");
        String name = Main.inp.nextLine();
        
        System.out.print("Enter Email: ");
        String email = Main.inp.nextLine();
        
         while(true){
            String qry = "SELECT * FROM tbl_user WHERE u_email = ?";
            java.util.List<java.util.Map<String, Object>> result = con.fetchRecords(qry, email);

            if (result.isEmpty()) {
                break;
            } else {
                System.out.print("Email already exists, Enter other Email: ");
                email = Main.inp.nextLine();
            }
        }
        
        System.out.print("Enter Phone Number: ");
        String contact = Main.inp.nextLine();
        
        System.out.print("Enter Password: ");
        String pass = Main.inp.nextLine();
        
        System.out.println("Choose Role:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Enter choice: ");
        int crole = -1;
        try {
            crole = Integer.parseInt(Main.inp.nextLine());
        } catch (Exception e) {
            crole = 2;
        }
        
        String role = "";
        if(crole == 1){
            role = "Admin";
        }else{
            role = "Customer";
        }
        
        String hash = con.hashPassword(pass);
        
        String sql = "INSERT INTO tbl_user(u_name, u_email, u_password, u_contact, u_role, u_status) VALUES(?, ?, ?, ?, ?, ?)";
        if(crole == 1){
            con.addRecord(sql, name, email, hash, contact, role, "Pending");
        }else{
            con.addRecord(sql, name, email, hash, contact, role, "Approved");
        }
        
        Main.main(null); return;
    }
}
