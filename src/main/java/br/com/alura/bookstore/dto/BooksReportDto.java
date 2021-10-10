package br.com.alura.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BooksReportDto {

    private String name;
    private Long books;
    private Double percentile;
}
