package com.hristoskova.springintrohomework.controllers;

import com.hristoskova.springintrohomework.entities.Author;
import com.hristoskova.springintrohomework.entities.Book;
import com.hristoskova.springintrohomework.entities.EditionType;
import com.hristoskova.springintrohomework.services.AuthorService;
import com.hristoskova.springintrohomework.services.BookService;
import com.hristoskova.springintrohomework.services.CategoryService;
import org.springframework.aop.target.EmptyTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.stereotype.Controller;

import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader reader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader reader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        System.out.println("Please, enter the number of exercise between 1 and 11: ");
        String exerciseNumber;

        while (!"0".equals(exerciseNumber = reader.readLine())) {
            switch (exerciseNumber) {
                case "1":
                    //Ex 1
                    System.out.println("Enter an AgeRestriction name: ");
                    try {
                        this.bookService.findAllBooksByAgeRestriction(reader.readLine())
                                .forEach(book -> System.out.println(book.getTitle()));
                    } catch (IllegalArgumentException noResult) {
                        System.out.println("Restriction with this name doesn't exist!");
                    }
                    break;
                case "2":
                    //Ex 2
                    this.bookService
                            .getAllBooksWithGoldenEditionAndLessThan5000Copies(EditionType.GOLD, 5000)
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case "3":
                    //Ex 3
                    this.bookService
                            .getAllBooksWithPriceLowerThan5AndHigherThan40(
                                    new BigDecimal(5), new BigDecimal(40))
                            .forEach(book -> System.out.println(
                                    String.format(
                                            "%s $%.2f",
                                            book.getTitle(),
                                            book.getPrice()
                                    )
                            ));
                    break;
                case "4":
                    //Ex 4
                    System.out.println("Please enter the release year: ");
                    this.bookService
                            .getAllBooksByReleaseYearNotIn(Integer.parseInt(reader.readLine()))
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case "5":
                    //Ex 5
                    System.out.println("Please, enter release date in format ---> dd-MM-yyyy");
                    try {
                        this.bookService
                                .getAllBooksByReleaseDateBefore(reader.readLine())
                                .forEach(book -> System.out.println(
                                        String.format(
                                                "%s %s %.2f",
                                                book.getTitle(),
                                                book.getEditionType(),
                                                book.getPrice()
                                        )
                                ));
                    } catch (DateTimeParseException ex) {
                        System.out.println("!!!You have entered the wrong data format!!!");
                    }
                    break;
                case "6":
                    //Ex 6
                    System.out.println("Please, enter the string pattern: ");
                    this.authorService
                            .getAllAuthorWithFirstNameEndsIn(reader.readLine())
                            .forEach(author ->
                                    System.out.println(author.getFirstName() + " " + author.getLastName()));

                    break;
                case "7":
                    //Ex 7
                    System.out.println("Please, enter a string pattern: ");
                    this.bookService
                            .getAllBooksEndingWithGivenPattern(reader.readLine())
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;

                case "8":
                    //Ex 8
                    System.out.println("Please, enter a string pattern: ");
                    this.bookService
                            .getAllBooksWithAuthorsLastNameStartsWith(reader.readLine())
                            .forEach(book -> System.out.println(
                                    String.format(
                                            "%s (%s %s)",
                                            book.getTitle(),
                                            book.getAuthor().getFirstName(),
                                            book.getAuthor().getLastName()
                                    )
                            ));
                    System.out.println("PLEASE, CHECK RESULTS MANUALLY in DB," +
                            " because we INSERT RANDOM AUTHORS for every books.");
                    return;
                case "9":
                    //Ex 9
                    System.out.println("Please, enter the book title length: ");
                    int input;
                    int result = this.bookService
                            .getCountOfBooksWithTitleLengthGreaterThan(input = Integer.parseInt(reader.readLine()));
                    System.out.println(String.format(
                            "%d ---> There are %d books with longer title than %d symbols",
                            result, result, input));
                    break;
                case "10":
                    //Ex 10
                    this.authorService
                            .getCountOfBookCopiesByGivenAuthor()
                            .forEach(System.out::println);
                    break;

                case "11":
                    //Ex 11
                    System.out.println("Please, enter a book title: ");
                    this.bookService
                            .getBookInfoByGivenTitle(reader.readLine())
                            .forEach(b -> System.out.println(
                                    String.format(
                                            "%s %s %s %.2f",
                                            b.getTitle(),
                                            b.getEditionType(),
                                            b.getAgeRestriction(),
                                            b.getPrice()
                                    )
                            ));
                    break;
                default:
                    System.out.println("!!!Exercise with this number doesn't exist!!!");
                    break;
            }
            System.out.println("Please, enter the number of exercise between 1 and 11: ");
            System.out.println("If you want to exit, enter 0");
        }
    }
}
