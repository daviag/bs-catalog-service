package com.daviag.bookshop.catalog.domain;

public record Book(
        String isbn,
        String title,
        String author,
        Double price
) {
}
