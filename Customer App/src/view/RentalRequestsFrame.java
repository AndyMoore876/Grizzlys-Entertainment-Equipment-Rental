/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import domain.RentalRequest;
import domain.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import client.Client;

/**
 *
 * @author moore
 */
public class RentalRequestsFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form EquipmentsFrame
     */
    public RentalRequestsFrame(Client client) {
        RentalRequestsFrame.client = client;
        initComponents();
        addRRToTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rrFrame = new javax.swing.JInternalFrame();
        completeButton = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rrTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Rental Requests");
        setName("rentalRequestsFrame"); // NOI18N
//        setVisible(true);

        setMaximizable(true);
        setResizable(true);
//        setVisible(true);
        setClosable(true);

        javax.swing.GroupLayout rrFrameLayout = new javax.swing.GroupLayout(rrFrame.getContentPane());
        rrFrame.getContentPane().setLayout(rrFrameLayout);
        rrFrameLayout.setHorizontalGroup(
            rrFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rrFrameLayout.setVerticalGroup(
            rrFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        completeButton.setText("Complete Request");
        completeButton.setAutoscrolls(true);
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.setAutoscrolls(true);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        rrTable.setAutoCreateRowSorter(true);
        rrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Customer ID", "Equipment ID", "Status", "Start Date", "Return Date", "Employee ID", "Total Cost", "Event"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rrTable.setColumnSelectionAllowed(true);
        rrTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(rrTable);
        rrTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setText("This table shows the list of all your rental requests in the system. ");

        jLabel2.setFont(new java.awt.Font("sansserif", 3, 12)); // NOI18N
        jLabel2.setText("Click the ID for the request you want to Complete or Delete.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(completeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 438, Short.MAX_VALUE)
                    .addComponent(rrFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 439, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(completeButton)
                    .addComponent(delete))
                .addGap(9, 9, 9))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 199, Short.MAX_VALUE)
                    .addComponent(rrFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 199, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int row = rrTable.getSelectedRow();

        if(!((String) model.getValueAt(row, 3)).equals("Complete")){
            client.sendAction("Delete Rental Request");
            client.sendObject(model.getValueAt(row, 0));
            client.receiveResponse();
            if (client.responseFlag) {
                model.setRowCount(0);
                addRRToTable();
            }
        } else {
            JOptionPane.showMessageDialog(App.desktopPane, "Request already completed and cannot be deleted.", "Delete Rental Request", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        int row = rrTable.getSelectedRow();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime currentTime = LocalTime.now();

        if( ((String) model.getValueAt(row,3)).equals("Approved")) {
            Transaction transaction = new Transaction();

            transaction.setRentalRequestId((String) model.getValueAt(row, 0));
            transaction.setTransactionDate(LocalDate.now().toString());
            transaction.setTransactionTime(currentTime.format(formatter));
            transaction.setequipmentId((String) model.getValueAt(row, 0));
            transaction.setTotalAmount((Float) model.getValueAt(row, 7));
            transaction.setEvent("Event");//will be updated
            transaction.setCustomerId(client.getLoggedInCustomer().getCustomerId());
            transaction.setEmployeeId((String) model.getValueAt(row, 6));

            client.sendAction("Save Transaction");
            client.sendObject(transaction);
            client.receiveResponse();

            if (client.responseFlag) {

                RentalRequest rr = new RentalRequest((String) model.getValueAt(row,0),(String) model.getValueAt(row,1),(String) model.getValueAt(row,2), (String) model.getValueAt(row, 6), "Completed",(String) model.getValueAt(row,4),(String) model.getValueAt(row,5), (float) model.getValueAt(row,7), (String) model.getValueAt(row, 8));

                client.sendAction("Update Rental Request");
                client.sendObject(rr);
                client.receiveResponse();

                client.sendObject("Get Equipment");
                client.sendObject(rr.getEquipmentId());
                client.receiveResponse();

                client.getEquipment().setRented(true);
                client.getEquipment().setReturnDate(rr.getReturnDate());
                client.getEquipment().setDateRented(rr.getStartDate());

                client.sendAction("Update Equipment");
                client.sendObject(client.getEquipment());
                client.receiveResponse();

                model.setRowCount(0);
                addRRToTable();

                JOptionPane.showMessageDialog(RentalRequestsFrame.this, "Rental Request completed and the transaction is now available to view in your transactions.");
            }
        }else {
            JOptionPane.showMessageDialog(RentalRequestsFrame.this, "Rental Request cannot be completed as it was not Approved or has already been completed. Please check its status.", "Request Status", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_completeButtonActionPerformed

    private void addRRToTable(){
        client.sendAction("Get Customer Rental Request List");
        client.sendObject(client.getLoggedInCustomer().getCustomerId());
        client.receiveResponse();
        this.rrList = new ArrayList<>();
        this.rrList = client.getRentalRequestsList();

//        System.out.println(rrList.toString());

        model = (DefaultTableModel) rrTable.getModel();
        Object rowData [] = new Object[9];

        for(int i=0; i < rrList.size(); i++){

            rowData[0] = rrList.get(i).getRentalRequestId();
            rowData[1] = rrList.get(i).getCustomerId();
            rowData[2] = rrList.get(i).getEquipmentId();
            rowData[3] = rrList.get(i).getStatus();
            rowData[4] = rrList.get(i).getStartDate();
            rowData[5] = rrList.get(i).getReturnDate();
            rowData[6] = rrList.get(i).getEmployeeId();
            rowData[7] = rrList.get(i).getTotalCost();
            rowData[8] = rrList.get(i).getEvent();

            model.addRow(rowData);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton completeButton;
    private DefaultTableModel model;

    private static Client client;
    private ArrayList<RentalRequest> rrList = new ArrayList<>();
    private javax.swing.JButton delete;
    private javax.swing.JInternalFrame rrFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable rrTable;
    // End of variables declaration//GEN-END:variables
}
