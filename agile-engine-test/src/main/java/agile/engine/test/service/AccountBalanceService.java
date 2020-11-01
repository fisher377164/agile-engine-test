package agile.engine.test.service;

import agile.engine.test.domain.adapter.FindAccount;
import agile.engine.test.domain.adapter.FindPaymentTransaction;
import agile.engine.test.domain.adapter.UpdateAccount;
import agile.engine.test.domain.entity.Account;
import agile.engine.test.domain.entity.PaymentTransaction;
import agile.engine.test.exeption.NotEnoughMoneyException;
import agile.engine.test.service.usecase.UpdateAccountBalanceUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountBalanceService implements UpdateAccountBalanceUseCase {

    private final FindPaymentTransaction findPaymentTransaction;

    private final FindAccount findAccount;

    private final UpdateAccount updateAccount;

    @Transactional
    @Override
    public void recalculate(final Long accountId) {
        final List<PaymentTransaction> transactions = findPaymentTransaction.byAccountId(accountId);
        final Account account = findAccount.byAccountId(accountId);

        final long balance = transactions.stream().reduce(0L, this::updateBalance, Long::sum);

        if (balance < 0) {
            throw new NotEnoughMoneyException("Not enough money on account with id: {0}!", account.getId());
        }

        account.setBalance(balance);
        updateAccount.update(account);
    }

    private long updateBalance(final long balance, final PaymentTransaction tx) {
        switch (tx.getType()) {
            case DEBIT:
                return balance + tx.getAmount();
            case CREDIT:
                return balance - tx.getAmount();
            default:
                return balance;
        }
    }
}
