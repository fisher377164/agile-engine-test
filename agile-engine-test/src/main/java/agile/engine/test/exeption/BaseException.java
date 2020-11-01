package agile.engine.test.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException {
    private final HttpStatus status;

    protected BaseException() {
        this.status = HttpStatus.BAD_REQUEST;
    }

    public BaseException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

    public  BaseException(final String message, final Throwable cause, final HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(final Throwable cause, final HttpStatus status) {
        super(cause);
        this.status = status;
    }

    public BaseException(final String message,
                  final Throwable cause,
                  final boolean enableSuppression,
                  final boolean writableStackTrace,
                  final HttpStatus status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

}
