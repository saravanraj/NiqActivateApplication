package com.niqactivate.service;

import com.niqactivate.model.Product;
import com.niqactivate.model.ProductItem;
import com.niqactivate.model.ProductMetadata;
import com.niqactivate.model.ShopperProductList;
import com.niqactivate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProductList(ShopperProductList shopperProductList, List<ProductMetadata> productMetadataList) {
        String shopperId = shopperProductList.getShopperId();
        List<ProductItem> productItems = shopperProductList.getShelf();
        for (int i = 0; i < productItems.size(); i++) {
            ProductItem item = productItems.get(i);
            String productId = item.getProductId();
            Optional<ProductMetadata> productMetadata = productMetadataList.stream()
                    .filter(product -> product.getProductId().equals(productId)).findFirst();
            String category="";
            String brand = "";
            if(productMetadata.isPresent()) {
                 category = productMetadata.get().getCategory();
                 brand = productMetadata.get().getBrand();
            }
            Product product = new Product(productId, shopperId, category, brand, item.getRelevancyScore());
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit) {
        // Call the repository method to fetch products by shopper with filters
        return productRepository.findByFilters(shopperId, category, brand, Pageable.ofSize(limit));
    }
}