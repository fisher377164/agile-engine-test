package agile.engine.test.domain.adapter;

import agile.engine.test.domain.entity.Account;

public interface FindAccount {

    Account byAccountId(final Long accountId);
}
