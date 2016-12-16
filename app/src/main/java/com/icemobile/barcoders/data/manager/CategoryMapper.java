package com.icemobile.barcoders.data.manager;

import com.icemobile.barcoders.data.domain.Product;

import java.util.List;

public interface CategoryMapper {

    Product getProduct(String barcode);

    List<String> getSentences(List<Product> products);

}
