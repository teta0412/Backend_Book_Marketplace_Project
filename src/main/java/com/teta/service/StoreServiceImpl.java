package com.teta.service;

import com.teta.model.Address;
import com.teta.model.Store;
import com.teta.model.User;
import com.teta.repository.AddressRepository;
import com.teta.repository.StoreRepository;
import com.teta.repository.UserRepository;
import com.teta.request.CreateStoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Store createStore(CreateStoreRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Store store = new Store();
        store.setAddress(address);
        store.setContactInformation(req.getContactInformation());
        store.setDescription(req.getDescription());
        store.setImages(req.getImages());
        store.setName(req.getName());
        store.setOpeningHours(req.getOpeningHours());
        store.setRegistrationDate(LocalDateTime.now());
        store.setOwner(user);

        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long storeId, CreateStoreRequest updatedStore) throws Exception {
        Store store = findStoreById(storeId);

        if (store.getDescription()!=null){
            store.setDescription(updatedStore.getDescription());
        }
        if (store.getName()!=null){
            store.setName(updatedStore.getName());
        }
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long storeId) throws Exception {
        Store store = findStoreById(storeId);
        storeRepository.delete(store);
    }

    @Override
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public List<Store> searchStore(String keyword) {
        return storeRepository.findBySearchQuery(keyword);
    }

    @Override
    public Store findStoreById(Long id) throws Exception {

        Optional<Store> opt = storeRepository.findById(id);

        if (opt.isEmpty()){
            throw new Exception("Store not found with id " + id);
        }

        return opt.get();
    }

    @Override
    public Store findStoreByUserId(Long userId) throws Exception {

        Store store = storeRepository.findByOwnerId(userId);

        if (store==null){
            throw new Exception("Store not found with owner id " + userId);
        }

        return store;
    }

    @Override
    public Store updateStoreStatus(Long id) throws Exception {
        Store store = storeRepository.findByOwnerId(id);
        store.setOpen(!store.isOpen());
        return storeRepository.save(store);
    }
}
