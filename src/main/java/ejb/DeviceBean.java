package ejb;

import entities.Device;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
@Named(value="devicebean")
@SessionScoped
public class DeviceBean implements Serializable {

    @EJB
    private Dao dao;

    private List<Device> data;
    private String text;
    private String message;

    public DeviceBean(){
        dao = new Dao();
        data = new ArrayList<>();
        Device d = new Device();
        d.setTags("vann og sånt.");
        d.setAvailable(false);
        d.setOnline(false);
        d.setUrl("aopspoamc");
        d.setName("heppatittentei");
        data.add(d);

        Device d2 = new Device();
        d2.setTags("heitur pottur");
        d2.setAvailable(false);
        d2.setOnline(false);
        d2.setUrl("aopspoasdonvm");
        d2.setName("The L33tles");
        data.add(d2);

        setText(d.getName());
        setMessage(d.getName() + " selected.");
    }

    public void valueChanged(ValueChangeEvent e){
        String t = (String) e.getNewValue();
        setMessage(t + " selected.");
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
