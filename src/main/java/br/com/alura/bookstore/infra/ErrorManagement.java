package br.com.alura.bookstore.infra;

import br.com.alura.bookstore.dto.Error400Dto;
import br.com.alura.bookstore.dto.Error500Dto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorManagement {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<Error400Dto> manageError400(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream()
                .map(error -> new Error400Dto(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Error500Dto manageError500(Exception ex, HttpServletRequest req) {
        return new Error500Dto(
                LocalDateTime.now(),
                ex.getClass().toString(),
                ex.getMessage(),
                req.getRequestURI());
    }

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void manageError404() {

    }

}
