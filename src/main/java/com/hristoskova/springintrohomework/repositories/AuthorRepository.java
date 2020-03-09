package com.hristoskova.springintrohomework.repositories;

import com.hristoskova.springintrohomework.entities.Author;
import com.hristoskova.springintrohomework.entities.Book;
import com.hristoskova.springintrohomework.services.AuthorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    //Ex 6
    List<Author> findAllByFirstNameLike(String pattern);
    //Ex 10
    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName, ' - ', SUM(b.copies)) " +
            "FROM Author AS a " +
            "JOIN a.books AS b " +
            "GROUP BY a.id " +
            "ORDER BY SUM(b.copies) DESC ")
    List<Object> getAuthorsByBooksCopies();
}
