package adminDashboard;

import config.config;
import Main.Main;
import java.sql.*;
import java.util.*;

public class adminDashboard {
    
    config con = new config();

    public void manageUsers() {
        String res;
        do {
            System.out.println("\n-----------------------");
            System.out.println("      MANAGE USERS     ");
            System.out.println("-----------------------");
            System.out.println("1. Approve Users");
            System.out.println("2. Add User");
            System.out.println("3. View Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Back to Admin Dashboard");

            System.out.print("\nChoose an option: ");
            String option = Main.inp.nextLine();

            switch(option) {
                case "1": approveUsers(); break;
                case "2": addUser(); break;
                case "3": viewAllUser(); break;
                case "4": updateUser(); break;
                case "5": deleteUser(); break;
                case "6": Main.adminDashborad(); return;
                default:
                    System.out.println("Invalid input!");
                    return;
            }

            System.out.print("\nContinue Manage Users? (yes/no): ");
            res = Main.inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
    }
  
    public void approveUsers() {
        System.out.println("\n--- Pending Users ---");
        String sql = "SELECT * FROM tbl_user WHERE u_status = 'Pending'";
        String[] headers = {"ID", "Name", "Email", "Contact", "Role", "Status"};
        String[] cols = {"u_id","u_name","u_email","u_contact","u_role","u_status"};
        con.viewRecords(sql, headers, cols);

        System.out.print("Enter user ID to Approve (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;
        String update = "UPDATE tbl_user SET u_status = 'Approved' WHERE u_id = ?";
        con.updateRecord(update, id);
    }
    
    public void addUser() {
        System.out.println("\n--- Add User ---");
        System.out.print("Name: ");
        String name = Main.inp.nextLine();
        System.out.print("Email: ");
        String email = Main.inp.nextLine();
        System.out.print("Phone: ");
        String phone = Main.inp.nextLine();
        System.out.print("Password: ");
        String pass = Main.inp.nextLine();
        System.out.print("Role (Admin/Customer): ");
        String role = Main.inp.nextLine();
        if (!role.equalsIgnoreCase("Admin")) role = "Customer";
        String hash = con.hashPassword(pass);
        String status = role.equalsIgnoreCase("Admin") ? "Pending" : "Approved";
        String sql = "INSERT INTO tbl_user(u_name, u_email, u_password, u_contact, u_role, u_status) VALUES (?, ?, ?, ?, ?, ?)";
        con.addRecord(sql, name, email, hash, phone, role, status);
    }
    
    public void viewAllUser() {
        String cateringQuery = "SELECT * FROM tbl_user";
        String[] cateringHeaders = {"ID", "Name", "Email", "Contact", "Role", "Status"};
        String[] cateringColumns = {"u_id", "u_name", "u_email", "u_contact", "u_role", "u_status"};
        con.viewRecords(cateringQuery, cateringHeaders, cateringColumns);
    }
    
    public void updateUser() {
        viewAllUser();
        System.out.print("\nEnter User ID to update (or blank to cancel): ");
        String uid = Main.inp.nextLine();
        if (uid == null || uid.trim().isEmpty()) return;

        System.out.println("1. Update Name");
        System.out.println("2. Update Email");
        System.out.println("3. Update Phone");
        System.out.println("4. Update Role");
        System.out.println("5. Update Status");
        System.out.print("Choose field to update: ");
        String field = Main.inp.nextLine();

        String sql = null;
        String newVal;
        switch(field) {
            case "1":
                System.out.print("Enter new name: ");
                newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_user SET u_name = ? WHERE u_id = ?";
                con.updateRecord(sql, newVal, uid);
                break;
            case "2":
                System.out.print("Enter new email: ");
                newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_user SET u_email = ? WHERE u_id = ?";
                con.updateRecord(sql, newVal, uid);
                break;
            case "3":
                System.out.print("Enter new phone: ");
                newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_user SET u_contact = ? WHERE u_id = ?";
                con.updateRecord(sql, newVal, uid);
                break;
            case "4":
                System.out.print("Enter new role (Admin/Customer): ");
                newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_user SET u_role = ? WHERE u_id = ?";
                con.updateRecord(sql, newVal, uid);
                break;
            case "5":
                System.out.print("Enter new status (Approved/Pending): ");
                newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_user SET u_status = ? WHERE u_id = ?";
                con.updateRecord(sql, newVal, uid);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
    }
    
    public void deleteUser() {
        viewAllUser();
        System.out.print("\nEnter User ID to delete (or blank to cancel): ");
        String uid = Main.inp.nextLine();
        if (uid == null || uid.trim().isEmpty()) return;
        System.out.print("Are you sure to delete user " + uid + " ? (y/n): ");
        String c = Main.inp.nextLine();
        if (c.equalsIgnoreCase("y")) {
            String sql = "DELETE FROM tbl_user WHERE u_id = ?";
            con.deleteRecord(sql, uid);
        } else {
            System.out.println("Canceled.");
        }
    }

    public void manageEvents() {
        String res;
        do {
            System.out.println("\n-----------------------");
            System.out.println("      MANAGE EVENTS    ");
            System.out.println("-----------------------");
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Back to Admin Dashboard");

            System.out.print("\nChoose an option: ");
            String option = Main.inp.nextLine();

            switch(option) {
                case "1": addEvent(); break;
                case "2": viewEvent(); break;
                case "3": updateEvent(); break;
                case "4": deleteEvent(); break;
                case "5": Main.adminDashborad(); return;
                default:
                    System.out.println("Invalid input!");
                    return;
            }

            System.out.print("\nContinue Manage Events? (yes/no): ");
            res = Main.inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
    }

    public void addEvent(){
        System.out.println("\n--- Add Event ---");
        System.out.print("Event Name: "); String name = Main.inp.nextLine();
        System.out.print("Event Type: "); String type = Main.inp.nextLine();
        System.out.print("Event Date (YYYY-MM-DD): "); String date = Main.inp.nextLine();
        System.out.print("Location: "); String loc = Main.inp.nextLine();
        System.out.print("Guest count: "); int guest = 0;
        try { guest = Integer.parseInt(Main.inp.nextLine()); } catch(Exception e) { guest = 0; }
        String sql = "INSERT INTO tbl_event(e_name, e_type, e_date, e_location, e_guest) VALUES (?, ?, ?, ?, ?)";
        con.addRecord(sql, name, type, date, loc, guest);
    }

    public void viewEvent(){
        String eventQuery = "SELECT * FROM tbl_event";
        String[] eventHeaders = {"EID", "Name", "Type", "Date", "Location", "guest"};
        String[] eventColumns = {"e_id", "e_name", "e_type", "e_date", "e_location", "e_guest"};
        con.viewRecords(eventQuery, eventHeaders, eventColumns);
    }

    public void updateEvent(){
        viewEvent();
        System.out.print("\nEnter Event ID to update (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;

        System.out.println("1. Update Name");
        System.out.println("2. Update Type");
        System.out.println("3. Update Date");
        System.out.println("4. Update Location");
        System.out.println("5. Update Guest Count");
        System.out.print("Choose field to update: ");
        String field = Main.inp.nextLine();
        String sql = null; String newVal = null;

        switch(field) {
            case "1":
                System.out.print("New name: "); newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_event SET e_name = ? WHERE e_id = ?"; con.updateRecord(sql, newVal, id); break;
            case "2":
                System.out.print("New type: "); newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_event SET e_type = ? WHERE e_id = ?"; con.updateRecord(sql, newVal, id); break;
            case "3":
                System.out.print("New date (YYYY-MM-DD): "); newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_event SET e_date = ? WHERE e_id = ?"; con.updateRecord(sql, newVal, id); break;
            case "4":
                System.out.print("New location: "); newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_event SET e_location = ? WHERE e_id = ?"; con.updateRecord(sql, newVal, id); break;
            case "5":
                System.out.print("New guest count: "); newVal = Main.inp.nextLine();
                sql = "UPDATE tbl_event SET e_guest = ? WHERE e_id = ?"; con.updateRecord(sql, Integer.parseInt(newVal), id); break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void deleteEvent(){
        viewEvent();
        System.out.print("\nEnter Event ID to delete (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;
        System.out.print("Are you sure to delete event " + id + " ? (y/n): ");
        String c = Main.inp.nextLine();
        if (c.equalsIgnoreCase("y")) {
            String sql = "DELETE FROM tbl_event WHERE e_id = ?";
            con.deleteRecord(sql, id);
        } else {
            System.out.println("Canceled.");
        }
    }

    public void manageMenu() {
        String res;
        do {
            System.out.println("\n-----------------------");
            System.out.println("       MANAGE MENU     ");
            System.out.println("-----------------------");
            System.out.println("1. Add Menu");
            System.out.println("2. View Menu");
            System.out.println("3. Update Menu");
            System.out.println("4. Delete Menu");
            System.out.println("5. Back to Admin Dashboard");

            System.out.print("\nChoose an option: ");
            String option = Main.inp.nextLine();

            switch(option) {
                case "1": addMenu(); break;
                case "2": viewMenu(); break;
                case "3": updateMenu(); break;
                case "4": deleteMenu(); break;
                case "5": Main.adminDashborad(); return;
                default:
                    System.out.println("Invalid input!"); return;
            }

            System.out.print("\nContinue Manage Menu? (yes/no): ");
            res = Main.inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
    }

    public void addMenu(){
        System.out.println("\n--- Add Menu ---");
        System.out.print("Menu name: "); String name = Main.inp.nextLine();
        System.out.print("Description: "); String desc = Main.inp.nextLine();
        System.out.print("Price: "); float price = 0;
        try { price = Float.parseFloat(Main.inp.nextLine()); } catch(Exception e) { price = 0; }
        String sql = "INSERT INTO tbl_menu(m_name, m_desc, m_price) VALUES (?, ?, ?)";
        con.addRecord(sql, name, desc, price);
    }

    public void viewMenu(){
        String menuQuery = "SELECT * FROM tbl_menu";
        String[] menuHeaders = {"MID", "Name", "Description", "Price"};
        String[] menuColumns = {"m_id", "m_name", "m_desc", "m_price"};
        con.viewRecords(menuQuery, menuHeaders, menuColumns);
    }

    public void updateMenu(){
        viewMenu();
        System.out.print("\nEnter Menu ID to update (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;

        System.out.println("1. Update Name");
        System.out.println("2. Update Description");
        System.out.println("3. Update Price");
        System.out.print("Choose field to update: ");
        String field = Main.inp.nextLine();
        switch(field) {
            case "1":
                System.out.print("New name: "); String n = Main.inp.nextLine();
                con.updateRecord("UPDATE tbl_menu SET m_name = ? WHERE m_id = ?", n, id); break;
            case "2":
                System.out.print("New description: "); String d = Main.inp.nextLine();
                con.updateRecord("UPDATE tbl_menu SET m_desc = ? WHERE m_id = ?", d, id); break;
            case "3":
                System.out.print("New price: "); String p = Main.inp.nextLine();
                try { float pf = Float.parseFloat(p); con.updateRecord("UPDATE tbl_menu SET m_price = ? WHERE m_id = ?", pf, id); }
                catch(Exception e){ System.out.println("Invalid price."); }
                break;
            default:
                System.out.println("Invalid option."); return;
        }
    }

    public void deleteMenu(){
        viewMenu();
        System.out.print("\nEnter Menu ID to delete (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;
        System.out.print("Are you sure to delete menu " + id + " ? (y/n): ");
        String c = Main.inp.nextLine();
        if (c.equalsIgnoreCase("y")) {
            String sql = "DELETE FROM tbl_menu WHERE m_id = ?";
            con.deleteRecord(sql, id);
        } else {
            System.out.println("Canceled.");
        }
    }

    public void manageBookings() {
        String res;
        do {
            System.out.println("\n-----------------------");
            System.out.println("     MANAGE BOOKINGS   ");
            System.out.println("-----------------------");
            System.out.println("1. View All Bookings");
            System.out.println("2. Approve Booking");
            System.out.println("3. Back to Admin Dashboard");

            System.out.print("\nChoose an option: ");
            String option = Main.inp.nextLine();

            switch(option) {
                case "1": viewAllBoking(); break;
                case "2": approveBooking(); break;
                case "3": Main.adminDashborad(); return;
                default:
                    System.out.println("Invalid input!"); return;
            }

            System.out.print("\nContinue Manage Bookings? (yes/no): ");
            res = Main.inp.nextLine();
        } while(res.equalsIgnoreCase("yes") || res.equals("1"));
    }

    public void viewAllBoking(){
        String sql = "SELECT b.b_id, u.u_name, e.e_name, b.b_date, b.b_status, b.total_amount " +
                     "FROM tbl_booking b " +
                     "LEFT JOIN tbl_user u ON b.u_id = u.u_id " +
                     "LEFT JOIN tbl_event e ON b.e_id = e.e_id";
    
        String[] headers = {"ID", "Customer", "Event", "Date", "Status", "Total"};
        String[] columns = {"b_id", "u_name", "e_name", "b_date", "b_status", "total_amount"};
    
        con.viewRecords(sql, headers, columns);
    }

    public void approveBooking() {
        viewAllBoking();
        System.out.print("\nEnter Booking ID to approve (or blank to cancel): ");
        String id = Main.inp.nextLine();
        if (id == null || id.trim().isEmpty()) return;
        System.out.print("Approve booking " + id + " ? (y/n): ");
        String c = Main.inp.nextLine();
        if (c.equalsIgnoreCase("y") || c.equalsIgnoreCase("1")) {
            String sql = "UPDATE tbl_booking SET b_status = 'Approved' WHERE b_id = ?";
            con.updateRecord(sql, id);
        } else {
            System.out.println("Canceled.");
        }
    }
}
