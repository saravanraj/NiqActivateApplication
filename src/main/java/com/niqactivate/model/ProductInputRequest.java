package com.niqactivate.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductInputRequest {
    private ShopperProductList shopperProductList;
    private List<ProductMetadata> productList;
}
