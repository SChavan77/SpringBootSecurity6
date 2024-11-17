package com.spring.security.controller;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private record Product(Integer productId, String productName, double price){}
    List<Product> products =new ArrayList<>(
            List.of(new Product(1,"iPhone",999.9),
                    new Product(2,"Macbook",1499.9),
                    new Product(3,"AirPod",79.9)
            ));

    @GetMapping
    public List<Product> getProducts(){
        return products;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){ //CSRF concept comes
        products.add(product);
        return product;
    }

}
