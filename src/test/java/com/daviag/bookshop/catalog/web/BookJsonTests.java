package com.daviag.bookshop.catalog.web;

import com.daviag.bookshop.catalog.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book(1L, "1234567890", "Title", "Author", 9.90,
                "david", Instant.now(), "laura", Instant.now(), 1);
        var jsonContent = json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.id")
                .isEqualTo(book.id());
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo(book.isbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(book.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualTo(book.author());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(book.price());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.createdBy")
                .isEqualTo(book.createdBy());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.createdDate")
                .isEqualTo(book.createdDate());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.lastModifiedBy")
                .isEqualTo(book.lastModifiedBy());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.lastModifiedDate")
                .isEqualTo(book.lastModifiedDate());
        assertThat(jsonContent).extractingJsonPathStringValue("@.version")
                .isEqualTo(book.version());
    }

    @Test
    void testDeserialize() throws Exception {
        Instant instant = Instant.now();
        System.out.println(instant);
        var content = """
                {
                  "id": 1,
                  "isbn": "1234567890",
                  "title": "Title",
                  "author": "Author",
                  "price": 9.90,
                  "createdBy": "david",
                  "createdDate": "%s",
                  "lastModifiedBy": "laura",
                  "lastModifiedDate": "%s",
                  "version": 1
                }
                """.formatted(instant, instant);
        System.out.println(content);
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book(1L, "1234567890", "Title", "Author", 9.90,
                        "david", instant, "laura", instant, 1));
    }
}
