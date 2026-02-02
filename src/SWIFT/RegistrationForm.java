package SWIFT;

import Config.Config;
import java.awt.Color;
import javax.swing.JOptionPane;

public class RegistrationForm extends javax.swing.JFrame {

    public RegistrationForm() {
        initComponents();
        customInit();
    }

    private void customInit() {
        this.setBackground(new Color(0, 0, 0, 0)); 
        this.setLocationRelativeTo(null);

        // 1. Keep them enabled so colors stay bright
        RegisterGIF.setVisible(false);
        RegisterGIF.setEnabled(true); 
        RegisterStatic.setEnabled(true);

        // 2. Make the button transparent but "Front and Center"
        btnSubmit.setOpaque(false);
        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setFocusPainted(false); // Removes the little square inside the button

        // 3. Force layering: Button (0) is top, GIF (1) is middle, Static (2) is below GIF
        getContentPane().setComponentZOrder(btnSubmit, 0);
        getContentPane().setComponentZOrder(RegisterGIF, 1);
        getContentPane().setComponentZOrder(RegisterStatic, 2);
        getContentPane().setComponentZOrder(Background, getContentPane().getComponentCount() - 1);
    }

    private void handleRegistration(boolean isAdminRequest) {
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        String contact = txtContact.getText().trim();
        String role = isAdminRequest ? "Admin" : comboRole.getSelectedItem().toString();
        String status = "Active";

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isAdminRequest) {
            String key = JOptionPane.showInputDialog(this, "Enter Admin Registration Key:", "Authentication", JOptionPane.WARNING_MESSAGE);
            if (!"Admin123".equals(key)) {
                JOptionPane.showMessageDialog(this, "Invalid Admin Key!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Save to Database
        boolean success = Config.registerUser(name, email, password, contact, role, status);
        
        if (success) {
            JOptionPane.showMessageDialog(this, role + " Registered Successfully!");
            new LoginForm().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();
        btnAdminReg = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        RegisterGIF = new javax.swing.JLabel();
        RegisterStatic = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Full Name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 100, 30));
        getContentPane().add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 170, 25));

        jLabel2.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 100, 30));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 170, 25));

        jLabel3.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 100, 30));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 170, 25));

        jLabel4.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contact:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 100, 30));
        getContentPane().add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 170, 25));

        jLabel6.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Role:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 100, 30));

        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Catering Manager", "Chef/Cook", "Waitstaff/Server", "Bartender", "Kitchen Assistant", "Event Coordinator" }));
        getContentPane().add(comboRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 170, 25));

        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubmitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubmitMouseExited(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 130, 40));

        btnAdminReg.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        btnAdminReg.setForeground(new java.awt.Color(153, 255, 255));
        btnAdminReg.setText("Admin Registration");
        btnAdminReg.setContentAreaFilled(false);
        btnAdminReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminRegActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdminReg, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 140, 30));

        btnBack.setFont(new java.awt.Font("Visitor TT1 BRK", 3, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(204, 204, 204));
        btnBack.setText("Back to Login");
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 120, 20));

        RegisterGIF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.gif"))); // NOI18N
        getContentPane().add(RegisterGIF, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 170, 60));

        RegisterStatic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Register.png"))); // NOI18N
        getContentPane().add(RegisterStatic, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 200, 50));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login.image/Background1.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -20, 740, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {                                       
        handleRegistration(false);
    }                                      

    private void btnSubmitMouseEntered(java.awt.event.MouseEvent evt) {                                        
        // Force the hand cursor to appear
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegisterGIF.setVisible(true); 
        repaint();
    }                                        

    private void btnSubmitMouseExited(java.awt.event.MouseEvent evt) {                                      
        RegisterGIF.setVisible(false); 
        repaint();
    }                                      

    private void btnAdminRegActionPerformed(java.awt.event.ActionEvent evt) {                                            
        handleRegistration(true);
    }                                           

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        new LoginForm().setVisible(true); 
        this.dispose();
    }                                       

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RegistrationForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel RegisterGIF;
    private javax.swing.JLabel RegisterStatic;
    private javax.swing.JButton btnAdminReg;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}