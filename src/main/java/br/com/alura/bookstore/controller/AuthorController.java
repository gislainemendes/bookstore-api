package br.com.alura.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bookstore.model.Author;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	private List<Author> authors = new ArrayList<>();
	
	@GetMapping
	public List<Author> toList() {
		return authors;
	}
	
	@PostMapping
	public void toSave(@RequestBody Author author) {
		authors.add(author);
	}
}
