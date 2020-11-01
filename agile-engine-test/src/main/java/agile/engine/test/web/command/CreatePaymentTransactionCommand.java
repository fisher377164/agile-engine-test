package agile.engine.test.web.command;

import agile.engine.test.domain.entity.TransactionType;
import lombok.Data;

@Data
public class CreatePaymentTransactionCommand {
    private Long accountId;

    private long amount;

    private TransactionType type;
}
