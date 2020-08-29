package com.mefcconsulting.app.projectmongodb.repository;

import com.mefcconsulting.app.projectmongodb.document.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Book saveBook(Book book);
    void updateBook(Book book);
    void deleteBook(String bookId);
    Book findByBookId(String bookId);
}
