package agile.engine.test.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int status;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private List<FieldErrorDTO> fieldErrors;

    ErrorDto(final HttpStatus error, final String message) {
        this(error.value(), error.getReasonPhrase(), message, LocalDateTime.now(), null);
    }
}
