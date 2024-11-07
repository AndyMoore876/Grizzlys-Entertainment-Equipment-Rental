/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frame;

import client.Client;
import domain.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author moore
 */
public class UpdateCustomer extends javax.swing.JInternalFrame {
    
    private static Client client;
    /**
     * Creates new form UpdateEquipment
     */
    public UpdateCustomer(Client client, String cxId) {
        UpdateCustomer.client = client;
        initComponents();
        addCutomersToTable(cxId);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        customerIdField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();

        setClosable(true);
        setTitle("Update Customer Contact Info");
//        setVisible(true);

        jLabel1.setText("Step 1: Enter the customer ID and click Search to retrieve their info.");

        jLabel2.setFont(new java.awt.Font("sansserif", 3, 12)); // NOI18N
        jLabel2.setText("Instructions to perform Customer Update");

        jLabel3.setText("Step 2: Update the relevant attributes for the customer in the table.");

        jLabel4.setText("Customer ID");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save Changes");
        saveButton.setEnabled(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Step 3: Use save changes button to save the changes made.");

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "First Name", "Last Name", "Phone Number", "Email", "Balance"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setColumnSelectionAllowed(true);
        customerTable.setShowHorizontalLines(true);
        customerTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(customerTable);
        customerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(saveButton)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(48, 48, 48)
                                                                .addComponent(searchButton)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveButton)
                                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        model = (DefaultTableModel) customerTable.getModel();

        int row = customerTable.getSelectedRow();

        Customer cx = new Customer((String) model.getValueAt(row,0), (String) model.getValueAt(row,1), (String) model.getValueAt(row,2), (String) model.getValueAt(row,3), (String) model.getValueAt(row,4), (Float) model.getValueAt(row,5));

        client.sendAction("Update Customer");
        client.sendObject(cx);
        client.receiveResponse();

        if (client.responseFlag){
            JOptionPane.showMessageDialog(UpdateCustomer.this, "Contact Info Updated Successfully", "Customer Update", JOptionPane.INFORMATION_MESSAGE);
            model.setRowCount(0);
            addCutomersToTable(cx.getCustomerId());
        } else{
            JOptionPane.showMessageDialog(UpdateCustomer.this, "Failed to Update Customer Contact Info", "Customre Update", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String cxId = customerIdField.getText();
        addCutomersToTable(cxId);
    }

    private void addCutomersToTable(String id){
        client.sendAction("Get Customer");
        client.sendObject(id);
        client.receiveResponse();
        Customer cx = client.getCustomer();

        model = (DefaultTableModel) customerTable.getModel();
        Object rowData [] = new Object[9];

        rowData[0] = cx.getCustomerId();
        rowData[1] = cx.getFirstName();
        rowData[2] = cx.getLastName();
        rowData[3] = cx.getPhoneNumber();
        rowData[4] = cx.getEmail();
        rowData[5] = cx.getBalance();

        model.addRow(rowData);

        saveButton.setEnabled(true);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTable customerTable;
    
    private DefaultTableModel model;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField customerIdField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration                   
}
