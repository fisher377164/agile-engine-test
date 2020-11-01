package agile.engine.test.exeption;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class ItemNotFoundException extends BaseException {

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public ItemNotFoundException(final String message, final Object... args) {
        super(new MessageFormat(message).format(args), STATUS);
    }
}
