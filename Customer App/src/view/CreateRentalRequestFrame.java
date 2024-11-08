/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import javax.swing.*;
import client.Client;
import domain.*;

import java.awt.event.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author moore
 */
public class CreateRentalRequestFrame extends JInternalFrame {

    private static Client client;
    private RentalRequest rr;

    /**
     * Creates new form CreateRentalRentalFrame
     */
    public CreateRentalRequestFrame(Client client, String eId) {
        CreateRentalRequestFrame.client = client;
        initComponents();
        equipmentIdField.setText(eId);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        submitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        equipmentIdField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        costLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        startDateChooser = new com.toedter.calendar.JDateChooser();
        endDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        totalCostLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        eventField = new javax.swing.JTextField();
        calculateButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Create Rental Request");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
//        setVisible(true);

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Create a new rental request.");

        jLabel2.setText("Equipment ID");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Cost Per Day");

        costLabel.setText("$");

        jLabel4.setText("Start Date");

        jLabel5.setText("End Date");

        jLabel6.setText("Total Cost");

        totalCostLabel.setText("$");

        jLabel7.setText("Event");

        calculateButton.setText("Calculate Total");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(submitButton)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5))
                                                                .addGap(55, 55, 55)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(equipmentIdField)
                                                                        .addComponent(costLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(startDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                                                        .addComponent(endDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel7))
                                                                .addGap(71, 71, 71)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(totalCostLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(eventField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 17, Short.MAX_VALUE)))))
                                                .addGap(47, 47, 47)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(calculateButton)
                                                        .addComponent(searchButton))))
                                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(equipmentIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(costLabel))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(startDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(calculateButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(totalCostLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(eventField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addComponent(submitButton)
                                .addGap(20, 20, 20))
        );

        pack();
    }

    private void submitButtonActionPerformed(ActionEvent evt) {
        rr = new RentalRequest();

        if(endDateChooser.getDate() != null && startDateChooser.getDate() !=null) {
            float totalCost =0 ;

            if(endDateChooser.getDate().getTime() > startDateChooser.getDate().getTime()) {
                long numDays = 1 + ((endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime()) / 86400000);
                totalCost = numDays * equipment.getCostPerDay();
                totalCostLabel.setText("$ " + totalCost);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");




                rr.setCustomerId(client.getLoggedInCustomer().getCustomerId());
                rr.setEquipmentId(equipmentIdField.getText());
                rr.setTotalCost(totalCost);
                rr.setStartDate(dateFormat.format(startDateChooser.getDate()));
                rr.setReturnDate(dateFormat.format(endDateChooser.getDate()));
                rr.setStatus("Pending");
                rr.setEvent(eventField.getText());

                client.sendAction("Save Rental Request");
                client.sendObject(rr);
                client.receiveResponse();

            }

            if (client.responseFlag) {
                equipmentIdField.setText("");
                costLabel.setText("$");
                totalCostLabel.setText("$");
                submitButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Request has been sent.");
            }
        } else {
            JOptionPane.showMessageDialog(CreateRentalRequestFrame.this, "Invalid dates entered. \nDouble check the start and end dates.", "Request Status", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String equipmentId = equipmentIdField.getText();

        client.sendAction("Get Equipment");
        client.sendObject(equipmentId);
        client.receiveResponse();
        equipment = client.getEquipment();
        if(equipment == null){
            JOptionPane.showMessageDialog(CreateRentalRequestFrame.this, "Equipment not found", "Equipment Search", JOptionPane.INFORMATION_MESSAGE);
        } else {
            costLabel.setText("$" + equipment.getCostPerDay());
            submitButton.setEnabled(true);
        }
    }

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if(endDateChooser.getDate() != null && startDateChooser.getDate() !=null) {
            if(endDateChooser.getDate().getTime() > startDateChooser.getDate().getTime()) {
                int numDays = (int) (1 + ((endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime()) / 86400000));
                System.out.println(numDays);
                float totalCost = numDays * equipment.getCostPerDay();
                totalCostLabel.setText("$ " + totalCost);
            }
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel costLabel;
    private Equipment equipment;
    private javax.swing.JLabel totalCostLabel;
    private javax.swing.JTextField equipmentIdField;
    private javax.swing.JTextField eventField;
    private com.toedter.calendar.JDateChooser startDateChooser;
    private com.toedter.calendar.JDateChooser endDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton calculateButton;
    // End of variables declaration
}
