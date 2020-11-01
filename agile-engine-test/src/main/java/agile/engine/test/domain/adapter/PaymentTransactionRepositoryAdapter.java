package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.PaymentTransaction;
import agile.engine.test.domain.entity.QPaymentTransaction;
import agile.engine.test.domain.repository.PaymentTransactionRepository;
import agile.engine.test.query.PageContainer;
import agile.engine.test.query.Pagination;
import agile.engine.test.query.Sorting;
import agile.engine.test.query.WhereClauseBuilder;
import agile.engine.test.web.filter.PaymentTransactionFilter;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class PaymentTransactionRepositoryAdapter implements CreatePaymentTransaction, FindPaymentTransaction, FindPaymentTransactions {

    private final PaymentTransactionRepository paymentTransactionRepository;

    @Override
    public PaymentTransaction create(final PaymentTransaction paymentTransaction) {
        return paymentTransactionRepository.save(paymentTransaction);
    }

    @Override
    public List<PaymentTransaction> byAccountId(final Long accountId) {
        return paymentTransactionRepository.findAllByAccountId(accountId);
    }

    @Override
    public PageContainer<PaymentTransaction> byFilter(final PaymentTransactionFilter filter, final Pagination pagination, final Sorting sorting) {
        final Pageable pageable = Pagination.toPageable(pagination, sorting);
        final Page<PaymentTransaction> page = paymentTransactionRepository.findAll(toWhereClause(filter), pageable);
        return new PageContainer<>(page.getContent(), page.getTotalElements());
    }

    private BooleanBuilder toWhereClause(final PaymentTransactionFilter filter) {
        final QPaymentTransaction paymentTransaction = QPaymentTransaction.paymentTransaction;
        final Long accountId = filter.getAccountId();

        return new WhereClauseBuilder()
                .optionalAnd(accountId, () -> paymentTransaction.accountId.eq(accountId))
                .where();
    }
}
