package ejb;

import entities.Device;
import entities.Subscription;
import entities.Users;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Named(value="devicebean")
@ViewScoped
public class DeviceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Dao dao;

    private List<Device> data;
    private String text;
    private String message;
    private String tags;
    private String url;
    private String available;
    private String online;

    public Device getD() {
        return d;
    }

    public void setD(Device d) {
        this.d = d;
    }

    private Device d;
    private String user;
    private String t;
    private Users dude;

    public DeviceBean(){
        d = new Device();
        d.setName("None");
        d.setTags("Nothing");
        d.setUrl("https://localnope:0000");
        d.setOnline(false);
        d.setAvailable(false);
        Users u = new Users();
        u.setUsername("No one");
        d.setUser(u);
    }

    public void init(){
        data = new ArrayList<>();
        data.addAll(this.dao.getAllDevices());
        setText(d.getName());
        setMessage("Name: " + d.getName());
        setTags("Tags: " + d.getTags());
        setUrl("URL: " + d.getUrl());
        setUser("Owner: " + d.getUser().getUsername());
        if(d.isOnline()){
            setOnline("Online");
        }else{
            setOnline("Offline");
        }
        if(d.isAvailable()){
            setAvailable("Available");
        }else{
            setAvailable("Unavailable");
        }
    }

    public void valueChanged(ValueChangeEvent e){
            t = (String) e.getNewValue();
            for(Device device : data){
                if(device.getName().equals(t)){
                    HttpSession session = SessionUtils.getSession();
                    session.setAttribute(Constants.CHOSEN, t);
                    d = device;
                    return;
                }
            }
    }

    public void subscribe() throws JMSException, NamingException {
        String name = SessionUtils.getUserName();
        List<Users> list = new ArrayList<>();
        list.addAll(this.dao.getAllUsers());
        for(Users u : list){
            if(u.getUsername().equals(name)){
                dude = u;
            }
        }
        Subscription sub = new Subscription();
        sub.setVerified(false);
        sub.setSubscribed(d);
        sub.setUser(dude);
        dude.addSubscribed(sub);
        d.addSubscription(sub);

        this.dao.persistSubscription(sub);
        this.dao.updateUser(dude);
        this.dao.updateDevice(d);
    }

    public String getAvailable() {
        return available;
    }

    public String getOnline() {
        return online;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTags() {
        return tags;

    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String isAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String isOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }


    public List<Device> getData(){
        return data;
    }

    public String getText(){
        return text;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String s) {
        message = s;
    }

    public void setText(String name) {
        text = name;
    }

}
