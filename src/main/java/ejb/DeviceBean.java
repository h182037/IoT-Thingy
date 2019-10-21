package ejb;

import entities.Device;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    private Device d;
    private String t;
    private boolean chosen;
    public DeviceBean(){
    }

    public void init(){
        data = new ArrayList<>();
        data.addAll(this.dao.getAllDevices());
        d = data.get(0);
        setText(d.getName());
        setMessage(d.getName() + " selected.");
    }

    public void valueChanged(ValueChangeEvent e){
            t = (String) e.getNewValue();
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
