package br.com.alura.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error400Dto {
    private String field;
    private String message;
}
