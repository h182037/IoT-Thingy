package entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@Table(name="device")
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private Long id;

    @JsonbTransient
    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private Users user;

    private String name;

    private String url;

    @OneToMany(mappedBy = "subscribed", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Subscription> subscriptionList;

    @OneToMany(mappedBy = "target", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;

    private String tags;

    private boolean online;

    private boolean available;

    public static final String FIND_ALL = "Device.findAll";

    public Device() {
        this.subscriptionList = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
    }

    public static boolean getTopic() {
        return true;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void addFeedback(Feedback f){
        this.feedbackList.add(f);
    }

    public void addSubscription(Subscription s){
        this.subscriptionList.add(s);
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }


    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*
            public List<Tag> getTags() {
                return tags;
            }

            public void setTags(List<Tag> tags) {
                this.tags = tags;
            }

            public List<Feedback> getFeedback() {
                return feedback;
            }

            public void setFeedback(List<Feedback> feedback) {
                this.feedback = feedback;
            }

            public Users getOwner() {
                return owner;
            }

            public void setOwner(Users owner) {
                this.owner = owner;
            }
        */
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return this.name;
    }

}