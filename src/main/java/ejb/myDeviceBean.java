package ejb;

import entities.Device;
import entities.Users;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
@Named(value="mydevicebean")
@ViewScoped
public class myDeviceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Dao dao;

    private List<Device> data2;
    private List<Device> data;
    private String text;
    private String message;
    private String tags;
    private String url;
    private String available;
    private String online;
    private Device d;
    private String user;
    private String t;
    private String name;

    public myDeviceBean(){
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
        name = SessionUtils.getUserName();
        data2 = new ArrayList<>();
        data = new ArrayList<>();
        data2.addAll(this.dao.getAllDevices());
        for(Device device : data2){
            if(device.getUser().getUsername().equals(name)){
                data.add(device);
            }
        }
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

    public void deleteDevice() throws JMSException, NamingException {
        this.dao.deleteDevice(d);
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
