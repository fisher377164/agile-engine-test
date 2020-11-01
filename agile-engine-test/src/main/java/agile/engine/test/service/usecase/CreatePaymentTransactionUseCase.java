package agile.engine.test.service.usecase;

import agile.engine.test.web.command.CreatePaymentTransactionCommand;

public interface CreatePaymentTransactionUseCase {

    String create(final CreatePaymentTransactionCommand command);
}
