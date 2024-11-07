package domain;

import javax.persistence.*;
import java.io.Serializable;

//annotation for the user class
@Entity
@Table(name = "user") //table name in the database
public class User implements Serializable {
    @Id
    @Column(name = "userId")
    private String id;
    @Column(name = "passwordHash")
    protected String passwordHash;
    public User(){
        id=null;
        passwordHash = null;
    }

    public User(String id, String passwordHash) {
        this.id = id;
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return this.passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
