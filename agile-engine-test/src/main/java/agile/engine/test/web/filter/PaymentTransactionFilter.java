package agile.engine.test.web.filter;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PaymentTransactionFilter {

    Long accountId;
}
