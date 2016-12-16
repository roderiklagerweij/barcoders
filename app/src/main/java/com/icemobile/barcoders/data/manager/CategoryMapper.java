package com.icemobile.barcoders.data.manager;

import com.icemobile.barcoders.data.domain.Sentence;

import java.util.List;

public interface CategoryMapper {

    List<Sentence> getSentences(List<String> barcodes);

}
