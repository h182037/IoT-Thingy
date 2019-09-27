package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@XmlRootElement
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //Create elements ids automatically, incremented 1 by 1
    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private Long id;

    private String username;

    private String password;

    @OneToMany
    private List<Device> ownedDevices;

    @OneToMany
    private List<Subscription> subscriptions;

    @OneToMany
    private List<Feedback> feedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Device> getOwnedDevices() {
        return ownedDevices;
    }

    public void addOwnedDevice(Device d) {
        this.ownedDevices.add(d);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscriptions(Subscription s) {
        this.subscriptions.add(s);
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void addFeedback(Feedback f) {
        this.feedback.add(f);
    }

    public static final String FIND_ALL = "User.findAll";

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ownedDevices = new ArrayList<Device>();
        this.feedback = new ArrayList<Feedback>();
        this.subscriptions = new ArrayList<Subscription>();
    }
}