package agile.engine.test.web.dto;

import agile.engine.test.domain.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransactionDto {

    private Long id;

    private String transactionId;

    private Long accountId;

    private TransactionType type;

    private long amount;

    private LocalDateTime timestamp;
}
