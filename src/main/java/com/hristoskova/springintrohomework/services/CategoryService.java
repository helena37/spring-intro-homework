package com.hristoskova.springintrohomework.services;

import com.hristoskova.springintrohomework.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;
    Category getCategoryById(long id);
}
