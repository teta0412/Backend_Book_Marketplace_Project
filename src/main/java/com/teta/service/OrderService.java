package com.teta.service;

import com.teta.model.Order;
import com.teta.model.User;
import com.teta.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder (Long orderId) throws Exception;

    public List<Order> getUsersOrder (Long userId) throws Exception;

    public List<Order> getStoresOrder (Long storeId ,String orderStatus) throws Exception;

    public Order findOrderById (Long orderId) throws Exception;
}
