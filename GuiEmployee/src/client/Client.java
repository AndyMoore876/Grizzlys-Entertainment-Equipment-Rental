package client;
import domain.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Client {

    private static final Logger Logger = LogManager.getLogger(Client.class);
    private Socket connectionSocket;
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private String action;
    public Employee loggedInEmployee;
    private Customer loggedInCustomer;
    private User user;
    private Equipment equipment;
    private Customer customer;
    private Transaction transaction;
    private Message message;
    private RentalRequest rentalRequest;
    private ArrayList <Transaction> transactionList;
    private ArrayList <Equipment> equipmentsList;
    private ArrayList <Message> messagesList;
    private ArrayList<RentalRequest> rentalRequestsList;
    private ArrayList<Customer> customerList;
    public Boolean responseFlag;
    private Boolean response;

    public Client(){
        this.createConnection();
        this.configureStreams();
    }

    private void createConnection(){
        try{
            connectionSocket = new Socket("127.0.0.1",8888);
            Logger.info("Connection established with server at 127.0.0.1:8888.");
        }catch(IOException ioe){
            Logger.error("Unable to connect to server at 127.0.0.1:8888.");
            Logger.error(ioe);
        }
    }

    private void configureStreams(){
        try{
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
            Logger.info("Configured object input stream successfully.");
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            Logger.info("Configured object output stream successfully.");
        }catch(IOException e){
            Logger.error("Error configuring the object input and output streams");
            Logger.error(e);
        }
    }

    public void closeConnection(){
        try{
            objOs.close();
            Logger.info("Object output stream closed by client");
            objIs.close();
            Logger.info("Object output stream closed by client");
            connectionSocket.close();
            Logger.info("Connection with server closed by client");
        }catch(IOException e){
            Logger.error("Error closing object input and output streams.");
            Logger.error("Error closing the connection with the server.");
            Logger.error(e);
        }
    }

    //sends an action to the server
    public void sendAction(String action){
        this.action = action;
        try{
            objOs.writeObject(action);
            Logger.info(action + " sent to the server successfully");
        }catch(IOException e){
            Logger.error(" '"+ action + "' not sent to the server.");
            Logger.error(e);
        }
    }

    //send an object to the server using generics
    public <T> void sendObject(T obj){
        try{
            objOs.writeObject(obj);
            Logger.info(obj.getClass().getSimpleName() + " object sent to the server.");
        }catch(IOException e){
            Logger.error("Error sending the " + obj.getClass().getSimpleName() +" object to the server.");
            Logger.error(e);
        }
    }

    //method to send login details to the server
    public void sendLoginInfo(String userId, String password){
        try{
            user = new User(userId, password);
            objOs.writeObject(user);
            Logger.info("User credentials sent to the server for login attempt.");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error connecting to the server. Unable to signin.", "Login Status", JOptionPane.INFORMATION_MESSAGE);
            Logger.error("Error sending the login credentials to the server unable to login.");
            Logger.error(e);
        }
    }


    //method to receive the response from the client
    public <T> void receiveResponse(){
        System.out.println(action);
        try{

            Logger.info("Receiving Response from server", action);

            if(action.equalsIgnoreCase("Login Customer")){
                Boolean flag = (Boolean) objIs.readObject();
                if(flag == true){

//                    JOptionPane.showMessageDialog(null, "Login Successful", "Customer Login Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Customer log in successful");
                    Logger.info("Customer session started" );
                    sendAction("Get Customer");
                    sendObject(user.getId());
                    receiveResponse();
                    this.loggedInCustomer = customer;

                } else {
//                    JOptionPane.showMessageDialog(null, "Login Unuccessful", "Customer Login Status", JOptionPane.ERROR_MESSAGE);
                    Logger.info("Customer log in attempt unsuccessful");
                }
            }

            if(action.equalsIgnoreCase("Login Employee")){
                Boolean flag = (Boolean) objIs.readObject();
                if(flag == true){

                    //Get employee from database if login is successful
//                    JOptionPane.showMessageDialog(null, "Login Successful", "Employee Login Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Employee log in successful");
                    Logger.info("Employee session started" );

                    sendAction("Get Employee");
                    sendObject(user.getId());
                    receiveResponse();
//                  loggedInEmployee = (Employee) objIs.readObject();


                }  else {
//                    JOptionPane.showMessageDialog(null, "Login Unsuccessful", "Employee Login", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Employee log in attempt unsuccessful");
                }
            }

            if(action.equalsIgnoreCase("Get Employee")){
                action = "";
                this.loggedInEmployee = (Employee) objIs.readObject();

                if(loggedInEmployee == null ){
                    JOptionPane.showMessageDialog(null, "Employee could not be found", "Get Employee Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Employee not found.");
                } else {
                    Logger.info("Employee object received from the server successfully");
                }
            }

            //checks if the attempt to save, update or delete an object in the database was successful
            if(action.contains("Save") || action.contains("Update") || action.contains("Delete")){
                responseFlag = (Boolean) objIs.readObject();

                System.out.println(responseFlag);
                if(responseFlag == true){
//                    JOptionPane.showMessageDialog(null, "Transaction Successful", action + " Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Transaction successful");
                } else {
//                    JOptionPane.showMessageDialog(null, "Transaction Unsuccessful", action + " Status", JOptionPane.ERROR_MESSAGE);
                    Logger.error("Transaction failed.");
                }
            }

            //get equipment from the server
            if (action.equalsIgnoreCase("Get Equipment")){

                this.equipment = (Equipment) objIs.readObject();

                if(equipment == null ){
                    JOptionPane.showMessageDialog(null, "Equipment could not be found", "Get Equipment Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Equipment not found.");
                } else {
                    Logger.info("Equipment object received from the server successfully");
                }
            }

            //get Customer from the server
            if (action.equalsIgnoreCase("Get Customer")){
                action = "";
                this.customer = (Customer) objIs.readObject();

                if(customer == null ){
                    JOptionPane.showMessageDialog(null, "Customer could not be found", "Get Customer Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Customer not found.");
                } else {
                    Logger.info("Customer object received from the server successfully");
                }
            }

            //get message from the server
            if (action.equalsIgnoreCase("Get Message")){

                this.message = (Message) objIs.readObject();

                if(message == null ){
                    JOptionPane.showMessageDialog(null, "Message could not be found", "Get Message Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Message not found.");
                } else {
                    Logger.info("Message object received from the server");
                }
            }

            //get rental request from the server
            if (action.equalsIgnoreCase("Get RentalRequest")){
                this.rentalRequest = (RentalRequest) objIs.readObject();

                if(rentalRequest == null ){
                    JOptionPane.showMessageDialog(null, "Rental request could not be found", "Get Rental Request Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Rental Request not found.");
                } else {
                    Logger.info("Message object received from the server");
                }
            }

            //get transaction from the server
            if (action.equalsIgnoreCase("Get Transaction")){

                this.transaction = (Transaction) objIs.readObject();

                if(transaction == null ){
                    JOptionPane.showMessageDialog(null, "Transaction could not be found", "Get Transaction Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Transaction not found.");
                } else {
                    Logger.info("Transaction object received from the server");
                }
            }

            //get transaction list from the server
            if(action.equalsIgnoreCase("Get Transaction List") || action.equalsIgnoreCase("Get Customer Transaction List")){
                this.transactionList = (ArrayList<Transaction>) objIs.readObject();

                if(transactionList == null){
                    JOptionPane.showMessageDialog(null, "Transactions could not be retrieved", "Get Transactions Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Transactions could not be retrieved.");
                } else {
                    Logger.info("Transactions List received from the server");
                }
            }

            //get customer list from the server
            if(action.equalsIgnoreCase("Get Customer List")){
                this.customerList = (ArrayList<Customer>) objIs.readObject();

                if(customerList == null){
                    JOptionPane.showMessageDialog(null, "Customers could not be retrieved", "Get Customer Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Customer could not be retrieved.");
                } else {
                    Logger.info("Customer List received from the server");
                }
            }

            //get equipment list from the server
            if(action.equalsIgnoreCase("Get Equipment List")){
                this.equipmentsList = (ArrayList<Equipment>) objIs.readObject();

                if(equipmentsList == null){
                    JOptionPane.showMessageDialog(null, "Equipments could not be retrieved", "Get Equipments Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Equipments List could not be retrieved.");
                } else {
                    Logger.info("Equipments List received from the server");
                }
            }

            //get messages list from the server
            if(action.equalsIgnoreCase("Get Message List") || action.equalsIgnoreCase("Get Customer Message List")){
                this.messagesList = (ArrayList<Message>) objIs.readObject();

                if(messagesList == null){
                    JOptionPane.showMessageDialog(null, "Messages could not be retrieved", "Get Messages Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Messages List could not be retrieved.");
                } else {
                    Logger.info("Messages List received from the server");
                }
            }

            //get rental request list from the server
            if(action.equalsIgnoreCase("Get Rental Request List") || action.equalsIgnoreCase("Get Customer Rental Request List")){
                this.rentalRequestsList = (ArrayList<RentalRequest>) objIs.readObject();

                if(rentalRequestsList == null){
                    JOptionPane.showMessageDialog(null, "Rental Requests could not be retrieved", "Get Rental Requests Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Rental Requests List could not be retrieved.");
                } else {
                    Logger.info("Rental Requests List received from the server");
                }
            }

        }catch(ClassCastException ex){
            Logger.error("Error casting object to the provided class. Please see exception below for details.");
            Logger.error(ex);
        } catch (ClassNotFoundException ex){
            Logger.error("Unable to locate provided class. Please see exception below for details.");
            Logger.error(ex);
        } catch (SocketException e){
            JOptionPane.showMessageDialog(null, "Server Connection Lost. A restart is required.", "Connection Lost", JOptionPane.ERROR_MESSAGE);
            Logger.error("Connection with the server has been lost");

        }
        catch (IOException ex){
            Logger.error("IO Exception thrown. Please see exception below for details.");
            Logger.error(ex);
        }
    }

    public User getUser() {
        return user;
    }

    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    public Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Message getMessage() {
        return message;
    }

    public RentalRequest getRentalRequest() {
        return rentalRequest;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public ArrayList<Equipment> getEquipmentsList() {
        return equipmentsList;
    }

    public ArrayList<Message> getMessagesList() {
        return messagesList;
    }

    public ArrayList<RentalRequest> getRentalRequestsList() {
        return rentalRequestsList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public Boolean getResponse() {
        return response;
    }

}
