/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import client.Client;
import domain.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author moore
 */
public class MessagesFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form MessagesFrame
     */
    public MessagesFrame(Client client, String cxId) {
        MessagesFrame.client = client;
        this.cxId = cxId;
        initComponents();
        addMessagesToChat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatHistory = new javax.swing.JTextArea();
        messageField = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Messages");
        setName("messagesFrame"); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
//        setVisible(true);

        sendButton.setText("Send");
        sendButton.setAutoscrolls(true);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chatHistory.setEditable(false);
        chatHistory.setColumns(20);
        chatHistory.setLineWrap(true);
        chatHistory.setRows(5);
        chatHistory.setName("chatHistory"); // NOI18N
        jScrollPane1.setViewportView(chatHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                                                .addComponent(sendButton)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sendButton)
                                        .addComponent(messageField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String messageText = messageField.getText();

        Message message = new Message();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        Date time = new Date();

        if(!messageText.equals("")){
            message.setMessageContent(messageText);
            message.setSender("Customer");
            message.setEmployeeId("");
            message.setCustomerId(cxId);
            message.setSentDate(dateFormatter.format(date));
            message.setSentTime(timeFormatter.format(time));

            client.sendAction("Save Message");
            client.sendObject(message);
            client.receiveResponse();

            chatHistory.setText("");
            addMessagesToChat(); //refreshes the chat
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addMessagesToChat(){
        ArrayList<Message> mList = new ArrayList<>();

        client.sendAction("Get Customer Message List");
        client.sendObject(cxId);
        client.receiveResponse();
        mList = client.getMessagesList();

        for(Message message : mList ){
            if (message.getSender().equals("Customer")) {

                chatHistory.append("\n" + message.getSentDate() + " " + message.getSentTime() + "-->");
                chatHistory.append("\tCustomer: " + message.getMessageContent());
            } else if (message.getSender().equals("Employee")){
                chatHistory.append("\n" + message.getSentDate() + " " + message.getSentTime() + "-->");
                chatHistory.append("\tGrizzly's Employee: " + message.getMessageContent());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatHistory;
    private String cxId;
    private static Client client;
    private javax.swing.JButton sendButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField messageField;
    // End of variables declaration//GEN-END:variables
}
