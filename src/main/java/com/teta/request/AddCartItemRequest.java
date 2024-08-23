package com.teta.request;

import lombok.Data;

@Data
public class AddCartItemRequest {
    private Long bookId;
    private int quantity;
}
