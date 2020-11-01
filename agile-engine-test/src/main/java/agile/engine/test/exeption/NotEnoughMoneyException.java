package agile.engine.test.exeption;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class NotEnoughMoneyException extends BaseException {

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public NotEnoughMoneyException(final String message, final Object... args) {
        super(new MessageFormat(message).format(args), STATUS);
    }
}
