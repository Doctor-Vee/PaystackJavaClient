package com.chrisworks.paystackclient.definitions.reactive;

import com.chrisworks.paystackclient.definitions.Constants;
import com.chrisworks.paystackclient.domain.request.QueryParamConstants;
import com.chrisworks.paystackclient.domain.subaccount.CreateSubaccountRequest;
import com.chrisworks.paystackclient.domain.subaccount.SubaccountResponse;
import com.chrisworks.paystackclient.domain.subaccount.UpdateSubaccountRequest;
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
    Mono<SubaccountResponse.Single> createSubAccount(@RequestBody CreateSubaccountRequest body);

    @GetExchange
    Mono<SubaccountResponse.Multiple> listSubAccounts(
            @RequestParam(name = QueryParamConstants.PAGE) @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE) @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to
    );

    @GetExchange("/{idOrCode}")
    Mono<SubaccountResponse.Single> fetchSubAccount(@PathVariable String idOrCode);

    @PutExchange("/{idOrCode}")
    Mono<SubaccountResponse.Single> updateSubAccount(@PathVariable String idOrCode, @RequestBody UpdateSubaccountRequest body);
}
