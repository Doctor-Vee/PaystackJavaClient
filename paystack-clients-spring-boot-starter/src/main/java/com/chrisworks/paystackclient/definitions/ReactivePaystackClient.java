package com.chrisworks.paystackclient.definitions;

import com.chrisworks.paystackclient.conditions.NoClientIsSelectedToBeActive;
import com.chrisworks.paystackclient.definitions.reactive.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

public interface ReactivePaystackClient {

    ApplePayClient applePay();
    CustomerClient customer();
    PlanClient plan();
    ProductClient product();
    SubAccountClient subAccount();
    TransactionClient transaction();

    @Component(value = "reactivePaystackClient")
    @Conditional(NoClientIsSelectedToBeActive.class)
    @ConditionalOnProperty(prefix = "paystack-client", name = "definition-type", havingValue = "REACTIVE")
    record Impl(ApplePayClient applePay, CustomerClient customer, PlanClient plan, ProductClient product,
                SubAccountClient subAccount, TransactionClient transaction) implements ReactivePaystackClient {}
}
