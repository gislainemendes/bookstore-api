package br.com.alura.bookstore.service;

import br.com.alura.bookstore.repository.AuthorRepository;
import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<AuthorsDto> toList() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(a -> modelMapper.map(a, AuthorsDto.class)).collect(Collectors.toList());
    }

    public void toSave(@RequestBody @Valid AuthorsFormDto dto) {
        Author author = modelMapper.map(dto, Author.class);
        authorRepository.save(author);
    }

    public Author findAuthorByName(String name){
        List<Author> authors = authorRepository.findAll();
        return authors.stream().filter(author -> author
                .getName()
                .equals(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Author doesn't exist!"));
    }
}
