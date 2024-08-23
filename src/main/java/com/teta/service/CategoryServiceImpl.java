package com.teta.service;

import com.teta.model.Category;
import com.teta.model.Store;
import com.teta.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private StoreService storeService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Store store = storeService.findStoreByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setStore(store);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByStoreId(Long id) throws Exception {
        Store store = storeService.findStoreByUserId(id);
        return categoryRepository.findByStoreId(store.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found");
        }

        return optionalCategory.get();
    }
}
