package com.project.luxuryCoupons.services;

import com.project.luxuryCoupons.beans.Categories;
import com.project.luxuryCoupons.enums.Category;
import com.project.luxuryCoupons.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

/**
 * The category service impl class
 * Is a facade that exacutes 'Category service'
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    /**
     * category repository - field
     */
    private final CategoryRepository categoryRepository;

    /**
     * Add all categoris
     * this method adds all the categories to DB
     */
    @Override
    public void addAllCategories() {
        EnumSet.allOf(Category.class).forEach(categoryEnum -> categoryRepository.save(Categories.builder().name(categoryEnum).build()));
    }
}


