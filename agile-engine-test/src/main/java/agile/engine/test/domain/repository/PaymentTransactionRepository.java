package agile.engine.test.domain.repository;

import agile.engine.test.domain.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long>, QuerydslPredicateExecutor<PaymentTransaction> {

    Optional<PaymentTransaction> findByTransactionId(final String transactionId);

    List<PaymentTransaction> findAllByAccountId(final Long accountId);

    List<PaymentTransaction> findAllByTransactionId(final String transactionId);
}
