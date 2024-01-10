package com.chrisworks.paystackclient.definitions.reactive;

import com.chrisworks.paystackclient.domain.request.QueryParamConstants;
import com.chrisworks.paystackclient.domain.transaction.*;
import com.chrisworks.paystackclient.definitions.Constants;
import com.maciejwalkowiak.spring.http.annotation.HttpClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@HttpClient(Constants.TRANSACTION_CLIENT)
public interface TransactionClient {

    @PostExchange("/initialize")
    Mono<InitTransactionResponse.Single> initializeTransaction(@RequestBody InitTransactionRequest body);

    @GetExchange("/verify/{reference}")
    Mono<TransactionResponse.Single> verifyTransaction(@PathVariable String reference);

    @GetExchange
    Mono<TransactionResponse.Multiple> listTransaction(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.CUSTOMER, required = false) BigInteger customer,
            @RequestParam(name = QueryParamConstants.TERMINAL_ID, required = false) String terminalId,
            @RequestParam(name = QueryParamConstants.STATUS, required = false) String status,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to,
            @RequestParam(name = QueryParamConstants.AMOUNT, required = false) String amount

    );

    @GetExchange
    Mono<TransactionResponse.Multiple> listTransaction();

    @GetExchange("/{id}")
    Mono<TransactionResponse.Single> fetchTransaction(@PathVariable BigInteger id);

    @PostExchange("/charge_authorization")
    Mono<TransactionTotalResponse.Single> chargeAuthorization(@RequestBody ChargeAuthorizationRequest body);

    @GetExchange("/timeline/{idOrReference}")
    Mono<TimeLineResponse.Single> viewTransactionTimeLine(@PathVariable String idOrReference);

    @GetExchange("/totals")
    Mono<TransactionTotalResponse.Single> transactionTotals(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to
    );

    @GetExchange("/totals")
    Mono<TransactionTotalResponse.Single> transactionTotals();

    @GetExchange("/export")
    Mono<ExportTransactionResponse.Single> exportTransactions(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to,
            @RequestParam(name = QueryParamConstants.CUSTOMER, required = false) BigInteger customer,
            @RequestParam(name = QueryParamConstants.STATUS, required = false) String status,
            @RequestParam(name = QueryParamConstants.CURRENCY, required = false) String currency,
            @RequestParam(name = QueryParamConstants.AMOUNT, required = false) String amount,
            @RequestParam(name = QueryParamConstants.SETTLED, required = false) Boolean settled,
            @RequestParam(name = QueryParamConstants.SETTLEMENT, required = false) BigInteger settlement,
            @RequestParam(name = QueryParamConstants.PAYMENT_PAGE, required = false) BigInteger paymentPage

    );

    @GetExchange("/export")
    Mono<ExportTransactionResponse.Single> exportTransactions();

    @PostExchange("/partial_debit")
    Mono<TransactionTotalResponse.Single> partialDebit(@RequestBody PartialDebitRequest body);
}
