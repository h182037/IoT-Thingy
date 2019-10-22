package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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

    private boolean verified;

    public static final String FIND_ALL = "Users.findAll";

    public Subscription() {
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