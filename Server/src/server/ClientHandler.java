package server;

import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.annotations.common.util.impl.Log;
import server.factories.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private Socket connectionSocket;
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private static final Logger Logger = LogManager.getLogger(ClientHandler.class);
    private static Connection dbConn;

    ClientHandler(Socket socket){
        Logger.info("ClientHandler instance created.");
        this.connectionSocket = socket;
        Logger.info("Connection socket configured");
        dbConn = DBConnectionFactory.getDatabaseConnection();
        configureStreams();
    }

    private void configureStreams() {

        System.out.println("Testa");
        try {

            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
            Logger.info("Object output stream configured successfully.");
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
            Logger.info("Object input stream configured successfully.");

        } catch (IOException e) {
            Logger.error("Error configuring the object streams. Please see exception details.");
            Logger.error(e);
        }
    }

    public void closeConnection() {
        try {
            objIs.close();
            Logger.info("Object input stream closed successfully.");
            objOs.close();
            Logger.info("Object output stream closed successfully.");
            connectionSocket.close();
            Logger.info("Connection socket closed successfully.");
        } catch (IOException e) {
            Logger.error("Error closing connection and object streams. Please see exceptin for details");
            Logger.error(e);
        }
    }

    private Customer getCustomer(String customerId){
        String query = "SELECT * FROM customer WHERE customerId = ?";
        Customer customer = new Customer();

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, customerId);
            ResultSet results = statement.executeQuery();

            while(results.next()) {
                customer.setCustomerId(results.getString("customerId"));
                customer.setFirstName(results.getString("firstName"));
                customer.setLastName(results.getString("lastname"));
                customer.setEmail(results.getString("email"));
                customer.setPhoneNumber(results.getString("phoneNumber"));
                customer.setBalance(results.getFloat("balance"));
            }

            Logger.info("Query to get customer from database executed");

        }catch (SQLException e){
            Logger.error("There was an error connecting to the database.");
            Logger.error(e);
        }catch (Exception e){
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }

        return customer;
    }

    private ArrayList<Customer> getCustomerList(){
        String query = "SELECT * FROM customer";
        Customer customer ;
        ArrayList customerList = new ArrayList();

        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            while(results.next()) {
                //get the customer attributes from the database
                customer = new Customer();
                customer.setCustomerId(results.getString("customerId"));
                customer.setFirstName(results.getString("firstName"));
                customer.setLastName(results.getString("lastname"));
                customer.setEmail(results.getString("email"));
                customer.setPhoneNumber(results.getString("phoneNumber"));
                customer.setBalance(results.getFloat("balance"));
                //add new customer object to the customerlist
                if(customer!= null) {
                    customerList.add(customer);
                }
            }

//			results.close();
        }catch(SQLException se) {
            Logger.error("There was an error connecting to the database.");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return customerList;
    }

    //function to save a customer to the database
    private Boolean saveCustomer(Customer customer){
        String query = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getEmail());
            statement.setFloat(6, customer.getBalance());
            int result = statement.executeUpdate();
            Logger.info("SQL statement to save customer executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    //delete customer from the database
    private Boolean deleteCustomer(String customerId){
        String query = "DELETE FROM customer WHERE customerId = ?";

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, customerId);
            int result = statement.executeUpdate();

            if(result == 1){
                Logger.info("DELETE query executed successfully.");
                return true;
            }

        }catch (SQLException e){
            Logger.error("There was an error connecting to the database.");
            Logger.error(e);
        }catch (Exception e){
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }

        return false;
    }

    private Boolean updateCustomer(Customer customer){
        String query = "UPDATE customer SET firstName = ?, lastName = ?, phoneNumber = ?, email = ?, balance = ? WHERE customerId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(6, customer.getCustomerId());
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNumber());
            statement.setString(4, customer.getEmail());
            statement.setFloat(5, customer.getBalance());
            int result = statement.executeUpdate();
            Logger.info("SQL statement to update customer executed.");
            if(result == 1) {
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    };

    private Boolean saveEmployee(Employee employee){
        String query = "INSERT INTO employee VALUES(?,?,?)";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, employee.getEmployeeId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());

            int result = statement.executeUpdate();

            if(result == 1) {
                Logger.info("SQL statement to save employee executed successfully.");
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean updateEmployee(Employee employee){
        String query = "UPDATE employee SET firstName = ?, lastName = ? WHERE employeeId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmployeeId());

            int result = statement.executeUpdate();
            Logger.info("SQL statement to update employee executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Employee getEmployee(String employeeId){
        String query = "SELECT * FROM employee WHERE employeeId = ?";
        Employee employee = new Employee();
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, employeeId);
            Logger.info(statement.toString());
            ResultSet result = statement.executeQuery();
            Logger.info("SQL statement to get employee executed.");

            while(result.next()) {
                System.out.println(result.getString("employeeId"));
                employee.setEmployeeId(result.getString("employeeId"));
                employee.setFirstName(result.getString("firstName"));
                employee.setLastName(result.getString("lastName"));
            }
            return employee;
        }catch(SQLException se) {

            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
            se.printStackTrace();
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return null;
    }

    private Boolean deleteEmployee(String employeeId){
        String query = "DELETE FROM employee WHERE employeeId = ?;";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, employeeId);

            int result = statement.executeUpdate();

            Logger.info("SQL statement to delete employee executed.");

            if(result == 1){
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean saveEquipment(Equipment equipment){
        String query = "INSERT INTO equipment VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, equipment.getEquipmentId());
            statement.setString(2, equipment.getEquipmentName());
            statement.setString(3, equipment.getEquipmentType());
            statement.setString(4, equipment.getCustomerId());
            statement.setString(5, equipment.getEvent());
            statement.setBoolean(6, equipment.getRented());
            statement.setString(7, equipment.getDateRented());
            statement.setString(8, equipment.getReturnDate());
            statement.setFloat(9, equipment.getCostPerDay());
            int result = statement.executeUpdate();
            Logger.info("SQL statement to save equipment executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean updateEquipment(Equipment equipment){
        String query = "UPDATE equipment SET equipmentName = ?, equipmentType = ?, customerId = ?, event = ?, rented = ?, dateRented = ?, returnDate = ?, costPerDay = ? WHERE equipmentId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);

            statement.setString(1, equipment.getEquipmentName());
            statement.setString(2, equipment.getEquipmentType());
            statement.setString(3, equipment.getCustomerId());
            statement.setString(4, equipment.getEvent());
            statement.setBoolean(5, equipment.getRented());
            statement.setString(6, equipment.getDateRented());
            statement.setString(7, equipment.getReturnDate());
            statement.setFloat(8, equipment.getCostPerDay());
            statement.setString(9, equipment.getEquipmentId());

            int result = statement.executeUpdate();
            Logger.info("SQL statement to save equipment executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Equipment getEquipment(String equipmentId){
        String query = "SELECT * FROM equipment WHERE equipmentId = ?";
        Equipment equipment = new Equipment();
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, equipmentId);
            ResultSet result = statement.executeQuery();
            Logger.info("SQL statement to get equipment executed.");

            while(result.next()) {
                equipment.setEquipmentId(result.getString("equipmentId"));
                equipment.setEquipmentType(result.getString("equipmentType"));
                equipment.setEquipmentName(result.getString("equipmentName"));
                equipment.setCustomerId(result.getString("customerId"));
                equipment.setEvent(result.getString("event"));
                equipment.setRented(result.getBoolean("rented"));
                equipment.setDateRented(result.getString("dateRented"));
                equipment.setReturnDate(result.getString("returnDate"));
                equipment.setCostPerDay(result.getFloat("costPerDay"));
            }
            return equipment;
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return null;
    }

    private ArrayList<Equipment> getEquipmentList(){
        String query = "SELECT * FROM equipment";
        Equipment equipment;

        ArrayList<Equipment> eList = new ArrayList<>();
        try {

            PreparedStatement statement = dbConn.prepareStatement(query);
            ResultSet result = statement.executeQuery(query);
            Logger.info("SQL statement to get equipment list executed.");

            while(result.next()) {
                equipment = new Equipment();
                equipment.setEquipmentId(result.getString("equipmentId"));
                equipment.setEquipmentType(result.getString("equipmentType"));
                equipment.setEquipmentName(result.getString("equipmentName"));
                equipment.setCustomerId(result.getString("customerId"));
                equipment.setEvent(result.getString("event"));
                equipment.setRented(result.getBoolean("rented"));
                equipment.setDateRented(result.getString("dateRented"));
                equipment.setReturnDate(result.getString("returnDate"));
                equipment.setCostPerDay(result.getFloat("costPerDay"));

                System.out.println(equipment.toString());
                eList.add(equipment);
            }

            System.out.println(eList.toString());
            result.close();
            return eList;
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return null;
    }

    private Boolean deleteEquipment(String equipmentId){
        String query = "DELETE FROM equipment WHERE equipmentId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, equipmentId);

            int result = statement.executeUpdate();

            Logger.info("SQL statement to delete equipment executed.");

            if(result == 1){
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean saveMessage(Message message){
        String query = "INSERT INTO message VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);


            statement.setString(1,message.getMessageId());
            statement.setString(2,message.getMessageContent());
            statement.setString(3,message.getCustomerId());
            statement.setString(4,message.getEmployeeId());
            statement.setString(5,message.getSentDate());
            statement.setString(6,message.getSentTime());
            statement.setString(7,message.getSender());

            Logger.info("SQL statement to save message executed.");
            int result = statement.executeUpdate();
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }
        return false;
    }

    private Boolean updateMessage(Message message){
        String query = "UPDATE message SET messageContent = ?, customerId = ?, employeeId = ?, sentDate = ?, sentTime = ?, responseDate = ?, responseTime = ?, responded = ?, response = ? WHERE messageId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);

            statement.setString(1,message.getMessageContent());
            statement.setString(2,message.getCustomerId());
            statement.setString(3,message.getEmployeeId());
            statement.setString(4,message.getSentDate().toString());
            statement.setString(5,message.getSentTime().toString());
            statement.setString(10,message.getMessageId());

            Logger.info("SQL statement to update message executed.");
            int result = statement.executeUpdate();
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }
        return false;
    }

    private Message getMessage(String messageId){
        String query = "SELECT * FROM message WHERE messageId = ?";
        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1,messageId);

            ResultSet result = statement.executeQuery();
            Message message = null;
            while(result.next()) {
                message = new Message(result.getString("messageId"), result.getString("messageContent"), result.getString("customerId"), result.getString("employeeId"), result.getString("sentDate"), result.getString("sentTime"), result.getString("Sender"));
            }
            Logger.info("SQL to retrieve message executed");
            return message;
        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }

        return null;
    }

    private ArrayList<Message> getMessageList(String customerId){
        String query = "SELECT * FROM message WHERE customerId = ?";
        ArrayList<Message> messageList = new ArrayList<>();
        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1,customerId);

            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Message message = new Message(result.getString("messageId"), result.getString("messageContent"), result.getString("customerId"), result.getString("employeeId"), result.getString("sentDate"), result.getString("sentTime"), result.getString("Sender"));
                messageList.add(message);
            }
            Logger.info("SQL to retrieve message list executed");
            return messageList;
        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }

        return null;
    }

    private ArrayList<Message> getMessageList(){
        String query = "SELECT * FROM message WHERE customerId = ?";
        ArrayList<Message> messageList = new ArrayList<>();
        try{
            PreparedStatement statement = dbConn.prepareStatement(query);

            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Message message = new Message(result.getString("messageId"), result.getString("messageContent"), result.getString("customerId"), result.getString("employeeId"), result.getString("sentDate"), result.getString("sentTime"), result.getString("Sender"));
                messageList.add(message);
            }

            Logger.info("SQL to retrieve message list executed");
            return messageList;
        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }

        return null;
    }

    private Boolean deleteMessage(String messageId){
        String query = "DELETE FROM message WHERE messageId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, messageId);

            int result = statement.executeUpdate();

            Logger.info("SQL statement to delete message executed.");

            if(result == 1){
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean saveRentalRequest(RentalRequest rentalRequest){
        String query = "INSERT INTO rental_request VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);


            statement.setString(1,rentalRequest.getRentalRequestId());
            statement.setString(2, rentalRequest.getCustomerId());
            statement.setString(3, rentalRequest.getEquipmentId());
            statement.setString(4, rentalRequest.getEmployeeId());
            statement.setString(5, rentalRequest.getStatus());
            statement.setString(6, rentalRequest.getStartDate());
            statement.setString(7, rentalRequest.getReturnDate());
            statement.setFloat(8,rentalRequest.getTotalCost());
            statement.setString(9, rentalRequest.getEvent());

            int result = statement.executeUpdate();

            Logger.info("SQL statement to save rental request executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }
        return false;
    }

    private Boolean updateRentalRequest(RentalRequest rentalRequest){
        String query = "UPDATE rental_request SET customerId = ?, equipmentId = ?, employeeId = ?, status = ?, startDate = ?, returnDate = ?, totalCost = ?, event = ? WHERE rentalRequestID = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);


            statement.setString(9,rentalRequest.getRentalRequestId());
            statement.setString(1, rentalRequest.getCustomerId());
            statement.setString(2, rentalRequest.getEquipmentId());
            statement.setString(3, rentalRequest.getEmployeeId());
            statement.setString(4, rentalRequest.getStatus());
            statement.setString(5, rentalRequest.getStartDate());
            statement.setString(6, rentalRequest.getReturnDate());
            statement.setFloat(7, rentalRequest.getTotalCost());
            statement.setString(8, rentalRequest.getEvent());

            int result = statement.executeUpdate();

            Logger.info("SQL statement to update rental request executed.");
            if(result == 1) {
                return true;
            }
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }
        return false;
    }

    private RentalRequest getRentalRequest(String rentalRequestId){
        String query = "SELECT * FROM rental_request WHERE rentalRequesId = ?";

        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1,rentalRequestId);

            ResultSet result = statement.executeQuery();
            RentalRequest rr=null;
            while(result.next()) {
                rr = new RentalRequest(result.getString("rentalRequestID"), result.getString("customerId"), result.getString("equipmentId"), result.getString("employeeId"), result.getString("status"), result.getString("startDate"), result.getString("returnDate"), result.getFloat("totalCost"), result.getString("event"));
            }
            Logger.info("SQL statement to update rental request executed.");

            return rr;
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }

        return null;
    }

    private ArrayList<RentalRequest> getRentalRequestList(String customerId){
        String query = "SELECT * FROM rental_request WHERE customerId = ?";
        ArrayList<RentalRequest> rrList = new ArrayList<>();

        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1,customerId);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                RentalRequest rr = new RentalRequest(result.getString("rentalRequestID"), result.getString("customerId"), result.getString("equipmentId"), result.getString("employeeId"), result.getString("status"), result.getString("startDate"), result.getString("returnDate"), result.getFloat("totalCost"),result.getString("event"));
                rrList.add(rr);
            }

            Logger.info("SQL statement to get rental request list executed.");
            return rrList;
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }

        return null;
    }

    private ArrayList<RentalRequest> getRentalRequestList(){
        String query = "SELECT * FROM rental_request";
        ArrayList<RentalRequest> rrList = new ArrayList<>();

        try {
            PreparedStatement statement = dbConn.prepareStatement(query);

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                RentalRequest rr = new RentalRequest(result.getString("rentalRequestID"), result.getString("customerId"), result.getString("equipmentId"), result.getString("employeeId"), result.getString("status"), result.getString("startDate"), result.getString("returnDate"), result.getFloat("totalCost"),result.getString("event"));
                rrList.add(rr);
            }

            Logger.info("SQL statement to get rental request list executed.");
            return rrList;
        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occurred");
            Logger.error(e);
        }

        return null;
    }

    private Boolean deleteRentalRequest(String rentalRequestId){
        String query = "DELETE FROM rental_request WHERE rentalRequestId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, rentalRequestId);

            int result = statement.executeUpdate();

            Logger.info("SQL statement to delete rental request executed.");

            if(result == 1){
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean saveTransaction(domain.Transaction trans){
        String query = "INSERT INTO transaction VALUES(?,?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);

            statement.setString(1, trans.getTransactionId());
            statement.setString(2, trans.getTransactionDate());
            statement.setString(3, trans.getTransactionTime());
            statement.setString(4, trans.getequipmentId());
            statement.setString(5, trans.getEvent());
            statement.setString(6, trans.getCustomerId());
            statement.setString(7, trans.getEmployeeId());
            statement.setFloat(8, trans.getTotalAmount());
            statement.setString(9, trans.getRentalRequestId());

            int result = statement.executeUpdate();

            if (result == 1){
                return true;
            }

        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean updateTransaction(domain.Transaction trans){
        String query = "UPDATE transaction SET transactionDate = ?, transactionTime = ?, employeeId = ?, event = ?, customerId = ?, equipmentId = ?, totalAmount = ?, rentalRequestId = ? WHERE transactionId = ?";

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);


            statement.setString(1, trans.getTransactionDate());
            statement.setString(2, trans.getTransactionTime());
            statement.setString(3, trans.getEmployeeId());
            statement.setString(4, trans.getEvent());
            statement.setString(5, trans.getCustomerId());
            statement.setString(6, trans.getequipmentId());
            statement.setFloat(7, trans.getTotalAmount());
            statement.setString(8, trans.getRentalRequestId());
            statement.setString(9, trans.getTransactionId());


            int result = statement.executeUpdate();

            if (result == 1){
                return true;
            }

        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private domain.Transaction getTransaction(String transactionId){
        String query = "SELECT * FROM transaction WHERE transaction = ?";
        domain.Transaction trans = new domain.Transaction();

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, transactionId);

            ResultSet results = statement.executeQuery();
            while(results.next()) {
                trans.setTransactionId(results.getString("transactionId"));
                trans.setequipmentId(results.getString("equipmentId"));
                trans.setEvent(results.getString("event"));
                trans.setTransactionDate(results.getString("transactionDate"));
                trans.setTransactionTime(results.getString("transactionTime"));
                trans.setTotalAmount(results.getFloat("totalAmount"));
                trans.setEmployeeId(results.getString("employeeId"));
                trans.setCustomerId(results.getString("customerId"));
                trans.setRentalRequestId(results.getString("rentalRequestId"));
            }

            return trans;

        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured.");
            Logger.error(e);
        }
        return null;
    }
    private ArrayList<domain.Transaction> getTransactionList(){
        //returns all transactions in database
        String query = "SELECT * FROM transaction";
        domain.Transaction trans;
        ArrayList <domain.Transaction> transactionList= new ArrayList<>();

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            ResultSet results = statement.executeQuery();

            while(results.next()) {
                trans = new domain.Transaction();
                trans.setTransactionId(results.getString("transactionId"));
                trans.setequipmentId(results.getString("equipmentId"));
                trans.setEvent(results.getString("event"));
                trans.setTransactionDate(results.getString("transactionDate"));
                trans.setTransactionTime(results.getString("transactionTime"));
                trans.setTotalAmount(results.getFloat("totalAmount"));
                trans.setEmployeeId(results.getString("employeeId"));
                trans.setCustomerId(results.getString("customerId"));
                trans.setRentalRequestId(results.getString("rentalRequestId"));

                transactionList.add(trans);
            }

            return transactionList;

        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured.");
            Logger.error(e);
        }
        return null;
    }

    private ArrayList<domain.Transaction> getTransactionList(String customerId){
        //returns all transactions made by a customer with the passed customerId

        String query = "SELECT * FROM transaction WHERE customerId = ?";
        domain.Transaction trans;
        ArrayList <domain.Transaction> transactionList = new ArrayList<>();

        try{
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1,customerId);

            ResultSet results = statement.executeQuery();

            while(results.next()) {

                trans = new domain.Transaction();
                trans.setTransactionId(results.getString("transactionId"));
                trans.setequipmentId(results.getString("equipmentId"));
                trans.setEvent(results.getString("event"));
                trans.setTransactionDate(results.getString("transactionDate"));
                trans.setTransactionTime(results.getString("transactionTime"));
                trans.setTotalAmount(results.getFloat("totalAmount"));
                trans.setEmployeeId(results.getString("employeeId"));
                trans.setCustomerId(results.getString("customerId"));
                trans.setRentalRequestId(results.getString("rentalRequestId"));

                transactionList.add(trans);

            }

            return transactionList;

        } catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return null;
    }

    private Boolean deleteTransaction(String transactionId){
        String query = "DELETE FROM transaction WHERE transactionId = ?";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            statement.setString(1, transactionId);

            int result = statement.executeUpdate();

            Logger.info("SQL statement to delete transaction executed.");

            if(result == 1){
                return true;
            }

        }catch(SQLException se) {
            Logger.error("There was an error executing the SQL statement");
            Logger.error(se);
        }catch(Exception e) {
            Logger.error("Unexpected error occured");
            Logger.error(e);
        }
        return false;
    }

    private Boolean saveUser(User user){

        try{
            Session session = HibernateSessionFactoryBuilder.getSessionFactory().getCurrentSession();

            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        } catch(Exception e){
            Logger.info("Unexpected error when saving the user to the database");
            Logger.info(e);
        }
        return false;
    }

    private Boolean updateUser(User user){
        try{
            Session session = HibernateSessionFactoryBuilder.getSessionFactory().getCurrentSession();

            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return true;
        } catch(Exception e){
            Logger.info("Unexpected error when saving the user to the database");
            Logger.info(e);
        }
        return false;
    }

    private User getUser(String userId){

        Session session = null;
        try{
            try {
                session = HibernateSessionFactoryBuilder.getSessionFactory().getCurrentSession();
                Logger.info("Connected to current database session.");
            } catch(HibernateException e){
                Logger.error("Error getting database session");
            }

            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            transaction.commit();
            session.close();
            return user;
        } catch(ClassCastException e) {
            Logger.error(e);
        }catch(Exception e){
            Logger.error("Unexpected error when saving the user to the database");
            Logger.error(e);
        }
        return null;
    }

    private Boolean deleteUser(String userId){
        Session session = null;
        try{
            try {
                session = HibernateSessionFactoryBuilder.getSessionFactory().getCurrentSession();
                Logger.info("Connected to current database session.");
            } catch(HibernateException e){
                Logger.error("Error getting database session");
            }

            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            session.delete(user);
            transaction.commit();
            session.close();

            return true;
        } catch(ClassCastException e) {
            Logger.error(e);
        }catch(Exception e){
            Logger.error("Unexpected error when saving the user to the database");
            Logger.error(e);
        }
        return false;
    }

    @Override
    public void run() {
        User currentUser;
        Message message;
        Employee employee;
        Equipment equipment;
        RentalRequest rentalRequest;
        domain.Transaction transaction;
        Customer customer;
        String action;

        while (true) {

//            this.configureStreams();

            try {
                action = (String) objIs.readObject();

                if (action.equalsIgnoreCase("Save Equipment")) {
                    Equipment eqmt = (Equipment) objIs.readObject();
                    eqmt.setEquipmentId(eqmt.genId());

                    objOs.writeObject(saveEquipment(eqmt));

                }

                if (action.equalsIgnoreCase("Get Equipment")) {
                    String equipmentId = (String) objIs.readObject();
                    objOs.writeObject(getEquipment(equipmentId));
                    System.out.println(getEquipment(equipmentId).toString());
                }

                if (action.equalsIgnoreCase("Update Equipment")) {
                    Equipment eqmt = (Equipment) objIs.readObject();

                    objOs.writeObject(updateEquipment(eqmt));
                }

                if (action.equalsIgnoreCase("Delete Equipment")) {
                    String id = (String) objIs.readObject();

                    objOs.writeObject(deleteEquipment(id));
                }

                if (action.equalsIgnoreCase("Save Customer")) {
                    Customer cx = (Customer) objIs.readObject();
                    User user = new User(cx.getCustomerId(), cx.getPassword());
                    if(saveCustomer(cx)){
                        objOs.writeObject(true);
                        saveUser(user);
                    } else {
                        objOs.writeObject(false);
                    }


                }

                if (action.equalsIgnoreCase("Get Customer")) {
                    String cxId = (String) objIs.readObject();

                    objOs.writeObject(getCustomer(cxId));
                }

                if (action.equalsIgnoreCase("Update Customer")) {
                    Customer cx = (Customer) objIs.readObject();

                    objOs.writeObject(updateCustomer(cx));
                }

                if (action.equalsIgnoreCase("Delete Customer")) {
                    String cxId = (String) objIs.readObject();

                    objOs.writeObject(deleteCustomer(cxId));
                }

                if (action.equalsIgnoreCase("Save Message")) {
                    Message mes = (Message) objIs.readObject();
                    mes.setMessageId(mes.genId());

                    objOs.writeObject(saveMessage(mes));
                }

                if (action.equalsIgnoreCase("Update Message")) {
                    Message mes = (Message) objIs.readObject();

                    objOs.writeObject(updateMessage(mes));
                }

                if (action.equalsIgnoreCase("Delete Message")) {
                    String messageId = (String) objIs.readObject();

                    objOs.writeObject(deleteMessage(messageId));
                }

                if (action.equalsIgnoreCase("Get Message")) {
                    String messageId = (String) objIs.readObject();

                    objOs.writeObject(getMessage(messageId));
                }

                if (action.equalsIgnoreCase("Get Message List")) {
                    objOs.writeObject(getMessageList());
                }

                if (action.equalsIgnoreCase("Get Customer Message List")) {
                    String customerId = (String) objIs.readObject();
                    objOs.writeObject(getMessageList(customerId));
                }

                if (action.equalsIgnoreCase("Save Rental Request")) {
                    RentalRequest rr = (RentalRequest) objIs.readObject();
                    rr.setRentalRequestId(rr.genId());

                    objOs.writeObject(saveRentalRequest(rr));
                }

                if (action.equalsIgnoreCase("Update Rental Request")) {
                    RentalRequest rr = (RentalRequest) objIs.readObject();

                    objOs.writeObject(updateRentalRequest(rr));
                }

                if (action.equalsIgnoreCase("Get Rental Request")) {
                    String rrId = (String) objIs.readObject();

                    objOs.writeObject((RentalRequest) getRentalRequest(rrId));
                }

                if (action.equalsIgnoreCase("Delete Rental Request")) {
                    String rrId = (String) objIs.readObject();
                    Logger.error("Rental Request");

                    objOs.writeObject((Boolean) deleteRentalRequest(rrId));
                }

                if (action.equalsIgnoreCase("Save Employee")) {
                    Employee emp = (Employee) objIs.readObject();
                    emp.setEmployeeId(emp.genId());
                    User user = new User(emp.getEmployeeId(), emp.getPassword());
                    if((Boolean) saveEmployee(emp)){
                        System.out.println(user.getId() +" " + user.getPassword() +" " +emp.getPassword());
                        System.out.println(emp);
                        saveUser(user);
                        objOs.writeObject(true);
                    } else {
                        objOs.writeObject(false);
                    }
                }

                if (action.equalsIgnoreCase("Update Employee")) {
                    Employee emp = (Employee) objIs.readObject();

                    objOs.writeObject((Boolean) updateEmployee(emp));
                }

                if (action.equalsIgnoreCase("Delete Employee")) {
                    String empId = (String) objIs.readObject();

                    objOs.writeObject((Boolean) deleteEmployee(empId));
                }

                if (action.equalsIgnoreCase("Get Employee")) {
                    String empId = (String) objIs.readObject();

                    objOs.writeObject((Employee) getEmployee(empId));
                }

                if (action.equalsIgnoreCase("Save Transaction")) {
                    domain.Transaction trans = (domain.Transaction) objIs.readObject();
                    trans.setTransactionId(trans.genId());

                    objOs.writeObject((Boolean) saveTransaction(trans));
                }

                if (action.equalsIgnoreCase("Update Transaction")) {
                    domain.Transaction trans = (domain.Transaction) objIs.readObject();

                    objOs.writeObject((Boolean) updateTransaction(trans));
                }

                if (action.equalsIgnoreCase("Delete Transaction")) {
                    String transId = (String) objIs.readObject();

                    objOs.writeObject((Boolean) deleteTransaction(transId));
                }

                if (action.equalsIgnoreCase("Get Transaction")) {
                    String transId = (String) objIs.readObject();

                    objOs.writeObject((domain.Transaction) getTransaction(transId));
                }

                if (action.equalsIgnoreCase("Get Customer Transaction List")) {
                    String customerId = (String) objIs.readObject();
                    objOs.writeObject(getTransactionList(customerId));
                }

                if(action.equalsIgnoreCase("Get Customer List")){
                    objOs.writeObject(getCustomerList());
                }

                if (action.equalsIgnoreCase("Get Transaction List")) {
                    objOs.writeObject(getTransactionList());
                }

                if (action.equalsIgnoreCase("Get Customer Rental Request List")) {
                    String customerId = (String) objIs.readObject();
                    objOs.writeObject(getRentalRequestList(customerId));
                }

                if (action.equalsIgnoreCase("Get Rental Request List")) {
//                    String customerId = (String) objIs.readObject();
                    objOs.writeObject(getRentalRequestList());
                }

                if (action.equalsIgnoreCase("Get Equipment List")) {

                    objOs.writeObject(getEquipmentList());
                    Logger.info("Equipment list retrieved and sent to the client.");
                }

                if (action.equalsIgnoreCase("Login Customer")) {
                    User user = (User) objIs.readObject();
                    User userX = (User) getUser(user.getId());

                    Logger.info("User credentials received from client.");

                    if (user.getPassword().equals(userX.getPassword())) {
                        //this means the user credentials match what is in the database
                        objOs.writeObject(true);
                        Logger.info("Customer logged in successfully. User ID:" + user.getId());
                    } else {
                        objOs.writeObject(false);
                        Logger.info("Customer failed to log in. User ID:" + user.getId());
                    }
                }

                if (action.equalsIgnoreCase("Login Employee")) {
                    User user = (User) objIs.readObject();
                    User userX = (User) getUser(user.getId());

                    Logger.info("User credentials received from client.");

                    if (user.getPassword().equals(userX.getPassword())) {
                        //this means the user credentials match what is in the database
                        objOs.writeObject(true);
                        Logger.info("Employee logged in successfully. User ID:" + user.getId());
                    } else {
                        objOs.writeObject(false);
                        Logger.info("Employee failed to log in. User ID:" + user.getId());
                    }
                }


            } catch (IOException e) {
                Logger.error(e);
                return;
            } catch (ClassNotFoundException e) {
                Logger.error(e);
                return;
            } catch (NullPointerException e) {
                Logger.error(e);
                return;
            } catch (Exception e) {
                Logger.error(e);
                return;
            }
//                closeConnection();
        }
    }

}
