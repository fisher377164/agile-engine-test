package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.PaymentTransaction;

public interface CreatePaymentTransaction {

    PaymentTransaction create(final PaymentTransaction paymentTransaction);
}
