package br.com.alura.bookstore.controller;

import br.com.alura.bookstore.dto.BooksReportDto;
import br.com.alura.bookstore.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/booksByAuthor")
    public List<BooksReportDto> booksByAuthorReport(){

        return reportService.booksByAuthorReport();
    }
}
