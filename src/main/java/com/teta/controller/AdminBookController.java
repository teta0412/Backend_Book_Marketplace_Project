package com.teta.controller;

import com.teta.model.Book;
import com.teta.model.Store;
import com.teta.model.User;
import com.teta.request.CreateBookRequest;
import com.teta.response.MessageResponse;
import com.teta.service.BookService;
import com.teta.service.StoreService;
import com.teta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/book")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Store store = storeService.findStoreById(req.getStoreId());
        Book book = bookService.createBook(req,req.getCategory(),store);


        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteBook(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        bookService.deleteBook(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("Book deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookAvailableStatus (@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Book book =  bookService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


}
