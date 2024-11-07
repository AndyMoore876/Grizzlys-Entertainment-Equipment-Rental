package client.Driver;

//used to test the client is conencting to the server
import client.Client;
import domain.Employee;
import domain.User;

public class Driver {
    public static void main(String [] args){
        Client test = new Client();
        User user = new User("Test0021","passwuiuord");

        test.sendAction("Login Employee");
        test.sendLoginInfo("Test0021","passwuiuord");
        test.receiveResponse();
        Employee andy =  test.getLoggedInEmployee();

        System.out.println(andy.toString());

        Employee taylor = new Employee("Chamarie", "Taylor","test");

        test.sendAction("Save Employee");
        test.sendObject(taylor);
        test.receiveResponse();

//        System.out.println(andy.getPassword());
//        test.sendObject(andy);

    }
}
