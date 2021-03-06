package thesimpleems_16nov;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import thesimpleems_16nov.FTE;
import javax.swing.table.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author P0068839
 */
public class LookupEmployeeInfoJFrame extends javax.swing.JFrame {
    
    
    // ATTRIBUTES
    private MyHashTable mainHT;  // This contains the ref value for theHT of MainJFrame.
    
    private DefaultTableModel model;
    
    private int empCounter;
    
    private boolean firstInit = true;

    
    // CONSTRUCTORS
    /**
     * Creates new form SeparateJFrame
     */
    public LookupEmployeeInfoJFrame() {
        initComponents();
        
        
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e){
                changed();
            }
            public void removeUpdate(DocumentEvent e){
                changed();
            }
            public void insertUpdate(DocumentEvent e){
                changed();
            }
            public void changed() {
                boolean jTF1empty = jTextField1.getText().isEmpty();
                if(jTF1empty){
                    jButton1.setEnabled(false);
                }else {
                    jButton1.setEnabled(true);
                }
            }
        });
        jLabel4.setVisible(false);
        /*
        model.addColumn("Status");
        model.addColumn("Employee Number");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        */
        //System.out.println("\n\nmodel row count is " + model.getRowCount());

    }
    
    
    // METHODS
    
    
    public void setMainHT(MyHashTable refvalForHT) {
        mainHT = refvalForHT;
        System.out.println("HERE HERE HERE HERE");
        initModel();
    }
    public void initModel(){
        int numInHT = mainHT.getNumInHashTable();
        
        model = new DefaultTableModel(new Object[] {"Status",
                                                    "Employee Num",
                                                    "First Name",
                                                    "Last Name",
                                                    "Gender",
                                                    "Work Loc.",
                                                    "Deduct Rate",
                                                    "Yearly Salary", 
                                                    "Hourly Wage", 
                                                    "Hours per Week", 
                                                    "Weeks per Year",
                                                    "Net Ann. Income"},
                                                    numInHT);
        model.setRowCount(1);
        jTable1.setModel(model);
        jTable1.setAutoCreateColumnsFromModel(true);
        jTable1.setRowHeight(40);
        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_ALL_COLUMNS);
        
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.getTableHeader().setReorderingAllowed(false);
            
//        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//            public void valueChanged(ListSelectionEvent event){
//                if(!event.getValueIsAdjusting()){
//                    ChangeEmployeeInfoJFrame newCEIJFrame = new ChangeEmployeeInfoJFrame();
//                    newCEIJFrame.setVisible(true);
//                    newCEIJFrame.setMainHT(mainHT);
//                }                
//            }
//        });

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent){
                
                try{
                    JTable table = (JTable) mouseEvent.getSource();
                    Point point = mouseEvent.getPoint();
                    int row = table.rowAtPoint(point);
                    if(mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1){
                        int theEmpNum = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString());
                        ChangeEmployeeInfoJFrame newCEIJFrame = new ChangeEmployeeInfoJFrame();
                        newCEIJFrame.setVisible(true);
                        newCEIJFrame.setMainHT(mainHT);
                        newCEIJFrame.changeFromTable(theEmpNum);
                        dispose();
//                        System.err.println(table.getValueAt(table.getSelectedRow(), 1).toString());
                    }
                }catch(Exception e){
                    System.err.println(e.getMessage());
                }
                
            }
        };
        
        if(firstInit){
            jTable1.addMouseListener(ml);
            firstInit = false;
        }
            
        this.empCounter = -1; // Row position in table for the employee
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Search by Employee Number");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setName(""); // NOI18N

        jTable1.setAutoCreateColumnsFromModel(false);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("Finding Employee");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Search Employee Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // Reinitialize an empty model (clear previous data)
        initModel();
        
//        jTextField1.setText("");
//        System.out.println("");
//        System.out.println("Number of employees in the HT is " + numInHT);
        
//        if (numInHT > 0) {
//            System.out.println("Here are the employees:");
//            for (int i = 0; i < mainHT.buckets.length; i++) {
//                for (int j = 0; j < mainHT.buckets[i].size(); j++) {
                    
//                    EmployeeInfo theEmp = mainHT.buckets[i].get(j);

        try {
            int theEmpNum = Integer.parseInt(jTextField1.getText());
            EmployeeInfo theEmp = mainHT.returnByEmployeeNumber(theEmpNum, false);
            if(theEmp != null){
                String theFirstName = theEmp.firstName;
                String theLastName = theEmp.lastName;
                jLabel4.setForeground(new Color(0, 150, 0));
                jLabel4.setText("Employee Found!");       
                jLabel4.setVisible(true);
//
                jTextField1.setText("");

                empCounter++;
            
                    
                System.out.println("  Employee number " + Integer.toString(theEmp.getEmpNum()));
                System.out.println("  First name, last name : " + theEmp.getFirstName() + " " + theEmp.getLastName());

                if (theEmp instanceof FTE) {
                    FTE theFTE = (FTE) theEmp;
                    System.out.println("    That employee has gross yearly salary $" + Double.toString(theFTE.getYearlySalary()));
                    System.out.println("    That employee has net yearly income $" + Double.toString(theFTE.calcAnnualNetIncome()));

                    model.setValueAt("Full Time", empCounter, 0);
                    model.setValueAt(theFTE.getEmpNum(), empCounter, 1);
                    model.setValueAt(theFTE.getFirstName(), empCounter, 2);
                    model.setValueAt(theFTE.getLastName(), empCounter, 3);
                    String genderOutput = "N/A";
                    if(theFTE.getGender() == 0){
                        genderOutput = "Male";
                    }else if(theFTE.getGender() == 1){
                        genderOutput = "Female";
                    }else{
                        genderOutput = "Other";
                    }
                    model.setValueAt(genderOutput, empCounter, 4);
                    String workLocOutput = "N/A";
                    if(theFTE.getWorkLoc() == 0){
                        workLocOutput = "Mississauga";
                    }else if(theFTE.getWorkLoc() == 1){
                        workLocOutput = "Toronto";
                    }else{
                        workLocOutput = "Markham";
                    }
                    model.setValueAt(workLocOutput, empCounter, 5);
                    model.setValueAt(theFTE.getDeductRate() * 100 + "%", empCounter, 6);
                    model.setValueAt(theFTE.getYearlySalary(), empCounter, 7);
                    model.setValueAt("N/A", empCounter, 8);
                    model.setValueAt("N/A", empCounter, 9);
                    model.setValueAt("N/A", empCounter, 10);
                    
                    double netAnnInc = theFTE.getYearlySalary() * (1 - theFTE.getDeductRate());
                    netAnnInc = ((double) Math.round(netAnnInc * 100)) / 100;
                    model.setValueAt(netAnnInc, empCounter, 11);
                }

                if (theEmp instanceof PTE) {
                    PTE thePTE = (PTE) theEmp;
                    System.out.println("    That employee has hourly wage $" + Double.toString(thePTE.hourlyWage));
                    System.out.println("    That employee has hours per week " + Double.toString(thePTE.hoursPerWeek));
                    System.out.println("    That employee has weeks per year " + Double.toString(thePTE.weeksPerYear));

                    model.setValueAt("Part Time", empCounter, 0);
                    model.setValueAt(thePTE.getEmpNum(), empCounter, 1);
                    model.setValueAt(thePTE.getFirstName(), empCounter, 2);
                    model.setValueAt(thePTE.getLastName(), empCounter, 3);
                    String genderOutput = "N/A";
                    if(thePTE.getGender() == 0){
                        genderOutput = "Male";
                    }else if(thePTE.getGender() == 1){
                        genderOutput = "Female";
                    }else{
                        genderOutput = "Other";
                    }
                    model.setValueAt(genderOutput, empCounter, 4);
                    String workLocOutput = "N/A";
                    if(thePTE.getWorkLoc() == 0){
                        workLocOutput = "Mississauga";
                    }else if(thePTE.getWorkLoc() == 1){
                        workLocOutput = "Toronto";
                    }else{
                        workLocOutput = "Markham";
                    }
                    model.setValueAt(workLocOutput, empCounter, 5);
                    model.setValueAt(thePTE.getDeductRate() * 100 + "%", empCounter, 6);
                    model.setValueAt("N/A", empCounter, 7);
                    model.setValueAt(thePTE.getHourlyWage(), empCounter, 8);
                    model.setValueAt(thePTE.getHoursPerWeek(), empCounter, 9);
                    model.setValueAt(thePTE.getWeeksPerYear(), empCounter, 10);
                    
                    double grossAnnInc = thePTE.getHourlyWage() * thePTE.getHoursPerWeek() * thePTE.getWeeksPerYear();
                    double netAnnInc = grossAnnInc * (1 - thePTE.getDeductRate());
                    netAnnInc = ((double) Math.round(netAnnInc * 100)) / 100;
                    model.setValueAt(netAnnInc, empCounter, 11);
               }
            }else{
                jLabel4.setForeground(Color.RED);
                jLabel4.setText("No Employee Found!");
                jLabel4.setVisible(true);
            }
           
        }catch(Exception e) {
            jLabel4.setForeground(Color.RED);
            jLabel4.setText("Invalid information!");
            jLabel4.setVisible(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LookupEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LookupEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LookupEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LookupEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LookupEmployeeInfoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
