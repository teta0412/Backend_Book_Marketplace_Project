package com.teta.service;

import com.teta.model.Store;
import com.teta.model.User;
import com.teta.request.CreateStoreRequest;

import java.util.List;

public interface StoreService {

    public Store createStore (CreateStoreRequest req , User user);

    public Store updateStore (Long storeId, CreateStoreRequest updatedStore) throws Exception;

    public void deleteStore (Long storeId) throws Exception;

    public List<Store> getAllStore();

    public List<Store> searchStore(String keyword);

    public Store findStoreById (Long id) throws Exception;

    public Store findStoreByUserId(Long userId) throws Exception;

    public Store updateStoreStatus(Long id) throws Exception;
}
