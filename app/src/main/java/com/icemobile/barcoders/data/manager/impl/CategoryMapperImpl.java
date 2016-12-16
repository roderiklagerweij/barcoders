package com.icemobile.barcoders.data.manager.impl;

import android.text.TextUtils;

import com.icemobile.barcoders.data.domain.Category;
import com.icemobile.barcoders.data.domain.Poetic;
import com.icemobile.barcoders.data.domain.PoeticWrapper;
import com.icemobile.barcoders.data.domain.Product;
import com.icemobile.barcoders.data.domain.SentenceType;
import com.icemobile.barcoders.data.manager.CategoryMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Product getProduct(String barcode) {
        if(!TextUtils.isEmpty(barcode)) {
            for(Product product : Product.values()) {
                if(product.getBarcode().equals(barcode)) {
                    return product;
                }
            }
        }
        return null;
    }

    private List<Product> getCategoryProducts(Category category) {
        switch (category) {
            case DRINK:
                return Arrays.asList(Product.MILK);
            default:
                return null;
        }
    }

    @Override
    public List<PoeticWrapper> getPoetic(Product product1, Product product2, Product product3) {
        List<PoeticWrapper> poetics = new ArrayList<>();

        poetics.add(new PoeticWrapper(product1, SentenceType.ANALYZE));
        poetics.add(new PoeticWrapper(product2, SentenceType.ADVICE));
        poetics.add(new PoeticWrapper(product3, SentenceType.FUTURE));

        Category preferredCategory = null;
        int matches = 0;
        for(Category category : Category.values()) {
            int counter = 0;
            final List<Product> categoryProducts = getCategoryProducts(category);
            if (categoryProducts.contains(product1)) {
                counter++;
            }
            if (categoryProducts.contains(product2)) {
                counter++;
            }
            if (categoryProducts.contains(product3)) {
                counter++;
            }

            if(counter > matches) {
                matches = counter;
                preferredCategory = category;
            }
        }
        poetics.add(new PoeticWrapper(preferredCategory, SentenceType.GROUP));

        return poetics;
    }

    @Override
    public String getSentence(Poetic poetic, SentenceType sentenceType) {
        // TODO get from examples
        return null;
    }
}
