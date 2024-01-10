package com.chrisworks.paystackclient.definitions.reactive;

import com.chrisworks.paystackclient.domain.product.CreateOrUpdateProductRequest;
import com.chrisworks.paystackclient.domain.product.ProductResponse;
import com.chrisworks.paystackclient.domain.request.QueryParamConstants;
import com.chrisworks.paystackclient.definitions.Constants;
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

@HttpClient(Constants.PRODUCT_CLIENT)
public interface ProductClient {

    @PostExchange
    Mono<ProductResponse.Single> createProduct(@RequestBody CreateOrUpdateProductRequest body);

    @GetExchange
    Mono<ProductResponse.Multiple> listProducts(
            @RequestParam(name = QueryParamConstants.PAGE, required = false, defaultValue = "1") @NonNull BigInteger page,
            @RequestParam(name = QueryParamConstants.PER_PAGE, required = false, defaultValue = "50") @NonNull BigInteger perPage,
            @RequestParam(name = QueryParamConstants.FROM, required = false) ZonedDateTime from,
            @RequestParam(name = QueryParamConstants.TO, required = false) ZonedDateTime to
    );

    @GetExchange
    Mono<ProductResponse.Multiple> listProducts();

    @GetExchange("/{id}")
    Mono<ProductResponse.Single> fetchProduct(@PathVariable String id);

    @PutExchange("/{id}")
    Mono<ProductResponse.Multiple> updateProduct(@PathVariable String id);
}
