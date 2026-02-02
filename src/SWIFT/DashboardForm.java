package SWIFT;

import java.awt.Color;

public class DashboardForm extends javax.swing.JFrame {

    private UserRecords userPanel;
    private BookingRecords bookingPanel;
    private String userRole; 

    // Default Constructor
    public DashboardForm() {
        this("User"); 
    }

    // Main Constructor - Triggered by successful login
    public DashboardForm(String role) {
        this.userRole = (role == null) ? "User" : role;
        initComponents();
        customInit();
        applyRolePermissions(); 
    }

    private void applyRolePermissions() {
        // Strict check: Only the role "Admin" (from our new admin table) sees user management
        boolean isAdmin = "Admin".equalsIgnoreCase(userRole);
        
        btnUser.setVisible(isAdmin);
        User1.setVisible(isAdmin); // Static icon
        User.setVisible(false);    // GIF Hover initial state
        
        // If not admin, relocate Booking button or adjust layout if needed
        if (!isAdmin) {
            System.out.println("Regular user logged in: User Management disabled.");
        }
    }

    private void customInit() {
        this.setBackground(new Color(0, 0, 0, 0)); 
        this.setLocationRelativeTo(null);
        
        // Initialize Panels
        userPanel = new UserRecords();
        userPanel.setVisible(false);
        getContentPane().add(userPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 570, 350));
        
        bookingPanel = new BookingRecords();
        bookingPanel.setVisible(false);
        getContentPane().add(bookingPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 570, 350));
        
        Booking1.setVisible(false); 
        
        // Z-Order Fix: Ensuring panels stay above background but below buttons
        getContentPane().setComponentZOrder(userPanel, 0);
        getContentPane().setComponentZOrder(bookingPanel, 0);
        getContentPane().setComponentZOrder(btnUser, 0);
        getContentPane().setComponentZOrder(btnBooking, 0);
        getContentPane().setComponentZOrder(btnLogout, 0);
        
        // Background at the very bottom (last index)
        getContentPane().setComponentZOrder(Background, getContentPane().getComponentCount() - 1);
        
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Logo = new javax.swing.JLabel();
        btnBooking = new javax.swing.JButton();
        Booking1 = new javax.swing.JLabel();
        Booking = new javax.swing.JLabel();
        btnUser = new javax.swing.JButton();
        User = new javax.swing.JLabel();
        User1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/LOGO.png"))); // NOI18N
        getContentPane().add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 470, 250));

        btnBooking.setContentAreaFilled(false);
        btnBooking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBookingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBookingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBookingMouseExited(evt);
            }
        });
        getContentPane().add(btnBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 140, 40));

        Booking1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/Booking.gif"))); // NOI18N
        getContentPane().add(Booking1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 60));

        Booking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/Booking.png"))); // NOI18N
        getContentPane().add(Booking, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 160, 60));

        btnUser.setContentAreaFilled(false);
        btnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUserMouseExited(evt);
            }
        });
        getContentPane().add(btnUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 120, 40));

        User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/User.gif"))); // NOI18N
        getContentPane().add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 60));

        User1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/User.png"))); // NOI18N
        getContentPane().add(User1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 60));

        btnLogout.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 102, 102));
        btnLogout.setText("LOGOUT");
        btnLogout.setContentAreaFilled(false);
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 120, 50));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboard.image/dash.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUserMouseEntered(java.awt.event.MouseEvent evt) {
        if (btnUser.isVisible()) {
            User.setVisible(true);
            repaint();
        }
    }

    private void btnUserMouseExited(java.awt.event.MouseEvent evt) {
        User.setVisible(false);
        repaint();
    }

    private void btnUserMouseClicked(java.awt.event.MouseEvent evt) {
        // Toggle Panel: Only works if Admin
        if ("Admin".equalsIgnoreCase(userRole)) {
            bookingPanel.setVisible(false);
            boolean isVisible = userPanel.isVisible();
            userPanel.setVisible(!isVisible);
            if (!isVisible) {
                userPanel.loadUserData(); // Refresh data from DatabaseHelper
            }
            repaint();
        }
    }

    private void btnBookingMouseClicked(java.awt.event.MouseEvent evt) {
        userPanel.setVisible(false);
        boolean isVisible = bookingPanel.isVisible();
        bookingPanel.setVisible(!isVisible);
        if (!isVisible) {
            bookingPanel.loadBookingData();
        }
        repaint();
    }

    private void btnBookingMouseEntered(java.awt.event.MouseEvent evt) {
        Booking1.setVisible(true);
        repaint();
    }

    private void btnBookingMouseExited(java.awt.event.MouseEvent evt) {
        Booking1.setVisible(false);
        repaint();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginForm().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DashboardForm("Admin").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Booking;
    private javax.swing.JLabel Booking1;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel User;
    private javax.swing.JLabel User1;
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUser;
    // End of variables declaration//GEN-END:variables
}