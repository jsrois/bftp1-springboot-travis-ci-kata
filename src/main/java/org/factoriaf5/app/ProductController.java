package org.factoriaf5.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


class Product {
    private String name;
    private int price;
    private boolean discountHalfPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDiscountHalfPrice() {
        return discountHalfPrice;
    }

    public void setDiscountHalfPrice(boolean discountHalfPrice) {
        this.discountHalfPrice = discountHalfPrice;
    }
}

class RespuestaDeCompra {
    private String response;

    public RespuestaDeCompra(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

}

@RestController
public class ProductController {
    @PostMapping("/products/buy")
    public RespuestaDeCompra buyProducts(@RequestBody List<Product> productList) {

        int numeroDeProductos = productList.size();

        int precioTotal = 0;

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            int precio = 0;

            if (product.isDiscountHalfPrice()) {
                precio = product.getPrice()/2;
            } else {
                precio = product.getPrice();
            }

            precioTotal = precioTotal + precio;
        }

        String response = "Gracias por su compra. "+ numeroDeProductos +
                " productos por un total de "+ precioTotal +" euros";

        return new RespuestaDeCompra(response);
    }
}
