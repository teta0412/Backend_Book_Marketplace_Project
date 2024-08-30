package com.teta.service;

import com.stripe.exception.StripeException;
import com.teta.model.Order;
import com.teta.response.PaymentResponse;
import lombok.Data;


public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
