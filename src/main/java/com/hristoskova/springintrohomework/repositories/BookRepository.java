package com.hristoskova.springintrohomework.repositories;

import com.hristoskova.springintrohomework.entities.AgeRestriction;
import com.hristoskova.springintrohomework.entities.Author;
import com.hristoskova.springintrohomework.entities.Book;
import com.hristoskova.springintrohomework.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Copies;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //Ex 1
    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);
    //Ex 2
    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    //Ex 3
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal greaterPrice);
    //Ex 4
    @Query("select b from Book b where function('YEAR', b.releaseDate) <> :year")
    List<Book> getBooksByReleaseDateNot_Year(int year);
    //Ex 5
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);
    //Ex 7
    List<Book> findAllByTitleLike(String pattern);
    //Ex 8
    @Query("select b from Book b where b.author.lastName like :pattern")
    List<Book> findAllByAuthorLastName(String pattern);
    //Ex 9
    @Query("select count(b) from Book b where length(b.title) > :length")
    Integer countAllByTitleGreaterThan(int length);
    //Ex 11

}
