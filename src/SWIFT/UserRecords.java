package SWIFT;

import Config.Config;
import java.awt.Color;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class UserRecords extends javax.swing.JPanel {

    public UserRecords() {
        initComponents(); 
        setOpaque(false);
        // Style the table a bit for a dark theme
        tblUsers.getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Loads user data from the SQLite database.
     * Matches the ERD: u_id, u_name, u_email, u_contact, u_role, status
     */
    public void loadUserData() {
        String[] columnNames = {"ID", "Full Name", "Email Address", "Contact #", "User Role", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Keep table read-only
            }
        };

        // Query matches your DatabaseHelper registerUser logic
        String sql = "SELECT u_id, u_name, u_email, u_contact, u_role, status FROM user";

        try (Connection conn = Config.connect()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("u_id"),
                        rs.getString("u_name"),
                        rs.getString("u_email"),
                        rs.getString("u_contact"),
                        rs.getString("u_role"),
                        rs.getString("status")
                    });
                }
                tblUsers.setModel(model);
            }
        } catch (SQLException e) {
            System.err.println("User Records Load Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("USER RECORDS");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 7, 400, 50));

        tblUsers.setBackground(new java.awt.Color(45, 45, 45));
        tblUsers.setForeground(new java.awt.Color(255, 255, 255));
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsers.setGridColor(new java.awt.Color(102, 102, 102));
        tblUsers.setRowHeight(25);
        tblUsers.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tableScroll.setViewportView(tblUsers);

        add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 510, 280));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}