package com.poly.lab7.dao;

import com.poly.lab7.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,String> {
}
