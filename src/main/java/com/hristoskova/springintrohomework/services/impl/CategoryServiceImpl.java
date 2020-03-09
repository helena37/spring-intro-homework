package com.hristoskova.springintrohomework.services.impl;

import com.hristoskova.springintrohomework.entities.Category;
import com.hristoskova.springintrohomework.repositories.CategoryRepository;
import com.hristoskova.springintrohomework.services.CategoryService;
import com.hristoskova.springintrohomework.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.hristoskova.springintrohomework.constants.GlobalConstants.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(row -> {
                    Category category = new Category(row);
                    this.categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public Category getCategoryById(long id) {
        return this.categoryRepository.getOne(id);
    }
}
