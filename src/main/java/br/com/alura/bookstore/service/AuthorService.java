package br.com.alura.bookstore.service;

import br.com.alura.bookstore.dto.AuthorsDto;
import br.com.alura.bookstore.dto.AuthorsFormDto;
import br.com.alura.bookstore.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private List<Author> authors = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<AuthorsDto> toList() {
        return authors.stream().map(a -> modelMapper.map(a, AuthorsDto.class)).collect(Collectors.toList());
    }

    public void toSave(@RequestBody @Valid AuthorsFormDto dto) {
        Author author = modelMapper.map(dto, Author.class);
        authors.add(author);
    }

    public Author findAuthorByName(String name){
        return authors.stream().filter(author -> author
                .getName()
                .equals(name))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Author doesn't exist!"));
    }
}
