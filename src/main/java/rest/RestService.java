package rest;


import ejb.Controller;
import ejb.Dao;
import entities.Device;
import entities.Subscription;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/devices")
@Stateless
public class RestService {

    @EJB
    private Dao dao;

    @GET
    public Response getDevices(){
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(this.dao.getAllDevices());
        Collections.reverse(devices);
        return Response.ok(devices).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDeviceById(@PathParam("id") String id) {
        long longid = 0;
        Device device = null;
        try{
            longid = Integer.parseInt(id);
            device = this.dao.getDevice(longid);
        }catch(NumberFormatException ex){}

        if (device == null)
            throw new NotFoundException();
        return Response.ok(device).build();
    }
        @GET
        @Path("/{id}/subscriptions")
        @Produces("application/json")
        public Response getDeviceRegistrations(@PathParam("id") String id){
            long longid = 0;
            Device device = null;
            try{
                longid = Integer.parseInt(id);
                device = this.dao.getDevice(longid);
            }catch(NumberFormatException ex){}

            List<Subscription> subs = device.getSubscriptionList();
            Collections.reverse(subs);
            return Response.ok(subs).build();

        }



}
