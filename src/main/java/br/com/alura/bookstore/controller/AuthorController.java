package br.com.alura.bookstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	private List<Author> authors = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping
	public List<AuthorsDto> toList() {
		return authors.stream().map(a -> modelMapper.map(a, AuthorsDto.class)).collect(Collectors.toList());
	}

	@PostMapping
	public void toSave(@RequestBody @Valid AuthorsFormDto dto) {
		Author author = modelMapper.map(dto, Author.class);
		authors.add(author);
	}
}
