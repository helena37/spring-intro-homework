package com.hristoskova.springintrohomework.services;

import com.hristoskova.springintrohomework.entities.AgeRestriction;
import com.hristoskova.springintrohomework.entities.Book;
import com.hristoskova.springintrohomework.entities.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookService {
    void seedBooks() throws IOException;
    //Ex 1
    List<Book> findAllBooksByAgeRestriction(String ageRestriction);
    //Ex 2
    List<Book> getAllBooksWithGoldenEditionAndLessThan5000Copies(EditionType editionType, int copies);
    //Ex 3
    List<Book> getAllBooksWithPriceLowerThan5AndHigherThan40(BigDecimal lowerPrice, BigDecimal higherPrice);
    //Ex 4
    List<Book> getAllBooksByReleaseYearNotIn(int year);
    //Ex 5
    List<Book> getAllBooksByReleaseDateBefore(String releaseDate);
    //Ex 7
    List<Book> getAllBooksEndingWithGivenPattern(String pattern);
    //Ex 8
    List<Book> getAllBooksWithAuthorsLastNameStartsWith(String pattern);
    //Ex 9
    Integer getCountOfBooksWithTitleLengthGreaterThan(int length);
    //Ex 11
    List<Book> getBookInfoByGivenTitle(String title);
}
