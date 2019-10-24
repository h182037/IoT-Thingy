package ejb;

import entities.Device;
import entities.Users;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import ejb.SessionController;


@ManagedBean
    @Named(value="createdevicebean")
    @ViewScoped
    public class CreateDeviceBean implements Serializable {

        private static final long serialVersionUID = 1L;

        @EJB
        private Dao dao;

        private List<Device> data;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    private String deviceName;
        private String message;
        private String tags;
        private String url;
        private boolean available;
        private boolean online;
        private Device d;
        private String user;
        private String t;
        private int usersId;
        private Users dis;
        public CreateDeviceBean(){
        }


            public String CreateDevice() throws JMSException, NamingException, IOException {
                String name = SessionUtils.getUserName();
                List<Users> usersList = this.dao.getAllUsers();

                for (Users u : usersList) {
                    if (u.getUsername().equals(name)) {
                        dis = u;
                    }


                }
                Device device = new Device();
                device.setAvailable(available);
                device.setName(deviceName);
                device.setOnline(online);
                device.setTags(tags);
                device.setUrl(url);
                device.setUser(dis);
                this.dao.persistDevice(device);
                HttpSession session = SessionUtils.getSession();
                if (session.getAttribute(Constants.USERNAME)==null) {
                    SessionUtils.getResponse().sendRedirect(Constants.LOGIN + ".xhtml");
                }
                return Constants.OWNED;            }

        public boolean getAvailable() {
            return available;
        }

        public boolean getOnline() {
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

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }


        public List<Device> getData(){
            return data;
        }



        public String getMessage(){
            return message;
        }

        public void setMessage(String s) {
            message = s;
        }



    }

