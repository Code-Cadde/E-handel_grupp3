package org.ehandel.Cart;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.param.ProductListParams;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/stripe")
public class StripeResource {

    @GET
    @Path("/v1/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response startCheckout() {

        // Ange din Stripe API-nyckel
        Stripe.apiKey = "sk_test_51OoMvCJedaXYqji2pwxGcUaTRy7KN1uNwgsPCu6ZdvW5tXUKdbll2464WXz0gBkSK3D87VoHEjtAJvUt2qr5Ucrx00LFr8vsd3";

        try {
            // Skapa parametrar för att begränsa antalet produkter som hämtas
            ProductListParams params = ProductListParams.builder().setLimit(3L).build();

            // Hämta en samling av produkter från Stripes API
            ProductCollection products = Product.list(params);

            // Returnera produktsamlingen som JSON-svar
            return Response.ok(products.toJson()).build();
        } catch (StripeException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
