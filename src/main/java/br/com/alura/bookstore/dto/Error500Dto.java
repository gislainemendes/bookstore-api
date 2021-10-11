package br.com.alura.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Error500Dto {

    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

}
