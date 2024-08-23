package com.teta.request;

import com.teta.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long storeId;
    private Address deliveryAddress;
}
