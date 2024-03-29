package org.ehandel.Cart;

import java.util.ArrayList;
import java.util.List;
import org.ehandel.Product.Product;
import org.ehandel.Product.ProductService;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Path("/order")
public class CartResource {

    @Inject
    ProductService productService;
    EntityManager em;

    public List<Product> orderList = new ArrayList<>();

    @GET
    public List<Product> getOrderList() {
        return orderList;
    }

    @POST
    @Path("/add/{id}")
    public List<Product> addProduct(@PathParam("id") Long id) {
        Product product = productService.find(id);
        orderList.add(product);
        return orderList;
    }

    @DELETE
    @Path("/remove/{id}")
    public List<Product> deleteProduct(@PathParam("id") Long id) {
        orderList.removeIf(product -> product.getId().equals(id));
        return orderList;
    }

    
    // Tar bort hela ordern
    @DELETE
    @Path("/clear")
    public Response clearOrder() {
    orderList.clear(); // Tar bort alla produkterna i orderList
    return Response.ok().build();
    }


    @PATCH
    @Path("/edit/{id}")
    public List<Product> editProduct(@PathParam("id") Long id, @QueryParam("quantity") int newQuantity) {
        for (Product product : orderList) {
            if (product.getId().equals(id)) {
                product.setQuantity(newQuantity);
                product.setTotalPrice(product.getPricePerItem() * newQuantity);
                break;
            }
        }
        return orderList;
    }

    @POST
    @Path("/purchase")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startCheckout() {
        try {
            if (orderList.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Order list is empty").build();
            }

            StripeApi stripeApi = new StripeApi();
            Stripe.apiKey = stripeApi.getStripeApiKey();
            
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
			for (Product product : orderList) {
				lineItems.add(

					SessionCreateParams.LineItem.builder()
						.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
								.setCurrency("sek")
								.setProductData(
									SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName(product.getTaste())
                                        
										.build()
								)
                                
								.setUnitAmount((long) (product.getTotalPrice() /product.getQuantity() * 100 )) // Använd totalpriset i stället för enhetspriset
								.build()
						)
						.setQuantity((long) product.getQuantity())
						.setAdjustableQuantity(
          SessionCreateParams.LineItem.AdjustableQuantity.builder()
            .setEnabled(true)
            .setMinimum(1L)
            .setMaximum(999L)
            .build()
        )
						.build()

				);
			}
			System.out.println("Line items: " + lineItems);
            
            String successUrl = "http://127.0.0.1:5500/index.html?paymentSuccess=true";
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setSuccessUrl(successUrl)
                            .setCancelUrl("https://example.com/cancel")
                            .addAllLineItem(lineItems)
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .build();

            Session session = Session.create(params);
            orderList.clear();

            

            return Response.ok(session.toJson()).build();

        } catch (StripeException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
