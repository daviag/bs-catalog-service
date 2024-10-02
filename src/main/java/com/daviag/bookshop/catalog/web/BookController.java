package com.daviag.bookshop.catalog.web;

import com.daviag.bookshop.catalog.domain.Book;
import com.daviag.bookshop.catalog.domain.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> get() {
        log.info("Fetching the list of books in the catalog");
        return bookService.viewBookList();
    }

    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        log.info("Fetching book with ISBN: {}", isbn);
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@RequestBody @Valid Book book) {
        log.info("Adding a new book to the catalog with ISBN {}", book.isbn());
        return bookService.addBookToCatalog(book);
    }

    @PutMapping("/{isbn}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book put(@PathVariable String isbn, @RequestBody @Valid Book book) {
        log.info("Updating book with ISBN {}", isbn);
        return bookService.editBookDetails(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        log.info("Deleting book with ISBN {}", isbn);
        bookService.removeBookFromCatalog(isbn);
    }
}
