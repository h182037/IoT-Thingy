package ejb;

import entities.Device;
import entities.Subscription;
import entities.Users;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Named(value="mysubbean")
@ViewScoped
public class mySubBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Dao dao;

    private List<Subscription> sublist;
    private List<Subscription> sublist2;
    private String url;
    private Subscription d;
    private String user;
    private String t;
    private String verified;
    private String view;
    private String sub;


    public mySubBean(){
        d = new Subscription();
        d.setVerified(false);
        Users u = new Users();
        u.setUsername("No One");
        d.setUser(u);
        Device d1 = new Device();
        d.setSubscribed(d1);
    }

    public void init(){
        sublist2 = new ArrayList<>();
        sublist = new ArrayList<>();
        sublist2.addAll(this.dao.getAllSubs());
        for(Subscription s : sublist2){
            if(s.getUser().getUsername().equals(SessionUtils.getUserName())){
                sublist.add(s);
            }
        }
        setView("Subscriber: " + d.getUser().getUsername());
        setSub("Device: " + d.getSubscribed().getName());
        if(d.isVerified()) {
            setVerified("Verified: True");
        }else{
            setVerified("Verified: False");
        }
    }

    public void valueChanged(ValueChangeEvent e){
        t = (String) e.getNewValue();
        for(Subscription s : sublist){
            if(s.getUser().getUsername().equals(t)){
                d = s;
                return;
            }
        }
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Subscription> getSublist() {
        return sublist;
    }

    public void setSublist(List<Subscription> sublist) {
        this.sublist = sublist;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getView() {
        return view;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setView(String view) {
        this.view = view;
    }
}