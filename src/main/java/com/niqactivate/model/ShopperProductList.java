package com.niqactivate.model;

import lombok.Data;

import java.util.List;

@Data
public class ShopperProductList {
    private String shopperId;
    private List<ProductItem> shelf;
}
