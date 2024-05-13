package com.niqactivate.service;

import com.niqactivate.model.Product;
import com.niqactivate.model.ProductMetadata;
import com.niqactivate.model.ShopperProductList;

import java.util.List;

public interface ProductService {
    void saveProductList(ShopperProductList shopperProductList, List<ProductMetadata> product);
    List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit);
}