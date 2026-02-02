package SWIFT;

import Config.Config;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class BookingRecords extends javax.swing.JPanel {

    public BookingRecords() {
        initComponents();
        setOpaque(false);
    }

    public void loadBookingData() {
        // Updated column names to match your ERD + Total Amount
        String[] columnNames = {"Booking ID", "Name", "Event/Equip ID", "Date", "Status", "Amount"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // SQL JOIN: We join Booking with User to get the u_name using the u_id
        String sql = "SELECT b.b_id, u.u_name, b.e_id, b.b_date, b.b_status, b.total_amount " +
                     "FROM Booking b " +
                     "LEFT JOIN user u ON b.u_id = u.u_id";

        try (Connection conn = Config.connect()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("b_id"),
                        rs.getString("u_name"), // Fetched from User table via JOIN
                        rs.getInt("e_id"),
                        rs.getString("b_date"),
                        rs.getString("b_status"),
                        "$" + rs.getDouble("total_amount")
                    });
                }
                tblBookings.setModel(model);
            }
        } catch (SQLException e) {
            System.err.println("Booking DB Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("BOOKINGS");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 14, 400, 40));

        tableScroll.setBackground(new java.awt.Color(153, 153, 153));

        tblBookings.setBackground(new java.awt.Color(102, 102, 102));
        tblBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBookings.setSelectionForeground(new java.awt.Color(153, 153, 153));
        tblBookings.getTableHeader().setResizingAllowed(false);
        tblBookings.getTableHeader().setReorderingAllowed(false);
        tableScroll.setViewportView(tblBookings);

        add(tableScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 510, 280));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable tblBookings;
    // End of variables declaration//GEN-END:variables
}