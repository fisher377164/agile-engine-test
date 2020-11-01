package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.Account;
import agile.engine.test.domain.repository.AccountRepository;
import agile.engine.test.exeption.ItemNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class AccountRepositoryAdapter implements FindAccount, UpdateAccount {

    private final AccountRepository accountRepository;

    @Override
    public Account byAccountId(final Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ItemNotFoundException("Account with userId '{0}' was not found", accountId));
    }

    @Override
    public void update(final Account account) {
        accountRepository.save(account);
    }
}
