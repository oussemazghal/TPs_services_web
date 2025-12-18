package myapps.ProdcutWSS;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("trains")
public class TrainResource {

    BookTrainBD bd = new BookTrainBD();

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Train> getTrains() {
        return BookTrainBD.getTrains();
    }

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "OK";
    }

    @GET
    @Path("/{num}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Train getTrain(@PathParam("num") String numTrain) {
        for (Train t : BookTrainBD.getTrains()) {
            if (t.getNumTrain().equals(numTrain)) {
                return t;
            }
        }
        return null;
    }

    @GET
    @Path("/search/{dep}/{arr}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Train> searchTrainByCriteria(@PathParam("dep") String dep,
                              @PathParam("arr") String arr) {
        List<Train> result = new ArrayList<>();
        for (Train t : BookTrainBD.getTrains()) {
            if (t.getVilleDepart().equalsIgnoreCase(dep) &&
                t.getVilleArrivee().equalsIgnoreCase(arr)) {
                result.add(t);
            }
        }
        return result;
    }
    @POST
    @Path("add1/{num}/")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void CreateTrain(@PathParam("num") String num , @P) {
        BookTrainBD.getTrains().add(t);
    }
    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createTrainObject(Train t) {
        BookTrainBD.getTrains().add(t);
    }
    

    @PUT
    @Path("modify/{num}/{heure}")
    public void modify(@PathParam("num") String num,
                       @PathParam("heure") int heureDepart) {
        for (Train t : BookTrainBD.getTrains()) {
            if (t.getNumTrain().equals(num)) {
                t.setHeureDepart(heureDepart);
                break;
            }
        }
    }

    @DELETE
    @Path("/{num}")
    public void remove(@PathParam("num") String num) {
        BookTrainBD.getTrains().removeIf(t -> t.getNumTrain().equals(num));
    }
}
