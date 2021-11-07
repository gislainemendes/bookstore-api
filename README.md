# API PARA GESTÃO DE LIVRARIA ONLINE

Projeto proposto para o Bootcamp de Java da ALURA CURSOS

## Módulo 2
Neste módulo foi utilizado Spring Boot para criação de uma API Rest


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
    
    
## Módulo 3 
Neste módulo foi implementada a camada de persistência na API da livraria, substituindo todo o código que simulava um banco de dados em memória.
Deve ser adicionado o módulo do Spring Data JPA, como como o driver de algum banco de dados, como por exemplo o MySQL.

Será necessário realizar as configurações do banco via propriedades no arquivo application.properties, bem como realizar o mapeamento das entidades JPA da API.

Deverá ser utilizado o FlyWay como ferramenta de migration, para o controle da evolução do schema do banco  de dados da API. Necessário também utilizar o recurso de paginação e ordenação nas funcionalidades de listagem de autores e de livros.


***Funcionalidades deste módulo:***
* Foi implementada uma nova funcionalidade: relatório de quantidade de livros publicados por autor;


***Requisitos:***
* Essa nova funcionalidade deverá retornar um JSON com os dados do relatório para que a aplicação FrontEnd consiga fazer a geração de um gráfico, similar ao demonstrado na seguinte figura: 

![image](https://user-images.githubusercontent.com/63201229/136712891-bf45a9e6-84df-43a9-b836-260d1ffb64d8.png)


## Módulo 4 
Neste módulo foi criada uma classe com métodos para tratamento dos erros: 400, 404 e 500, bem como classes DTO para representar os erros de maneira mais simplificada.

Foram escritos testes automatizados para as classes controller, repository e service da API, utilizando para isso as bibliotecas JUnit, AssertJ e Mockito, bem como os recursos de testes do Spring Boot.

Foi utilizado também o Swagger como ferramenta para geração automatizada de documentação da API, sendo criada uma classe para realizar as suas configurações.


***Funcionalidades deste módulo:***
* Além de implementar os recursos citados anteriormente, foram implementadas novas funcionalidades na API, que servirão para completar o CRUD de autores e livros.

Funcionalidades:

* Atualização dos dados de um determinado autor
* Exclusão de um determinado autor
* Detalhamento de um determinado autor
* Atualização dos dados de um determinado livro
* Exclusão de um determinado livro
* Detalhamento de um determinado livro


## Módulo 5
Neste módulo foi implementado código para ter um controle de autenticação e autorização na API, utilizando para isso o Spring Security em conjunto com a biblioteca JJWT, para assim seguir o modelo de autenticação Stateless via token. 


***Requisitos:***
Foi criada uma classe que contém os códigos para realizar as configurações de segurança, como os detalhes de autenticação e autorização da API de maneira Stateless, criadas também as classes Usuário e Perfil, implementando as interfaces do Spring Security.

Criado um filter para verificação do token de acesso à API. 

Os testes automatizados precisarão ser atualizados para contemplar essas mudanças relacionadas com a segurança.
