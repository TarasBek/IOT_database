package ua.lviv.iot.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.lviv.iot.exception.EntityAlreadyExistsException;
import ua.lviv.iot.exception.ForeignKeyConstraintException;
import ua.lviv.iot.exception.InvalidDateTimeException;
import ua.lviv.iot.exception.NoDataFoundException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NoDataFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException e) {
        // Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now()
        );

        // Return response entity
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        // Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error ->
                errors
                        .append("'")
                        .append(((FieldError) error).getField())
                        .append("': '")
                        .append(error.getDefaultMessage())
                        .append("';  ")
        );

        ApiException apiException = new ApiException(
                errors.toString(),
                badRequest,
                ZonedDateTime.now()
        );

        // Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ForeignKeyConstraintException.class})
    public ResponseEntity<Object> handleForbiddenExceptions(RuntimeException e) {
        // Create payload containing exception details
        HttpStatus forbidden = HttpStatus.FORBIDDEN;

        ApiException apiException = new ApiException(
                e.getMessage(),
                forbidden,
                ZonedDateTime.now()
        );

        // Return response entity
        return new ResponseEntity<>(apiException, forbidden);
    }

    @ExceptionHandler(value = {EntityAlreadyExistsException.class, InvalidDateTimeException.class})
    public ResponseEntity<Object> handleBadRequestExceptions(RuntimeException e) {
        // Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );

        // Return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }
}
