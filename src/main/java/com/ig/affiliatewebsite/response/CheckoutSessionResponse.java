package com.ig.affiliatewebsite.response;

public class CheckoutSessionResponse {
    private String id;

    public CheckoutSessionResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}