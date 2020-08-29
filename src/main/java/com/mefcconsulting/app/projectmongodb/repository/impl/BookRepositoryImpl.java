package com.mefcconsulting.app.projectmongodb.repository.impl;

import com.mefcconsulting.app.projectmongodb.document.Book;
import com.mefcconsulting.app.projectmongodb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final MongoOperations mongoOperations;

    @Autowired
    public BookRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Book saveBook(Book book) {
        this.mongoOperations.save(book);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        this.mongoOperations.save(book);
    }

    @Override
    public void deleteBook(String bookId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("bookId").is(bookId)), Book.class);
    }

    @Override
    public Book findByBookId(String bookId) {
        Book book = mongoOperations.findOne(new Query(Criteria.where("bookId").is(bookId)), Book.class);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = this.mongoOperations.find(new Query(), Book.class);
        return books;
    }


}
