package com.icemobile.barcoders.data.manager;

import java.util.List;

public interface CategoryMapper {

    List<String> getSentences(List<String> barcodes);

}
