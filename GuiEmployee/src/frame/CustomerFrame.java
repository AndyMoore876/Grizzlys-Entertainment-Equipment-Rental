/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frame;

import client.Client;
import domain.Customer;
import view.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author moore
 */
public class CustomerFrame extends javax.swing.JInternalFrame {

    private static Client client;
    private ArrayList<Customer> cList = new ArrayList<>();
    /**
     *
     * Creates new form EquipmentsFrame
     */
    public CustomerFrame(Client client) {
        CustomerFrame.client = client;
        initComponents();
        addCutomersToTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        equipmentsFrame = new javax.swing.JInternalFrame();
        recordPayment = new javax.swing.JButton();
        messagesButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        transactionsButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Customers");
        setAutoscrolls(true);
        setName("rentalRequestsFrame"); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
//        setVisible(true);

        equipmentsFrame.setMaximizable(true);
        equipmentsFrame.setResizable(true);
        equipmentsFrame.setTitle("Equipments");

        javax.swing.GroupLayout equipmentsFrameLayout = new javax.swing.GroupLayout(equipmentsFrame.getContentPane());
        equipmentsFrame.getContentPane().setLayout(equipmentsFrameLayout);
        equipmentsFrameLayout.setHorizontalGroup(
            equipmentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        equipmentsFrameLayout.setVerticalGroup(
            equipmentsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        recordPayment.setText("Record Payment");
        recordPayment.setAutoscrolls(true);
        recordPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordPaymentActionPerformed(evt);
            }
        });

        messagesButton.setText("Messages");
        messagesButton.setAutoscrolls(true);
        messagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagesButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete Customer Profile");
        deleteButton.setAutoscrolls(true);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        customerTable.setRowSelectionAllowed(true);
        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "First Name", "Last Name", "Phone Number", "Email", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setColumnSelectionAllowed(true);
        customerTable.setShowHorizontalLines(true);
        customerTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(customerTable);
        customerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("This table shows the list of all customers in the system. ");

        jLabel2.setFont(new java.awt.Font("sansserif", 3, 12)); // NOI18N
        jLabel2.setText("Click the ID for the customer you are working with.");

        updateButton.setText("Update Contact Info");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        transactionsButton.setText("Transactions");
        transactionsButton.setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messagesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recordPayment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transactionsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addGap(29, 29, 29))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 333, Short.MAX_VALUE)
                    .addComponent(equipmentsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 333, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messagesButton)
                    .addComponent(recordPayment)
                    .addComponent(deleteButton)
                    .addComponent(updateButton)
                    .addComponent(transactionsButton))
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 255, Short.MAX_VALUE)
                    .addComponent(equipmentsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 255, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        model = (DefaultTableModel) customerTable.getModel();

        int row = customerTable.getSelectedRow();

        String cxId = (String) model.getValueAt(row,0);

        int result = JOptionPane.showConfirmDialog(CustomerFrame.this, "Delete Customer: " + cxId);
        switch (result) {
            case JOptionPane.YES_OPTION:

                client.sendAction("Delete Customer");
                client.sendObject(cxId);
                client.receiveResponse();

                if(client.responseFlag){
                    JOptionPane.showMessageDialog(CustomerFrame.this, "Customer Deleted Successfully", "Delete Customer", JOptionPane.INFORMATION_MESSAGE);
                    model.setRowCount(0);
                    addCutomersToTable();
                } else{
                    JOptionPane.showMessageDialog(CustomerFrame.this, "Failed to delete customer.", "Delete Customer", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case JOptionPane.NO_OPTION:
            default:
        }

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void recordPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordPaymentActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) customerTable.getModel();

        int row = customerTable.getSelectedRow();

        Customer cx = new Customer((String) model.getValueAt(row,0), (String) model.getValueAt(row,1), (String) model.getValueAt(row,2), (String) model.getValueAt(row,3), (String) model.getValueAt(row,4), (Float) model.getValueAt(row,5));

        float amount = Float.parseFloat(JOptionPane.showInputDialog(null, "Payment Amount:"));

        if(amount>0 && amount <= cx.getBalance()){
            cx.setBalance(cx.getBalance()-amount);
            client.sendAction("Update Customer");
            client.sendObject(cx);
            client.receiveResponse();

            if (client.responseFlag){
                JOptionPane.showMessageDialog(CustomerFrame.this, "Payment Recorded and Balance Updated", "Payment Status", JOptionPane.INFORMATION_MESSAGE);
                model.setRowCount(0);
                addCutomersToTable();
            } else{
                JOptionPane.showMessageDialog(CustomerFrame.this, "Unbale to process payment.", "Payment Status", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(CustomerFrame.this, "Payment Amount Invalid. Minimum amount 1 and Max amount customer balance.", "Payment Status", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_recordPaymentActionPerformed

    private void messagesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagesButtonActionPerformed
        int row = customerTable.getSelectedRow();
        DashboardFrame.desktop.add(new MessagesFrame(client, (String) model.getValueAt(row,0))).setVisible(true);
    }//GEN-LAST:event_messagesButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int row = customerTable.getSelectedRow();
        DashboardFrame.desktop.add(new UpdateCustomer(client, (String) model.getValueAt(row,0))).setVisible(true);
    }//GEN-LAST:event_updateButtonActionPerformed


    private void addCutomersToTable(){
        client.sendAction("Get Customer List");
        client.receiveResponse();
        this.cList = client.getCustomerList();

        System.out.println(cList.toString());

        model = (DefaultTableModel) customerTable.getModel();
        Object rowData [] = new Object[9];

        for(int i=0; i < cList.size(); i++){

            rowData[0] = cList.get(i).getCustomerId();
            rowData[1] = cList.get(i).getFirstName();
            rowData[2] = cList.get(i).getLastName();
            rowData[3] = cList.get(i).getPhoneNumber();
            rowData[4] = cList.get(i).getEmail();
            rowData[5] = cList.get(i).getBalance();

            model.addRow(rowData);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteButton;
    private DefaultTableModel model;
    private javax.swing.JInternalFrame equipmentsFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton messagesButton;
    private javax.swing.JButton recordPayment;
    private javax.swing.JButton transactionsButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
