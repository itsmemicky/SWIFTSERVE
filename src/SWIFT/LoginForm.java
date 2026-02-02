package SWIFT;

import Config.Config;
import javax.swing.JOptionPane;
import java.awt.Color;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        // Initialize DB to ensure admin table and default admin exist
        Config.initializeDB();
        initComponents();
        customInit(); 
    }

    private void customInit() {
        // Force the window to be visible size-wise
        this.setSize(760, 560); 
        
        // Hide hover effects initially
        Login1.setVisible(false);
        Exit1.setVisible(false);
        Register.setVisible(false); 

        // Transparency and Centering
        this.setBackground(new Color(0, 0, 0, 0)); 
        this.setLocationRelativeTo(null);
    }

    /**
     * Logic for processing login across multi-table setup
     */
    private void handleLoginProcess() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Updated: Check role from DatabaseHelper (checks both admin and user tables)
        String role = Config.getUserRole(username, password);

        if (role != null) {
            btnLog.setEnabled(false);
            
            // Visual login transition
            new Thread(() -> {
                java.awt.EventQueue.invokeLater(() -> { 
                    Login1.setVisible(true); 
                    repaint(); 
                });
                
                // Small delay for the GIF animation effect
                try { Thread.sleep(500); } catch (InterruptedException e) {}

                java.awt.EventQueue.invokeLater(() -> {
                    this.dispose();
                    // Opens dashboard with the specific role found
                    new DashboardForm(role).setVisible(true); 
                });
            }).start();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLog = new javax.swing.JButton();
        Login1 = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();
        btnExt = new javax.swing.JButton();
        Exit1 = new javax.swing.JLabel();
        Exit = new javax.swing.JLabel();
        btnReg = new javax.swing.JButton();
        Register = new javax.swing.JLabel();
        Register1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(760, 560));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLog.setBorder(null);
        btnLog.setContentAreaFilled(false);
        btnLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogMouseExited(evt);
            }
        });
        btnLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogActionPerformed(evt);
            }
        });
        getContentPane().add(btnLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 150, 60));

        Login1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Log1i.gif"))); // NOI18N
        getContentPane().add(Login1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 170, 60));

        Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Logi.png"))); // NOI18N
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 180, -1));

        btnExt.setBorder(null);
        btnExt.setContentAreaFilled(false);
        btnExt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExtMouseExited(evt);
            }
        });
        btnExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtActionPerformed(evt);
            }
        });
        getContentPane().add(btnExt, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 150, 50));

        Exit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Exi.gif"))); // NOI18N
        getContentPane().add(Exit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 170, 80));

        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Exi.png"))); // NOI18N
        getContentPane().add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 210, 50));

        btnReg.setBorder(null);
        btnReg.setContentAreaFilled(false);
        btnReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegMouseExited(evt);
            }
        });
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });
        getContentPane().add(btnReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 150, 40));

        Register.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.gif"))); // NOI18N
        getContentPane().add(Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, 50));

        Register1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.png"))); // NOI18N
        getContentPane().add(Register1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 200, 60));

        jLabel2.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 140, 40));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 170, 30));

        jLabel3.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 140, 40));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 170, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Background1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 740, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogMouseEntered
        Login1.setVisible(true);
        repaint();
    }//GEN-LAST:event_btnLogMouseEntered

    private void btnLogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogMouseExited
        Login1.setVisible(false);
        repaint();
    }//GEN-LAST:event_btnLogMouseExited

    private void btnLogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogMouseClicked
        handleLoginProcess();
    }//GEN-LAST:event_btnLogMouseClicked

    private void btnRegMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegMouseEntered
        Register.setVisible(true);
        repaint();
    }//GEN-LAST:event_btnRegMouseEntered

    private void btnRegMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegMouseExited
        Register.setVisible(false);
        repaint();
    }//GEN-LAST:event_btnRegMouseExited

    private void btnRegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegMouseClicked
        new RegistrationForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegMouseClicked

    private void btnExtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtMouseEntered
        Exit1.setVisible(true);
        repaint();
    }//GEN-LAST:event_btnExtMouseEntered

    private void btnExtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtMouseExited
        Exit1.setVisible(false);
        repaint();
    }//GEN-LAST:event_btnExtMouseExited

    private void btnExtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnExtMouseClicked

    private void btnLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogActionPerformed

    private void btnExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtActionPerformed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Exit;
    private javax.swing.JLabel Exit1;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel Login1;
    private javax.swing.JLabel Register;
    private javax.swing.JLabel Register1;
    private javax.swing.JButton btnExt;
    private javax.swing.JButton btnLog;
    private javax.swing.JButton btnReg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}