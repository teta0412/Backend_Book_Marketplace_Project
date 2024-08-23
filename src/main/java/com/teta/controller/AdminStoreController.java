package com.teta.controller;

import com.teta.model.Store;
import com.teta.model.User;
import com.teta.request.CreateStoreRequest;
import com.teta.response.MessageResponse;
import com.teta.service.StoreService;
import com.teta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/store")
public class AdminStoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Store> createStore (@RequestBody CreateStoreRequest req,
                                              @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Store store = storeService.createStore(req,user);

        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore (@RequestBody CreateStoreRequest req,
                                              @RequestHeader("Authorization") String jwt,
                                              @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Store store = storeService.updateStore(id,req);

        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteStore (@RequestHeader("Authorization") String jwt,
                                              @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        storeService.deleteStore(id);

        MessageResponse res =new MessageResponse();
        res.setMessage("Store deleted successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Store> updateStoreStatus (
                                                        @RequestHeader("Authorization") String jwt,
                                                        @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Store store = storeService.updateStoreStatus(id);

        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Store> findStoreByUserId (
                                                    @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Store store = storeService.findStoreByUserId(user.getId());

        return new ResponseEntity<>(store, HttpStatus.OK);
    }
}
