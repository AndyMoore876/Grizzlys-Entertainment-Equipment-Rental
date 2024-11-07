package view;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.formdev.flatlaf.FlatLightLaf;
import frame.*;

import javax.swing.*;

import client.Client;


public class DashboardFrame extends JFrame{
	private static Client client;
	public static JDesktopPane desktop;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem rentalRequest, equipment, transaction,  customer;
    private JMenuItem menuItemSave;
    public DashboardFrame(Client client) throws HeadlessException {
        FlatLightLaf.setup();
    	DashboardFrame.client = client;
        initializeComponents();
        addMenuItemsToMenu();
        addMenusToMenuBar();
        addComponentsToWindow();
        registerListeners();
        setWindowProperties();
    }

    private void initializeComponents(){
        desktop = new JDesktopPane();
        menuBar = new JMenuBar();

        menu = new JMenu("Employee Menu");
        menu.setMnemonic(KeyEvent.VK_A);

        rentalRequest = new JMenuItem("Rental Request");
        equipment = new JMenuItem("Equipment");
        transaction = new JMenuItem("Transaction");
        customer = new JMenuItem("Customer");

        menuItemSave = new JMenuItem("Save");
        menuItemSave.setToolTipText("Saves the active document");
    }

    private void addMenusToMenuBar(){
        menuBar.add(menu);
    }

    private void addMenuItemsToMenu(){
        menu.add(customer);
        menu.add(transaction);
        menu.add(equipment);
        menu.add(rentalRequest);
    }
    private void addComponentsToWindow(){
        this.add(desktop);
    }

    private void setWindowProperties(){
        this.setJMenuBar(menuBar);
        this.setSize(1020, 700);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Grizzly's Entertainment Equipment Rental - Employee Dashboard");
    }

    private void registerListeners(){
        rentalRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktop.add(new RentalRequestsFrame(client)).setVisible(true);
            }
        });

        equipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktop.add(new EquipmentsFrame(client)).setVisible(true);
            }
        });
        
        transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktop.add(new TransactionsFrame(client)).setVisible(true);
            }
        });

        customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktop.add(new CustomerFrame(client)).setVisible(true);
            }
        });
    }
}