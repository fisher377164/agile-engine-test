package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.PaymentTransaction;

import java.util.List;

public interface FindPaymentTransaction {

    List<PaymentTransaction> byAccountId(final Long accountId);

}
