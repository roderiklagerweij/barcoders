package com.icemobile.barcoders.data.manager.impl;

import android.text.TextUtils;

import com.icemobile.barcoders.data.domain.Category;
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
            case SPICE:
                return Arrays.asList(Product.MUSTARD, Product.NOODLES, Product.PEPPER, Product.COFFEE);
            case MORNING:
                return Arrays.asList(Product.MILK, Product.CORNFLAKES, Product.PEANUTBUTTER, Product.NUTELLA, Product.COFFEE);
            case SUGAR:
                return Arrays.asList(Product.COKE, Product.CORNFLAKES, Product.NUTELLA);
            case DRINKS:
                return Arrays.asList(Product.MILK, Product.BEER, Product.COKE, Product.COFFEE);
            case ENJOY:
                return Arrays.asList(Product.BEER, Product.NUTELLA);
            case SAVORY:
                return Arrays.asList(Product.MUSTARD, Product.NOODLES, Product.PEPPER);
            case CREAMY:
                return Arrays.asList(Product.PEANUTBUTTER, Product.NUTELLA, Product.MUSTARD);
            default:
                return new ArrayList<>();
        }
    }

    @Override
    public List<String> getSentences(List<Product> products) {
        if(products == null || products.size() < 3) {
            return new ArrayList<>();
        }

        List<PoeticWrapper> poetics = new ArrayList<>();

        poetics.add(new PoeticWrapper(products.get(0), SentenceType.ANALYZE));
        poetics.add(new PoeticWrapper(products.get(1), SentenceType.ADVICE));
        poetics.add(new PoeticWrapper(products.get(2), SentenceType.FUTURE));

        Category preferredCategory = null;
        int matches = 0;
        for(Category category : Category.values()) {
            int counter = 0;
            final List<Product> categoryProducts = getCategoryProducts(category);
            for(Product product : products) {
                if (categoryProducts.contains(product)) {
                    counter++;
                }
            }

            if(counter > matches) {
                matches = counter;
                preferredCategory = category;
            }
        }
        poetics.add(new PoeticWrapper(preferredCategory, SentenceType.GROUP));

        List<String> sentences = new ArrayList<>();
        for(PoeticWrapper poeticWrapper : poetics) {
            sentences.add(getSentence(poeticWrapper));
        }

        return sentences;
    }

    private String getSentence(PoeticWrapper poeticWrapper) {
        // TODO get from examples
        return null;
    }
}
