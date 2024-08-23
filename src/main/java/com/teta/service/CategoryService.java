package com.teta.service;

import com.teta.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoryByStoreId(Long id)throws Exception;

    public Category findCategoryById(Long id)throws Exception;
}
