package view;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.*;
import java.sql.*;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.formdev.flatlaf.FlatLightLaf;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.Equipment;
import domain.Message;
import org.jdatepicker.impl.*;
import client.Client; // Import the Client class


public class App extends JFrame {
    private JFrame frame;
    public static JDesktopPane desktopPane;
    private static Client client; // Add a Client member

    public static JMenuBar menuBar;

    public App() {
        FlatLightLaf.setup();
    	
        // Initialize the Client object
        menuBar = createMenuBar();
        client = new Client();
        desktopPane = new JDesktopPane();
        desktopPane.add(new LoginFrame(client));
//        this.menuBar.setEnabled(false);
        for (Component component : menuBar.getComponents()){
            component.setEnabled(false);
        }
        this.setTitle("Grizzly's Entertainment Equipment Rental - Customer Dashboard");
        this.setJMenuBar(menuBar);
        this.add(desktopPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }
    
    // Methods loadEquipmentFromDatabase, createMenuBar, and createEquipmentFrame remain unchanged
//    private void loadEquipmentFromDatabase() {
//        // Request equipment list from server
//        client.sendAction("Get Equipment List");
//        client.receiveResponse();
//
//        // Handle the response
//        List<Equipment> equipments = client.getEquipmentsList();
//        equipmentCosts.clear();
//        for (Equipment equipment : equipments) {
//        	equipmentCosts.put(equipment.getEquipmentName(), (int) equipment.getCostPerDay());
//
//        }
//    }


    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setEnabled(false);
        // Create Equipment Menu Item
        JMenuItem viewEquipmentItem = new JMenuItem("Equipments");
        viewEquipmentItem.setMnemonic(KeyEvent.VK_E); // Pressing Alt+E will highlight this menu item
        viewEquipmentItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK)); // Ctrl+1 will open the equipment frame
        viewEquipmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.add(new EquipmentsFrame(client)).setVisible(true);
            }
        });
        menuBar.add(viewEquipmentItem);

        // Create Transactions Menu Item
        JMenuItem viewTransactionsItem = new JMenuItem("Transactions");
        viewTransactionsItem.setMnemonic(KeyEvent.VK_T); // Pressing Alt+T will highlight this menu item
        viewTransactionsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK)); // Ctrl+2 will open the transactions frame
        viewTransactionsItem.addActionListener(e -> desktopPane.add(new TransactionsFrame(client)).setVisible(true));
        menuBar.add(viewTransactionsItem);

        // Create Rental Request Menu Item
        JMenuItem rentalRequestItem = new JMenuItem("Rental Requests");
        rentalRequestItem.setMnemonic(KeyEvent.VK_I);
        rentalRequestItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
        rentalRequestItem.addActionListener(e -> desktopPane.add(new RentalRequestsFrame(client)).setVisible(true));
        menuBar.add(rentalRequestItem);

        // Create Message Menu Item
        JMenuItem sendMessageItem = new JMenuItem("Messages");
        sendMessageItem.setMnemonic(KeyEvent.VK_S); // Pressing Alt+S will highlight this menu item
        sendMessageItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK)); // Ctrl+4 will open the message frame
        sendMessageItem.addActionListener(e -> desktopPane.add(new MessagesFrame(client, client.getLoggedInCustomer().getCustomerId())).setVisible(true));
        menuBar.add(sendMessageItem);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
