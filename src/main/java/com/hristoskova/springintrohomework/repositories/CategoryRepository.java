package com.hristoskova.springintrohomework.repositories;

import com.hristoskova.springintrohomework.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
