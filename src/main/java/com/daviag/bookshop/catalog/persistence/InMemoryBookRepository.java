package com.daviag.bookshop.catalog.persistence;

import com.daviag.bookshop.catalog.domain.Book;
import com.daviag.bookshop.catalog.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<String, Book> BOOKS = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return BOOKS.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn)
                ? Optional.of(BOOKS.get(isbn))
                : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return BOOKS.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
        BOOKS.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        BOOKS.remove(isbn);
    }
}
