# API WEB PARA GESTÃO DE LIVRARIA ONLINE

Projeto proposto para o Bootcamp de Java da ALURA CURSOS

**Módulo 2 -** Neste módulo foi utilizado Spring Boot para criação de uma API Rest


***Funcionalidades deste módulo:***
* Criação de cadastro e listagem de Autores;
* Criação de cadastro e listagem de Livros;


***Requisitos:***
* O projeto deve ser uma API Rest utilizando o Spring Boot, criada pelo site Spring Initializr;
* O projeto deve utilizar as bibliotecas Lombok, ModelMapper e Bean Validation;
* As funcionalidades devem ser implementadas no modelo de API, consumindo e devolvendo as informações no formato JSON;
* Para o cadastro de livros:

    1- Um livro deve possuir as seguintes informações: título, data de lançamento, número de páginas e autor;

    2- Título deve ser obrigatório e ter no mínimo 10 caracteres;

    3- Data de lançamento deve ser uma data menor ou igual a data atual;

    4- O número de páginas deve ser maior ou igual a 100.
