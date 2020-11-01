package agile.engine.test.service.usecase;

import agile.engine.test.query.PageContainer;
import agile.engine.test.query.Pagination;
import agile.engine.test.query.Sorting;
import agile.engine.test.web.dto.PaymentTransactionDto;
import agile.engine.test.web.filter.PaymentTransactionFilter;

public interface FindPaymentTransactionsUseCase {

    PageContainer<PaymentTransactionDto> findProfiles(final PaymentTransactionFilter profileFilter,
                                                      final Pagination pagination,
                                                      final Sorting sorting);
}
