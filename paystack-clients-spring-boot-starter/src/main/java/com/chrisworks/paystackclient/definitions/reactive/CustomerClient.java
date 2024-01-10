package com.chrisworks.paystackclient.definitions.reactive;

import com.chrisworks.paystackclient.definitions.Constants;
import com.chrisworks.paystackclient.domain.customer.*;
import com.chrisworks.paystackclient.domain.request.QueryParamConstants;
import com.chrisworks.paystackclient.domain.response.EmptyDataResponse;
import com.maciejwalkowiak.spring.http.annotation.HttpClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@HttpClient(Constants.CUSTOMER_CLIENT)
public interface CustomerClient {

    @PostExchange
    Mono<CustomerResponse.Single> createCustomer(@RequestBody CreateCustomerRequest body);

    @GetExchange
    Mono<CustomerResponse.Multiple> listCustomers(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) @NonNull ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) @NonNull ZonedDateTime to
    );

    @GetExchange
    Mono<CustomerResponse.Multiple> listCustomers();

    @GetExchange("/{customerCode}")
    Mono<DetailedCustomerResponse.Single> fetchCustomer(@PathVariable String customerCode);

    @PutExchange("/{customerCode}")
    Mono<DetailedCustomerResponse.Single> updateCustomer(@PathVariable String customerCode, @RequestBody UpdateCustomerRequest body);

    @PostExchange("/{customerCode}/identification")
    Mono<EmptyDataResponse> validateCustomer(@PathVariable String customerCode, @RequestBody ValidateCustomerRequest body);

    @PostExchange("/set_risk_action")
    Mono<CustomerResponse.Single> whiteOrBlackListCustomer(@RequestBody WhiteOrBlackListCustomerRequest body);

    @PostExchange("/deactivate_authorization")
    Mono<EmptyDataResponse> deactivateAuthorization(@RequestBody DeactivateAuthorizationRequest body);
}
