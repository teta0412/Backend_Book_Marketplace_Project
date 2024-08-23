package com.teta.controller;

import com.teta.model.Book;
import com.teta.model.Store;
import com.teta.model.User;
import com.teta.request.CreateBookRequest;
import com.teta.service.BookService;
import com.teta.service.StoreService;
import com.teta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Book> books = bookService.searchBook(name);


        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Book>> getStoreBook(@RequestParam boolean available,
                                                   @RequestParam boolean comic,
                                                   @RequestParam boolean novel,
                                                 @RequestParam(required = false) String book_category,
                                                 @PathVariable Long storeId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Book> books = bookService.getStoreBook(storeId,available,novel,comic,book_category);


        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
