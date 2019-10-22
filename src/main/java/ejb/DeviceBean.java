package ejb;

import entities.Device;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
@Named(value="devicebean")
@SessionScoped
public class DeviceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Dao dao;

    private List<Device> data;
    private String text;
    private String message;
    private String description;

    public DeviceBean(){
        data = new ArrayList<>();
       // data.addAll(this.dao.getAllDevices());
        if(data.isEmpty()){
            Device d = new Device();
            d.setTags("vann og sånt.");
            d.setAvailable(false);
            d.setOnline(false);
            d.setUrl("aopspoamc");
            d.setName("heppatittentei");
            d.setDescription("sykt bra");
            data.add(d);
        }
        Device d = data.get(0);
        setText(d.getName());
        setMessage(d.getName());
        setDescription(d.getDescription());
    }

    public void valueChanged(ValueChangeEvent e){
        String t = (String) e.getNewValue();
        setMessage(t);
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

    public void setDescription(String d1) { description = d1;}

    public String getDescription() {
        return description;
    }
}
