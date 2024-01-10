package com.chrisworks.paystackclient;

import com.chrisworks.paystackclient.definitions.Constants;

public enum SupportedClient {
    APPLE_PAY(Constants.APPLE_PAY_CLIENT),
    CUSTOMER(Constants.CUSTOMER_CLIENT),
    PLAN(Constants.PLAN_CLIENT),
    PRODUCT(Constants.PRODUCT_CLIENT),
    SUB_ACCOUNT(Constants.SUB_ACCOUNT_CLIENT),
    TRANSACTION(Constants.TRANSACTION_CLIENT);

    private final String beanName;

    public String beanName() {
        return beanName;
    }

    SupportedClient(String beanName) {
        this.beanName = beanName;
    }
}
