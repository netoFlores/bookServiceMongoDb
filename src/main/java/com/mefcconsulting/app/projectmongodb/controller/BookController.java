package com.mefcconsulting.app.projectmongodb.controller;

import com.mefcconsulting.app.projectmongodb.document.Book;
import com.mefcconsulting.app.projectmongodb.exception.ServiceException;
import com.mefcconsulting.app.projectmongodb.response.Response;
import com.mefcconsulting.app.projectmongodb.services.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * This's the controller class with the EndPoints of a CRUD for Books.
 * @author netoFlores
 * @since 1.0.0
 */

@RestController
@RequestMapping("books")
public class BookController {

    private static final Log log = LogFactory.getLog(BookController.class);

    @Autowired
    private BookService bookService;

    /**
     * Get a Book by bookId
     * @param bookId
     * @return
     * @throws ServiceException
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<Response> findByBookId(@PathVariable("bookId") String bookId) throws ServiceException{
        Response<Book> response = new Response<>();
        log.info("GET bookId");
        try{
            Book book = bookService.findByBookId(bookId);
            response.setBody(book);
            response.setCode(HttpStatus.OK.value());
        }catch (ServiceException e){
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setErrorMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Get All Books
     * @return a controller
     */
    @GetMapping
    public ResponseEntity<Response> findAll(){
        Response<List<Book>> response = new Response<>();
        log.info("Get All Books");
        try{
            List<Book> books = bookService.findAll();
            response.setCode(HttpStatus.OK.value());
            response.setBody(books);
        }catch (ServiceException e){
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Save a new Book
     * @param book
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> save(@RequestBody  Book book){
        Response<Book> response = new Response<>();
        log.info("Save new Book");
        try{
            response.setCode(HttpStatus.OK.value());
            response.setBody(bookService.saveBook(book));
        }catch (ServiceException e){
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Update a book
     * @param book
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> update(@RequestBody Book book){
        Response<Integer> response = new Response<Integer>();
        log.info("Update Book");
        try{
            Book findBook = bookService.findByBookId(book.getBookId());
            if(findBook != null) {
                bookService.updateBook(book);
                response.setCode(HttpStatus.OK.value());
                response.setBody(1);
            }else{
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setErrorMessage("Not FOUND Book");
            }
        }catch (ServiceException e){
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a Book by bookId
     * @param bookId
     * @return
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Response> delete(@PathVariable("bookId") String bookId){
        Response<Integer> response = new Response<Integer>();
        log.info("Update Book");
        try{
            Book findBook = bookService.findByBookId(bookId);
            if(findBook != null) {
                bookService.deleteBook(bookId);
                response.setCode(HttpStatus.OK.value());
                response.setBody(1);
                return ResponseEntity.ok(response);
            }else{
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setErrorMessage("No FOUND Book");
                return ResponseEntity.ok(response);
            }
        }catch (ServiceException e){
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setErrorMessage(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
