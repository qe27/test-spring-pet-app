package com.qe27.webapp.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private int userId;
    private String itemId;
    private double discount;
}