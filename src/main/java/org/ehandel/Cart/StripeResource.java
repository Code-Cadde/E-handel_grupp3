package org.ehandel.Cart;

import java.util.ArrayList; // Importera ArrayList från java.util
import java.util.List; // Importera List från java.util

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.model.checkout.Session;
import com.stripe.param.ProductListParams;
import com.stripe.param.checkout.SessionCreateParams;

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

    // Ange din Stripe API-nyckel
    // private static final String STRIPE_API_KEY = "sk_test_51OoMvCJedaXYqji2pwxGcUaTRy7KN1uNwgsPCu6ZdvW5tXUKdbll2464WXz0gBkSK3D87VoHEjtAJvUt2qr5Ucrx00LFr8vsd3";

    // @GET
    // @Path("/v1/products/session")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response startCheckoutSession(List<Product> orderList) {

    //     try {
    //         // Skapa en tom lista för line items
    //         List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

    //         // Lägg till line items baserat på din orderList (ersätt med din faktiska implementering)
    //         for (Product product : orderList) {
    //             lineItems.add(
    //                     SessionCreateParams.LineItem.builder()
    //                             .setPrice(product.getStripePriceId()) // Använd pris-id för produkten från Stripe
    //                             .setQuantity(product.getQuantity())
    //                             .build()
    //             );
    //         }

    //         // Skapa parametrarna för sessionen
    //         SessionCreateParams params =
    //                 SessionCreateParams.builder()
    //                         .setSuccessUrl("https://example.com/success")
    //                         .setCancelUrl("https://example.com/cancel")
    //                         .addAllLineItem(lineItems)
    //                         .setMode(SessionCreateParams.Mode.PAYMENT)
    //                         .build();

    //         // Ange din Stripe API-nyckel
    //         Stripe.apiKey = STRIPE_API_KEY;

    //         // Skapa checkout-sessionen med Stripe
    //         Session session = Session.create(params);

    //         // Returnera sessionen som JSON-svar
    //         return Response.ok(session.toJson()).build();

    //     } catch (StripeException e) {
    //         e.printStackTrace();
    //         return Response.serverError().build();
    //     }
    // }
}
