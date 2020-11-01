package agile.engine.test.web.exception;

import agile.engine.test.exeption.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
class ExceptionTranslator {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDto> processCustomError(final BaseException ex) {
        final ErrorDto errorDto = new ErrorDto(ex.getStatus(), ex.getMessage());
        log.warn(errorDto.toString());
        return new ResponseEntity<>(errorDto, ex.getStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto processValidationError(final MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        final ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        errorDto.setFieldErrors(processFieldErrors(fieldErrors));
        log.warn(errorDto.toString());
        return errorDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorDto processAnyError(final Throwable ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private List<FieldErrorDTO> processFieldErrors(final List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(fieldError -> FieldErrorDTO.builder()
                        .objectName(fieldError.getObjectName())
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}

