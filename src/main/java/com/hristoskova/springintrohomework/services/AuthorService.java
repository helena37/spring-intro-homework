package com.hristoskova.springintrohomework.services;

import com.hristoskova.springintrohomework.entities.Author;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AuthorService {
    void seedAuthors() throws IOException;
    long getAllAuthorsCount();
    Author findAuthorById(long id);

    //Ex6
    List<Author> getAllAuthorWithFirstNameEndsIn(String pattern);
    //Ex 10
    List<Object> getCountOfBookCopiesByGivenAuthor();
}
