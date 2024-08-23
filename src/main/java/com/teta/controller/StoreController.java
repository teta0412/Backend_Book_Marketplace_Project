package com.teta.controller;

import com.teta.model.Store;
import com.teta.model.User;
import com.teta.service.StoreService;
import com.teta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Store>> searchStore (@RequestHeader("Authorization") String jwt,
                                              @RequestParam String keyword) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List <Store> stores = storeService.searchStore(keyword);

        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Store>> getAllStore (@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Store> stores = storeService.getAllStore();

        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> findStoreById (@RequestHeader("Authorization") String jwt,
                                                @PathVariable Long id
                                                      ) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Store stores = storeService.findStoreById(id);

        return new ResponseEntity<>(stores, HttpStatus.OK);
    }


}
