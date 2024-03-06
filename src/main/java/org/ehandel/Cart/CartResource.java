package org.ehandel.Cart;

import java.util.ArrayList;
import java.util.List;

import org.ehandel.Product.Product;
import org.ehandel.Product.ProductService;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
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

@Path("/order")

public class CartResource {

	@Inject
    ProductService productService;
    EntityManager em;

	public List <Product> orderList = new ArrayList<>();

	//Hämta/visa orderlista
	@GET
	public List<Product> getOrderList() {
		
		return orderList;
	}

	//Lägga till produkt
	@POST
	@Path("/add/{id}")
	public List<Product> addProduct(@PathParam ("id") Long id) {
		
		Product product = productService.find(id);
		orderList.add(product);

		return orderList;
	}

	//Deleta produkter

	@DELETE
	@Path("/remove/{id}")
	public List<Product> deleteProduct(@PathParam("id") Long id) {
		
		orderList.removeIf(product -> product.getId().equals(id));
		
		return orderList;
	}

	//Ändra antal och pris

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
}
