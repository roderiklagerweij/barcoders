package com.icemobile.barcoders.data.manager;

import com.icemobile.barcoders.data.domain.Poetic;
import com.icemobile.barcoders.data.domain.PoeticWrapper;
import com.icemobile.barcoders.data.domain.Product;
import com.icemobile.barcoders.data.domain.SentenceType;

import java.util.List;

public interface CategoryMapper {

    Product getProduct(String barcode);

    List<PoeticWrapper> getPoetic(Product product1, Product product2, Product product3);

    String getSentence(Poetic poetic, SentenceType sentenceType);

}
