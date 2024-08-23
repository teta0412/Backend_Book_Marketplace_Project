package com.teta.repository;

import com.teta.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByCustomerId (Long userId);

    public List<Order> findByStoreId (Long storeId);
}
