package thesimpleems_16nov;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
public class DisplayEmployeeInfoJFrame extends javax.swing.JFrame {
    
    
    // ATTRIBUTES
    private MyHashTable mainHT;  // This contains the ref value for theHT of MainJFrame.
    
    private DefaultTableModel model;
    
    private int empCounter;

    
    // CONSTRUCTORS
    /**
     * Creates new form SeparateJFrame
     */
    public DisplayEmployeeInfoJFrame() {
        initComponents();
        
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
        jTable1.addMouseListener(new MouseAdapter() {
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
        });
        
        this.empCounter = -1; // Row position in table for the employee
        
        jLabel1.setText("Number of Employees: " + numInHT);
        
        for (int i = 0; i < mainHT.buckets.length; i++) {
            for (int j = 0; j < mainHT.buckets[i].size(); j++) {

                EmployeeInfo theEmp = mainHT.buckets[i].get(j);
//                EmployeeInfo theEmp = mainHT.returnByEmployeeNumber(theEmpNum, false);

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
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setAutoCreateColumnsFromModel(false);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setText("Number of Employees: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DisplayEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisplayEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisplayEmployeeInfoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new DisplayEmployeeInfoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
