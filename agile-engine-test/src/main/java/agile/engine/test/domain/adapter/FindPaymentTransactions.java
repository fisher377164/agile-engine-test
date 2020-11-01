package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.PaymentTransaction;
import agile.engine.test.query.PageContainer;
import agile.engine.test.query.Pagination;
import agile.engine.test.query.Sorting;
import agile.engine.test.web.filter.PaymentTransactionFilter;

public interface FindPaymentTransactions {

    PageContainer<PaymentTransaction> byFilter(final PaymentTransactionFilter filter, final Pagination pagination, final Sorting sorting);

}
