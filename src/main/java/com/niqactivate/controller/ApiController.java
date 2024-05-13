package com.niqactivate.controller;

import com.niqactivate.model.Product;
import com.niqactivate.model.ProductInputRequest;
import com.niqactivate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProductsByShopper(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit
                                             ) {
        // Call service method to get products by shopper with filters
        return productService.getProductsByShopper(shopperId, category, brand, limit);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProductList(@RequestBody ProductInputRequest request){
        try{
            productService.saveProductList(request.getShopperProductList(), request.getProductList());
            return new ResponseEntity<>("Product save successfuly", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Failed to Save "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
