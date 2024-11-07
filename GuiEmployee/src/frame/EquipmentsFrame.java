/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package frame;

import client.Client;
import domain.Equipment;
import view.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author moore
 */
public class EquipmentsFrame extends javax.swing.JInternalFrame {

    private static Client client;
    private ArrayList<Equipment> eList = new ArrayList<>();
    private DefaultTableModel model;

    /**
     * Creates new form EquipmentsFrame
     */
    public EquipmentsFrame(Client client) {
        EquipmentsFrame.client = client;
        initComponents();
        addEquipmentsToTable();
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
        update = new javax.swing.JButton();
        create = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        equipmentTable = new javax.swing.JTable();

        setTitle("Equipments");
        setName("equipmentsFrame"); // NOI18N
//        setVisible(true);
        setResizable(true);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        moveToFront();

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

        update.setText("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = equipmentTable.getSelectedRow();
                DashboardFrame.desktop.add(new UpdateEquipment(client, model.getValueAt(row,0))).setVisible(true);
            }
        });

        create.setText("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame.desktop.add(new CreateEquipment(client)).setVisible(true);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        equipmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Cost Per Day", "Rented", "Customer ID", "Date Rented", "Return Date", "Event"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        equipmentTable.setColumnSelectionAllowed(true);
        equipmentTable.setRowSelectionAllowed(true);
        equipmentTable.setCellSelectionEnabled(false);
        equipmentTable.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(equipmentTable);
        equipmentTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(create)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 343, Short.MAX_VALUE)
                    .addComponent(equipmentsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 344, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(create)
                    .addComponent(update)
                    .addComponent(delete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 163, Short.MAX_VALUE)
                    .addComponent(equipmentsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGap(0, 163, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int row = equipmentTable.getSelectedRow();

        client.sendAction("Delete Equipment");
        client.sendObject((String) model.getValueAt(row,0));
        client.receiveResponse();

        if (client.responseFlag == true){
            JOptionPane.showMessageDialog(EquipmentsFrame.this,"Equipment deleted succesfully.", "Update Equipment", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(EquipmentsFrame.this,"Failed to delete equipment.", "Delete Equipment", JOptionPane.ERROR_MESSAGE);
        }

        model.setRowCount(0);
        addEquipmentsToTable();
    }//GEN-LAST:event_deleteActionPerformed

    private void addEquipmentsToTable(){
        client.sendAction("Get Equipment List");
        client.receiveResponse();
        this.eList = client.getEquipmentsList();

        System.out.println(eList.toString());

        model = (DefaultTableModel) equipmentTable.getModel();
        Object rowData [] = new Object[9];
        for(int i=0; i < eList.size(); i++){
            rowData[0] = eList.get(i).getEquipmentId();
            rowData[1] = eList.get(i).getEquipmentName();
            rowData[2] = eList.get(i).getEquipmentType();
            rowData[3] = eList.get(i).getCostPerDay();
            rowData[4] = eList.get(i).getRented();
            rowData[5] = eList.get(i).getCustomerId();
            rowData[6] = eList.get(i).getDateRented();
            rowData[7] = eList.get(i).getReturnDate();
            rowData[8] = eList.get(i).getEvent();

            model.addRow(rowData);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create;
    private javax.swing.JButton delete;
    private javax.swing.JTable equipmentTable;
    private javax.swing.JInternalFrame equipmentsFrame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
