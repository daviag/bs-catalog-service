package com.daviag.bookshop.catalog.web;

import com.daviag.bookshop.catalog.domain.Book;
import com.daviag.bookshop.catalog.domain.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Iterable<Book> get() {
        return bookService.viewBookList();
    }

    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book post(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    @PutMapping("/{isbn}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book put(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.editBookDetails(isbn, book);
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
}
