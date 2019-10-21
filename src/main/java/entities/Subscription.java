package entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;


@Entity
@XmlRootElement
@Table(name="subscription")
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private Long id;

    public Device getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Device subscribed) {
        this.subscribed = subscribed;
    }
    @JsonbTransient
    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @JsonbTransient
    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    private Device subscribed;

    private boolean verified;

    public static final String FIND_ALL = "Users.findAll";

    public Subscription() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}