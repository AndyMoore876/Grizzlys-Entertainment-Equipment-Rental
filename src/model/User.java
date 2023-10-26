package model;



public class User {
    protected String passwordHash;
    protected Boolean loggedIn = false;

    public User(String passwordHash, Boolean loggedIn) {
        this.passwordHash = passwordHash;
        this.loggedIn = loggedIn;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    protected void logIn(){

    }
}
