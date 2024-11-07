package view;

import javax.swing.*;

import client.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;


public class LoginFrame extends JFrame{
	private static Client client = new Client();
    private Logger Logger = LogManager.getLogger(LoginFrame.class);
	private JLabel label1, label2;
    private JTextField textField1;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Employee Login");
        setSize(1020, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20, 5));

        label1 = new JLabel("Employee ID:");
        panel.add(label1);

        textField1 = new JTextField();
        panel.add(textField1);

        label2 = new JLabel("Password:");
        panel.add(label2);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginButton) {
            String employeeId = textField1.getText();
            String password = new String(passwordField.getPassword());

            // Send employeeId and password to the server for authentication
            // If authentication is successful, open the dashboard
            DashboardFrame dashboardFrame = new DashboardFrame();
            dashboardFrame.setVisible(true);
            dispose();
        }*/
        loginButton.setMnemonic(KeyEvent.VK_C);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                String userID = textField1.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                if(userID.equals("") || pwd.equals("") ) {
                    Logger.error("Login Failed.");
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Unsuccessful. Please try again.", "Employee Login", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    client.sendAction("Login Employee");
                    client.sendLoginInfo(userID, pwd);
                    client.receiveResponse();
                    if (client.getLoggedInEmployee() == null) {
                        Logger.info("Login Failed.");
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login Unsuccessful. Please try again.", "Employee Login", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Logger.info("Login Successful.");
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login successful", "Employee Login", JOptionPane.INFORMATION_MESSAGE);
                        DashboardFrame dashboardFrame = new DashboardFrame(client);
                        dashboardFrame.setVisible(true);
                        dispose();
                    }
                }
			}
		});
	}
	
}	
	


