package ejb;

import entities.Device;
import entities.Feedback;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Named(value="feedbackbean")
@ViewScoped
public class FeedbackBean implements Serializable {

        private static final long serialVersionUID = 1L;

        @EJB
        private Dao dao;

        private List<Feedback> feedbackList;
        private List<Feedback> feedbackList2;
        private String view;
        private String feed;
        private Feedback d;
        private String user;
        private String t;
        private Device device;

        public FeedbackBean(){
            d = new Feedback();
            d.setText("None");
            d.setAuthor("no one");
            device = new Device();
            d.setTarget(device);
        }

    public void init(){
            feedbackList2 = new ArrayList<>();
            feedbackList = new ArrayList<>();
            feedbackList2.addAll(this.dao.getAllFeedbacks());
            for(Feedback f : feedbackList2){
                if(f.getTarget().getName().equals(SessionUtils.getChosen())){
                    feedbackList.add(f);
                }
        }
            setView(d.getAuthor());
            setFeed(d.getText());
            setUser("Author: " + d.getAuthor());
        }


    public void valueChanged(ValueChangeEvent e){
        t = (String) e.getNewValue();
        for(Feedback feedback : feedbackList){
            if(feedback.getAuthor().equals(t)){
                d = feedback;
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
    public String getView() {
        return view;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }


    public void setView(String name) {
        view = name;
    }

    public List<Feedback> getData() {
        return feedbackList;
    }

    public void setData(List<Feedback> data) {
        this.feedbackList = data;
    }

    }


