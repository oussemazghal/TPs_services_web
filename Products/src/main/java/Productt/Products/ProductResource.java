package Productt.Products;
import javax.ws.rs.core.MediaType;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@javax.ws.rs.Path("/products")
public class ProductResource {

    static ProductDAO dao = new ProductDAO();

    /* Tous les produits */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return dao.getProducts();
    }

    /* Produit par id */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") int id) {
        return dao.getProduct(id);
    }

    /* Recherche par nom */
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> searchByName(@QueryParam("name") String name) {
        return dao.searchProductsByName(name);
    }

    /* Création */
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Product create(Product p) {
        dao.addProduct(p);
        return p;
    }

    /* Modification */
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Product p) {
        dao.updateProduct(p);
    }

    /* Suppression */
    @DELETE
    @Path("delete/{id}")
    public void delete(@PathParam("id") int id) {
        dao.deleteProduct(id);
    }
}
