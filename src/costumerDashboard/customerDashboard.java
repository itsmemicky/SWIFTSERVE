package costumerDashboard;

import config.config;
import Main.Main;
import java.sql.*;

public class customerDashboard {
    
    config con = new config();
    
    public void viewMyBooking(){
        System.out.println("\n------------------");
        System.out.println("MY BOOKINGS");
        System.out.println("------------------");

        String sqlQuery = "SELECT b.b_id, e.e_name, b.b_date, b.b_status, b.total_amount " +
                          "FROM tbl_booking b JOIN tbl_event e ON b.e_id = e.e_id WHERE b.u_id = ?";
        String[] headers = {"ID", "Event", "Date", "Status", "Total"};
        String[] columns = {"b_id", "e_name", "b_date", "b_status", "total_amount"};

        try {
            java.util.List<java.util.Map<String, Object>> rows = con.fetchRecords(sqlQuery, Main.currentUserID);

            int colCount = columns.length;
            int[] colWidths = new int[colCount];
            for (int i = 0; i < colCount; i++) colWidths[i] = headers[i].length();

            java.util.List<String[]> data = new java.util.ArrayList<>();
            for (java.util.Map<String, Object> row : rows) {
                String[] r = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    Object val = row.get(columns[i].toUpperCase());
                    if (val == null) val = row.get(columns[i]);
                    String s = (val == null) ? "" : val.toString();
                    r[i] = s;
                    if (s.length() > colWidths[i]) colWidths[i] = s.length();
                }
                data.add(r);
            }

            for (int i = 0; i < colCount; i++) colWidths[i] += 2;

            StringBuilder separator = new StringBuilder();
            separator.append("-");
            for (int width : colWidths) {
                for (int i = 0; i < width + 3; i++) separator.append("-");
            }
            separator.append("-");

            System.out.println(separator);
            StringBuilder headerLine = new StringBuilder("| ");
            for (int i = 0; i < colCount; i++) {
                headerLine.append(String.format("%-" + colWidths[i] + "s | ", headers[i]));
            }
            System.out.println(headerLine);
            System.out.println(separator);

            for (String[] r : data) {
                StringBuilder rowLine = new StringBuilder("| ");
                for (int i = 0; i < colCount; i++) {
                    rowLine.append(String.format("%-" + colWidths[i] + "s | ", r[i]));
                }
                System.out.println(rowLine);
            }

            System.out.println(separator);

        } catch (Exception e) {
            System.out.println("Error retrieving your bookings: " + e.getMessage());
        }
    }
    
    public void createBooking(){
        System.out.println("\n----------");
        System.out.println("CREATE BOOKING");
        System.out.println("----------");

        adminDashboard.adminDashboard ad = new adminDashboard.adminDashboard();

        // Show Events
        System.out.println("\nAvailable Events:");
        ad.viewEvent();

        System.out.print("\nEnter Event ID: ");
        int eventID = 0;
        try {
            eventID = Integer.parseInt(Main.inp.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Event ID.");
            return;
        }

        // Show Menus
        System.out.println("\nAvailable Menus:");
        ad.viewMenu();

        System.out.print("\nEnter Menu ID: ");
        int menuID = 0;
        try {
            menuID = Integer.parseInt(Main.inp.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid Menu ID.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int qty = 1;
        try {
            qty = Integer.parseInt(Main.inp.nextLine());
            if (qty < 1) qty = 1;
        } catch (Exception e) {
            qty = 1;
        }

        // Get MENU PRICE
        String sqlPrice = "SELECT m_price FROM tbl_menu WHERE m_id = ?";
        float price = 0;

        try (Connection conn = con.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlPrice)) {

            pstmt.setInt(1, menuID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getFloat("m_price");
            } else {
                System.out.println("Menu not found!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        float total = price * qty;
        String bookingDate = java.time.LocalDate.now().toString();

        String sqlAdd = "INSERT INTO tbl_booking(u_id, e_id, b_date, b_status, total_amount) VALUES (?, ?, ?, ?, ?)";

        con.addRecord(sqlAdd, Main.currentUserID, eventID, bookingDate, "Pending", total);

        System.out.println("\nBooking Created Successfully!");
    }   
}
