package com.icemobile.barcoders.data;

import com.icemobile.barcoders.data.manager.CategoryMapper;
import com.icemobile.barcoders.data.manager.impl.CategoryMapperImpl;

public class Classwiring {

    private static CategoryMapper categoryMapper;

    public static CategoryMapper getCategoryMapper() {
        if(categoryMapper == null) {
            categoryMapper = new CategoryMapperImpl();
        }
        return categoryMapper;
    }
}
