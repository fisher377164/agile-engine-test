package agile.engine.test.service;

import agile.engine.test.domain.adapter.CreatePaymentTransaction;
import agile.engine.test.domain.adapter.FindPaymentTransactions;
import agile.engine.test.domain.entity.PaymentTransaction;
import agile.engine.test.domain.entity.TransactionType;
import agile.engine.test.query.PageContainer;
import agile.engine.test.query.Pagination;
import agile.engine.test.query.Sorting;
import agile.engine.test.service.usecase.CreatePaymentTransactionUseCase;
import agile.engine.test.service.usecase.FindPaymentTransactionsUseCase;
import agile.engine.test.service.usecase.UpdateAccountBalanceUseCase;
import agile.engine.test.web.command.CreatePaymentTransactionCommand;
import agile.engine.test.web.dto.PaymentTransactionDto;
import agile.engine.test.web.filter.PaymentTransactionFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentTransactionService implements CreatePaymentTransactionUseCase, FindPaymentTransactionsUseCase {

    private final CreatePaymentTransaction createPaymentTransaction;

    private final FindPaymentTransactions findPaymentTransactions;

    private final UpdateAccountBalanceUseCase updateAccountBalanceUseCase;

    @Transactional
    @Override
    public String create(final CreatePaymentTransactionCommand command) {
        return createPaymentTransaction(command.getAccountId(), command.getAmount(), command.getType());
    }

    private String createPaymentTransaction(final Long accountId, final long amount, final TransactionType type) {
        final var transactionId = UUID.randomUUID().toString();
        final var transaction = PaymentTransaction.builder()
                .transactionId(transactionId)
                .type(type)
                .accountId(accountId)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .build();
        createPaymentTransaction.create(transaction);
        updateAccountBalanceUseCase.recalculate(accountId);
        return transactionId;
    }

    @Override
    public PageContainer<PaymentTransactionDto> findProfiles(final PaymentTransactionFilter filter, final Pagination pagination, final Sorting sorting) {
        final PageContainer<PaymentTransaction> paymentTransactions = findPaymentTransactions.byFilter(filter, pagination, sorting);

        return PageContainer.from(paymentTransactions, this::toPaymentTransactionDto);
    }

    private PaymentTransactionDto toPaymentTransactionDto(final PaymentTransaction paymentTransaction) {
        return PaymentTransactionDto.builder()
                .id(paymentTransaction.getId())
                .transactionId(paymentTransaction.getTransactionId())
                .accountId(paymentTransaction.getAccountId())
                .type(paymentTransaction.getType())
                .amount(paymentTransaction.getAmount())
                .timestamp(paymentTransaction.getTimestamp())
                .build();
    }
}
