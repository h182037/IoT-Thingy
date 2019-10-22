package ejb;

import entities.Device;
import entities.Feedback;
import entities.Users;

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
import entities.Device;

@ManagedBean
@Named(value="feedbackbean")
@ViewScoped
public class FeedbackBean implements Serializable {

        private static final long serialVersionUID = 1L;

        @EJB
        private Dao dao;

        private List<Feedback> data;
        private String text;
        private String feed;

    public String getText() {
        return text;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    private Feedback d;
        private String user;


        public FeedbackBean(){
            d = new Feedback();
            d.setText("None");
            d.setAuthor("no one");
        }

    public List<Feedback> getData() {
        return data;
    }

    public void setData(List<Feedback> data) {
        this.data = data;
    }

    public void init(){
            data = new ArrayList<>();
            data.addAll(this.dao.getAllFeedbacks());
            setText(d.getAuthor());
            setFeed(d.getText());
            setUser("Author: " + d.getAuthor());
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }


        public void setText(String name) {
            text = name;
        }
    public void valueChanged(ValueChangeEvent e){
        String t = (String) e.getNewValue();
        for(Feedback feedback : data){
            if(feedback.getAuthor().equals(t)){
                d = feedback;
            }
        }
        d.setText(t);
        d.setAuthor(t + " selected.");
    }

    }


