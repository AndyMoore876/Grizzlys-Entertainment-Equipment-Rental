package client;
import domain.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private static final Logger Logger = LogManager.getLogger(Client.class);
    private Socket connectionSocket;
    private static ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private String action;
    private Employee loggedInEmployee;
    private Customer loggedInCustomer;
    Equipment equipment = new Equipment();
    private Customer customer = new Customer();
    private Event event = new Event();
    private Transaction transaction = new Transaction();
    private Message message = new Message();
    private RentalRequest rentalRequest = new RentalRequest();
    private ArrayList <Transaction> transactionList = new ArrayList<>();
    private ArrayList <Equipment> equipmentsList = new ArrayList<>();
    private ArrayList <Message> messagesList = new ArrayList<>();
    private ArrayList <Event> eventList = new ArrayList<>();
    private ArrayList<RentalRequest> rentalRequestsList = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();

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
            objOs = new ObjectOutputStream((connectionSocket.getOutputStream()));
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
            objOs.writeObject(userId);
            Logger.info("User ID sent to the server for login attempt.");
            objOs.writeObject(password);
            Logger.info("Password sent to the server for login attempt.");
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error connecting to the server. Unable to signin.", "Login Status", JOptionPane.INFORMATION_MESSAGE);
            Logger.error("Error sending the login credentials to the server unable to login.");
            Logger.error(e);
        }
    }


    //method to receive the response from the client
    public <T> void receiveResponse(){
        try{

            if(action.equalsIgnoreCase("Login Customer")){
                Boolean flag = (Boolean) objIs.readObject();
                if(flag == true){
                    JOptionPane.showMessageDialog(null, "Login Successful", "Customer Login Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Customer log in successful");
                }
            }

            if(action.equalsIgnoreCase("Login Employee")){
                Boolean flag = (Boolean) objIs.readObject();
                if(flag == true){
                    JOptionPane.showMessageDialog(null, "Login Successful", "Employee Login Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Employee log in successful");
                    Logger.info("Employee session started" );
                }
            }

            //checks if the attempt to save, update or delete an object in the database was successful
            if(action.contains("Save") || action.contains("Update") || action.contains("Delete")){
                Boolean flag = (Boolean) objIs.readObject();
                if(flag == true){
                    JOptionPane.showMessageDialog(null, "Transaction Successful", action + " Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.info("Transaction successful");
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

                this.customer = (Customer) objIs.readObject();

                if(customer == null ){
                    JOptionPane.showMessageDialog(null, "Customer could not be found", "Get Customer Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Customer not found.");
                } else {
                    Logger.info("Customer object received from the server successfully");
                }
            }

            //get event from the server
            if (action.equalsIgnoreCase("Get Event")){

                this.event = (Event) objIs.readObject();

                if(event == null ){
                    JOptionPane.showMessageDialog(null, "Event could not be found", "Get Event Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Event not found.");
                } else {
                    Logger.info("Event object received from the server");
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

//            if (action.equalsIgnoreCase("Get Message")){
//                T message = (T) objIs.readObject();
//
//                if(message == null ){
//                    JOptionPane.showMessageDialog(null, "Message could not be found", "Get Message Status", JOptionPane.INFORMATION_MESSAGE);
//                    Logger.error("Message not found.");
//                } else {
//                    Logger.info("Message object received from the server");
//                }
//            }

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
            if (action.equalsIgnoreCase("Get RentalRequest")){

                this.transaction = (Transaction) objIs.readObject();

                if(transaction == null ){
                    JOptionPane.showMessageDialog(null, "Transaction could not be found", "Get Transaction Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Transaction not found.");
                } else {
                    Logger.info("Transaction object received from the server");
                }
            }

            //get transaction list from the server
            if(action.equalsIgnoreCase("Get Transaction List")){
                this.transactionList = (ArrayList<Transaction>) objIs.readObject();

                if(transactionList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Transactions could not be retrieved", "Get Transactions Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Transactions could not be retrieved.");
                } else {
                    Logger.info("Transactions List received from the server");
                }
            }

            //get customer list from the server
            if(action.equalsIgnoreCase("Get Customer List")){
                this.customerList = (ArrayList<Customer>) objIs.readObject();

                if(customerList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Customers could not be retrieved", "Get Customer Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Customer could not be retrieved.");
                } else {
                    Logger.info("Customer List received from the server");
                }
            }

            //get equipment list from the server
            if(action.equalsIgnoreCase("Get Equipment List")){
                this.equipmentsList = (ArrayList<Equipment>) objIs.readObject();

                if(equipmentsList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Equipments could not be retrieved", "Get Equipments Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Equipments List could not be retrieved.");
                } else {
                    Logger.info("Equipments List received from the server");
                }
            }

            //get events list from the server
            if(action.equalsIgnoreCase("Get Event List")){
                this.eventList = (ArrayList<Event>) objIs.readObject();

                if(eventList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Events could not be retrieved", "Get Events Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Events List could not be retrieved.");
                } else {
                    Logger.info("Events List received from the server");
                }
            }

            //get messages list from the server
            if(action.equalsIgnoreCase("Get Message List")){
                this.messagesList = (ArrayList<Message>) objIs.readObject();

                if(messagesList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Messages could not be retrieved", "Get Messages Status", JOptionPane.INFORMATION_MESSAGE);
                    Logger.error("Messages List could not be retrieved.");
                } else {
                    Logger.info("Messages List received from the server");
                }
            }

            //get rental request list from the server
            if(action.equalsIgnoreCase("Get Rental Request List")){
                this.rentalRequestsList = (ArrayList<RentalRequest>) objIs.readObject();

                if(rentalRequestsList.isEmpty()){
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
        } catch (IOException ex){
            Logger.error("IO Exception thrown. Please see exception below for details.");
            Logger.error(ex);
        }
    }
}
