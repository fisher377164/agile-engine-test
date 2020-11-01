package agile.engine.test.web;

import agile.engine.test.query.PageContainer;
import agile.engine.test.query.Pagination;
import agile.engine.test.query.Sorting;
import agile.engine.test.service.usecase.CreatePaymentTransactionUseCase;
import agile.engine.test.service.usecase.FindPaymentTransactionsUseCase;
import agile.engine.test.web.command.CreatePaymentTransactionCommand;
import agile.engine.test.web.dto.PaymentTransactionDto;
import agile.engine.test.web.filter.PaymentTransactionFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/payment_transaction")
public class PaymentTransactionController {

    private final CreatePaymentTransactionUseCase createPaymentTransactionUseCase;

    private final FindPaymentTransactionsUseCase findPaymentTransactionsUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody final CreatePaymentTransactionCommand command) {
        createPaymentTransactionUseCase.create(command);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageContainer<PaymentTransactionDto> getPaymentTransactions(@RequestParam(required = false, defaultValue = "0") final Integer pageIndex,
                                                                       @RequestParam(required = false, defaultValue = "10")
                                                                       @Valid @Min(1) @Max(20) final Integer pageSize,
                                                                       @RequestParam(required = false) final String sortBy,
                                                                       @RequestParam(required = false) final Long accountId,
                                                                       @RequestParam(required = false) final String sortOrder) {
        final Pagination pagination = Pagination.with(pageIndex, pageSize);
        final Sorting sorting = Sorting.by(sortBy, sortOrder);

        final var filter = PaymentTransactionFilter.builder()
                .accountId(accountId)
                .build();

        return findPaymentTransactionsUseCase.findProfiles(filter, pagination, sorting);
    }
}
