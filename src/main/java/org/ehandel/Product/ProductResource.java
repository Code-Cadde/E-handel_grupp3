package org.ehandel.Product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;
    EntityManager em;

    @GET
    public Response getProducts() {
        List<Product> products = productService.findAll();

        if (products.isEmpty()) {
            return Response.noContent().build();
        }
        
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@jakarta.ws.rs.PathParam("id") Long id) {

        Product product = productService.find(id);

        return Response.ok(product).build();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Response countProducts() {

        Long count = productService.countAll();
        return Response.ok(count).build();
    }
    @POST
    public Response createProduct(Product product) throws URISyntaxException {

        product = productService.create(product);

        URI createUri = new URI(product.getId().toString());
        
        return Response.created(createUri).entity(product).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@jakarta.ws.rs.PathParam("id") Long id) {

        productService.delete(id);
        return Response.noContent().build();

    }
}
