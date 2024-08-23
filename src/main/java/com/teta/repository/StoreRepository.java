package com.teta.repository;

import com.teta.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "SELECT r FROM store where lower(r.name) LIKE lower(concat('%',:query,'%')",nativeQuery = true)
    List<Store> findBySearchQuery(String query);

    Store findByOwnerId(Long userId);


}
