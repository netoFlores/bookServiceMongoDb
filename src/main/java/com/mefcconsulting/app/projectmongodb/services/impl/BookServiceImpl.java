package com.mefcconsulting.app.projectmongodb.services.impl;

import com.mefcconsulting.app.projectmongodb.document.Book;
import com.mefcconsulting.app.projectmongodb.exception.ServiceException;
import com.mefcconsulting.app.projectmongodb.repository.BookRepository;
import com.mefcconsulting.app.projectmongodb.services.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    private static final Log log = LogFactory.getLog(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book saveBook(Book book) throws ServiceException {
        try{
            log.debug(String.format("New Book  '{%s}'", book.toString()));
            return bookRepository.saveBook(book);
        }catch (Exception e){
            log.error("Error ", e);
            throw new ServiceException(String.format("New Book  '{%s}'", book.toString()));
        }
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        try{
            log.debug(String.format("Update Book '{%s}'", book.toString()));
            bookRepository.updateBook(book);
        }catch (Exception e){
            throw new ServiceException(String.format("Update Book '{%s}'", book.toString()));
        }
    }

    @Override
    public void deleteBook(String bookId) throws ServiceException {
        try{
            log.debug(String.format("Delete Book By bookId '{%s}'", bookId));
            bookRepository.deleteBook(bookId);
        }catch (Exception e){
            throw new ServiceException(String.format("Delete Book By bookId '{%s}'", bookId));
        }
    }

    @Override
    public Book findByBookId(String bookId) throws ServiceException {
        Book book = bookRepository.findByBookId(bookId);
        log.debug(String.format("Book bookId '{%s}", bookId));
        if(book != null)
            return  book;
        else
            throw new ServiceException(String.format("Book Find by bookId '{%s}'", bookId));
    }

    @Override
    public List<Book> findAll() throws ServiceException {
        return bookRepository.findAll();
    }
}
