package dev.practice.moneymanagementback.exception;

import dev.practice.moneymanagementback.dtos.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setPath(((ServletWebRequest) request).getRequest().getServletPath());

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        errors.forEach((err) -> {
            errorResponse.addError(err.getDefaultMessage());
        });
        return new ResponseEntity<>(errorResponse, headers, status);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex,
                                                         WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        errorResponse.setPath(((ServletWebRequest) webRequest).getRequest().getServletPath());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponse.addError(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        log.info(ex.getMessage(), ex);
        return errorResponse;
    }

    @ExceptionHandler(ActionForbiddenException.class)
    public ErrorResponse handleActionForbiddenException(ActionForbiddenException ex,
                                                        WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        errorResponse.setPath(((ServletWebRequest) webRequest).getRequest().getServletPath());
        errorResponse.setErrorCode(HttpStatus.FORBIDDEN);
        errorResponse.addError(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        log.info(ex.getMessage(), ex);

        return errorResponse;
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResponse handleInvalidParameterException(InvalidParameterException ex,
                                                         WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        errorResponse.setPath(((ServletWebRequest) webRequest).getRequest().getServletPath());
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.addError(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        log.info(ex.getMessage(), ex);
        return errorResponse;
    }

/*    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGlobalException(Exception ex,
                                               WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        errorResponse.setPath(((ServletWebRequest) webRequest).getRequest().getServletPath());
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.addError(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        log.info(ex.getMessage(), ex);

        return errorResponse;
    }*/
}
