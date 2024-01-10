package com.chrisworks.paystackclient.definitions.reactive;

import com.chrisworks.paystackclient.definitions.Constants;
import com.chrisworks.paystackclient.domain.request.QueryParamConstants;
import com.chrisworks.paystackclient.domain.subaccount.CreateSubAccountRequest;
import com.chrisworks.paystackclient.domain.subaccount.SubAccountResponse;
import com.chrisworks.paystackclient.domain.subaccount.UpdateSubAccountRequest;
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

@HttpClient(Constants.SUB_ACCOUNT_CLIENT)
public interface SubAccountClient {

    @PostExchange
    Mono<SubAccountResponse.Single> createSubAccount(@RequestBody CreateSubAccountRequest body);

    @GetExchange
    Mono<SubAccountResponse.Multiple> listSubAccounts(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to
    );

    @GetExchange
    Mono<SubAccountResponse.Multiple> listSubAccounts();

    @GetExchange("/{idOrCode}")
    Mono<SubAccountResponse.Single> fetchSubAccount(@PathVariable String idOrCode);

    @PutExchange("/{idOrCode}")
    Mono<SubAccountResponse.Single> updateSubAccount(@PathVariable String idOrCode, @RequestBody UpdateSubAccountRequest body);
}
