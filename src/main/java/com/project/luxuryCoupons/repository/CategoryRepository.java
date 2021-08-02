package com.project.luxuryCoupons.repository;

import com.project.luxuryCoupons.beans.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Category repository class
 * exacute categories CRUD
 */
public interface CategoryRepository extends JpaRepository<Categories, Integer> {}

