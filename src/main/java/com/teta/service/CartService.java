package com.teta.service;

import com.teta.model.Cart;
import com.teta.model.CartItem;
import com.teta.model.User;
import com.teta.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart (Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId (Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

}
