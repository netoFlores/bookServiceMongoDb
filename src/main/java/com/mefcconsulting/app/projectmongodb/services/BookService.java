package com.mefcconsulting.app.projectmongodb.services;

import com.mefcconsulting.app.projectmongodb.document.Book;
import com.mefcconsulting.app.projectmongodb.exception.ServiceException;

import java.util.List;

public interface BookService {

    Book saveBook(Book book) throws ServiceException;

    void updateBook(Book book) throws ServiceException;

    void deleteBook(String bookId) throws ServiceException;

    Book findByBookId(String bookId) throws  ServiceException;

    List<Book> findAll() throws ServiceException;
}
