package by.iivanov.springbootvalidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

//@Slf4j
@RestControllerAdvice
class MyExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        String detail = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI();
        log.warn("Request URL {}. MethodArgumentNotValidException. details: {}", requestURI, detail);
        log.warn("**************************************");
        ProblemDetail problemDetail = new ProblemDetail(HttpStatus.BAD_REQUEST.value(), ex, detail);
        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }



    public record ProblemDetail(int status, String exceptionName, String detail) {

        public ProblemDetail(int status, Exception exception, String detail) {
            this(status, exception.getClass().getName(), detail);
        }
    }
}
