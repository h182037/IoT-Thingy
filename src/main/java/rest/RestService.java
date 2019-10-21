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
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/devices")

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
    /*

        @GET
        @Path("/{id}")
        public Response getDeviceById(@PathParam("id") String id) {
            long idInt = 0;
            try{
                idInt = Integer.parseInt(id);
            }catch(NumberFormatException ex){}

            Device device = em.find(Device.class, idInt);
            if (device == null)
                throw new NotFoundException();
            return Response.ok(device).build();
        }
        @POST
        @Path("/{id}/registrations")
        public Response getDeviceRegistrations(@PathParam("id") String id){
            long idInt = 0;
            try{
                idInt = Integer.parseInt(id);
            }catch(NumberFormatException ex){}

            Device device = em.find(Device.class, idInt);
            if (device == null)
                throw new NotFoundException();

            TypedQuery<Subscription> query =
                    em.createNamedQuery("Subscription.findAllVerified", Subscription.class).setParameter("deviceId", device.getId());
            List<Subscription> registrations = query.getResultList();


            return Response.ok(registrations).build();
        }
    @GET
    @Path("/{id}/registrations/{rid}")
    public Response getDeviceRID(@PathParam("id") String id, @PathParam("rid") String rid){
        long idInt = 0;
        try{
            idInt = Integer.parseInt(id);
        }catch(NumberFormatException ex){}

        Device device = em.find(Device.class, idInt);
        if (device == null)
            throw new NotFoundException();

        return Response.ok().build();
    }
    */


}
