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

import static com.icemobile.barcoders.data.domain.SentenceType.ADVICE;
import static com.icemobile.barcoders.data.domain.SentenceType.FUTURE;
import static com.icemobile.barcoders.data.domain.SentenceType.GROUP;

public class CategoryMapperImpl implements CategoryMapper {

    private Product getProduct(String barcode) {
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
    public List<String> getSentences(List<String> barcodes) {
        if(barcodes == null || barcodes.size() < 3) {
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();
        for(String barcode : barcodes) {
            products.add(getProduct(barcode));
        }

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

        List<PoeticWrapper> poetics = new ArrayList<>();
        poetics.add(new PoeticWrapper(preferredCategory, GROUP));
        poetics.add(new PoeticWrapper(products.get(0), SentenceType.ANALYZE));
        poetics.add(new PoeticWrapper(products.get(1), ADVICE));
        poetics.add(new PoeticWrapper(products.get(2), FUTURE));

        List<String> sentences = new ArrayList<>();
        for(PoeticWrapper poeticWrapper : poetics) {
            sentences.add(getSentence(poeticWrapper));
        }

        return sentences;
    }

    private String getSentence(PoeticWrapper poeticWrapper) {
        switch(poeticWrapper.getSentenceType()) {
            case ANALYZE:
                switch ((Product)poeticWrapper.getPoetic()) {
                    case MILK:
                        return "and the engine that keeps everything running";
                    case BEER:
                        return "and bring people together";
                    case COKE:
                        return "and you deliver happiness worldwide";
                    case CORNFLAKES:
                        return "and you can break a silence";
                    case PEANUTBUTTER:
                        return "and really soft to everyone";
                    case NUTELLA:
                        return "and everyone loves you";
                    case MUSTARD:
                        return "and you always surprise people";
                    case NOODLES:
                        return "and you are the go-to person when things slow down";
                    case PEPPER:
                        return "and you like to be part of a group";
                    case COFFEE:
                        return "and you give a smile to every day";
                    default:
                        return "ERROR: no category found";
                }
            case ADVICE:
                switch ((Product)poeticWrapper.getPoetic()) {
                    case MILK:
                        return "So be open to stand still and reconsider";
                    case BEER:
                        return "So know when you need to hold back";
                    case COKE:
                        return "So, just join them if you can't beat them";
                    case CORNFLAKES:
                        return "So be failtfull to your heritage";
                    case PEANUTBUTTER:
                        return "So, find new friends with similar interests";
                    case NUTELLA:
                        return "So, relax and enjoy yourself";
                    case MUSTARD:
                        return "So, find your inner French";
                    case NOODLES:
                        return "So, try to be silent and listen more";
                    case PEPPER:
                        return "So, make yourself visible";
                    case COFFEE:
                        return "So, try not to run out";
                    default:
                        return "ERROR: no category found";
                }
            case FUTURE:
                switch ((Product)poeticWrapper.getPoetic()) {
                    case MILK:
                        return "Then, you will have a meaning in daily life";
                    case BEER:
                        return "Then, people can't live without you";
                    case COKE:
                        return "Then, you will get a fresh new chance";
                    case CORNFLAKES:
                        return "Then, you will create new memories";
                    case PEANUTBUTTER:
                        return "Then, people will value you more";
                    case NUTELLA:
                        return "Then, you will stay authentic";
                    case MUSTARD:
                        return "Then, people will see what your worth";
                    case NOODLES:
                        return "Then, you will a source of inspiration";
                    case PEPPER:
                        return "Then, you can adapt to every change";
                    case COFFEE:
                        return "Then, people will always find you when they need you";
                    default:
                        return "ERROR: no category found";
                }
            case GROUP:
                switch ((Category)poeticWrapper.getPoetic()) {
                    case SPICE:
                        return "You are a spicey person";
                    case MORNING:
                        return "You are a morning person";
                    case SUGAR:
                        return "You are a sweet person";
                    case DRINKS:
                        return "You keep everything fluid";
                    case ENJOY:
                        return "You are a relaxed person";
                    case SAVORY:
                        return "You like to make things tasty";
                    case CREAMY:
                        return "You are a smooth operator";
                    default:
                        return "ERROR: no category found";
                }
            default:
                return "ERROR: no sentence type found";
        }
    }

}
