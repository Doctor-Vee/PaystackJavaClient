package com.chrisworks.paystackclient.domain.transaction;

import com.chrisworks.paystackclient.domain.request.PaystackListPagedQueryParam;

import java.math.BigInteger;
import java.time.ZonedDateTime;

public class TransactionTotalQueryParam extends PaystackListPagedQueryParam {

    public TransactionTotalQueryParam(BigInteger perPage, BigInteger page) {
        super(perPage, page);
    }

    public TransactionTotalQueryParam() {
        super();
    }

    public TransactionTotalQueryParam from(ZonedDateTime from) {
        params.put("from", from.toString());
        return this;
    }

    public TransactionTotalQueryParam to(ZonedDateTime to) {
        params.put("to", to.toString());
        return this;
    }
}
