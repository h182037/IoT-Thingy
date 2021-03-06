package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name="users")
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Device> ownedDevices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Subscription> subscriptionList;

    private String username;

    private String password;

    public static final String FIND_ALL = "Users.findAll";

    public Users(){
        this.ownedDevices = new ArrayList<>();
        this.subscriptionList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void addOwned(Device d){
        this.ownedDevices.add(d);
    }

    public List<Device> getOwnedDevices() {
        return ownedDevices;
    }

    public void setOwnedDevices(List<Device> ownedDevices) {
        this.ownedDevices = ownedDevices;
    }

    public void addSubscribed(Subscription s){
        this.subscriptionList.add(s);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
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
/*
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
*/

/*
    public void setOwnedDevices(List<Device> ownedDevices) {
        this.ownedDevices = ownedDevices;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }
*/

}