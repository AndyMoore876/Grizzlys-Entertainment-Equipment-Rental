import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FileMenu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem loginItem = new JMenuItem("Login");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem transactionsItem = new JMenuItem("Transactions");
        JMenuItem equipmentItem = new JMenuItem("Equipment");
        JMenuItem customerItem = new JMenuItem("Customer");
        JMenuItem messageItem = new JMenuItem("Message");
        JMenuItem rentalRequestItem = new JMenuItem("Rental Request");
        JMenuItem eventItem = new JMenuItem("Event");

        fileMenu.add(loginItem);
        fileMenu.add(logoutItem);
        fileMenu.add(transactionsItem);
        fileMenu.add(equipmentItem);
        fileMenu.add(customerItem);
        fileMenu.add(messageItem);
        fileMenu.add(rentalRequestItem);
        fileMenu.add(eventItem);

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        JDesktopPane desktopPane = new JDesktopPane();
        frame.setContentPane(desktopPane);

        JInternalFrame transactionsFrame = new JInternalFrame("Transactions", true, true, true, true);
        transactionsFrame.setSize(300, 200);
        transactionsFrame.setLocation(50, 50);
        desktopPane.add(transactionsFrame);

        JInternalFrame equipmentFrame = new JInternalFrame("Equipment", true, true, true, true);
        equipmentFrame.setSize(300, 200);
        equipmentFrame.setLocation(100, 100);
        desktopPane.add(equipmentFrame);

        JInternalFrame customerFrame = new JInternalFrame("Customer", true, true, true, true);
        customerFrame.setSize(300, 200);
        customerFrame.setLocation(150, 150);
        desktopPane.add(customerFrame);

        JInternalFrame messageFrame = new JInternalFrame("Message", true, true, true, true);
        messageFrame.setSize(300, 200);
        messageFrame.setLocation(200, 200);
        desktopPane.add(messageFrame);

        JInternalFrame rentalRequestFrame = new JInternalFrame("Rental Request", true, true, true, true);
        rentalRequestFrame.setSize(300, 200);
        rentalRequestFrame.setLocation(250, 250);
        desktopPane.add(rentalRequestFrame);

        JInternalFrame eventFrame = new JInternalFrame("Event", true, true, true, true);
        eventFrame.setSize(300, 200);
        eventFrame.setLocation(300, 300);
        desktopPane.add(eventFrame);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

}
