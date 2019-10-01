package entities;

import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity
@XmlRootElement
@Table(name="subscription")
@NamedQueries({
        @NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s"),
        @NamedQuery(name = "Subscription.findAllVerified", query = "SELECT s FROM Subscription s WHERE s.verified=true and s.id = :deviceId")
})
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;

    //Create elements ids automatically, incremented 1 by 1
    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private Long id;
/*
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "device_id")
    private Device device;
*/
    private boolean verified;

    public static final String FIND_ALL = "User.findAll";

    public Subscription() {
    }

    public Subscription(Users user, Device device, boolean verified) {
     //   this.user = user;
     //   this.device = device;
        this.verified = verified;
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
/*
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
*/
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}