# API PARA GESTÃO DE LIVRARIA ONLINE

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
    
    
**Módulo 3 -** Neste módulo foi implementada a camada de persistência na API da livraria, substituindo todo o código que simulava um banco de dados em memória.
Deve ser adicionado o módulo do Spring Data JPA, como como o driver de algum banco de dados, como por exemplo o MySQL.

Será necessário realizar as configurações do banco via propriedades no arquivo application.properties, bem como realizar o mapeamento das entidades JPA da API.

Deverá ser utilizado o FlyWay como ferramenta de migration, para o controle da evolução do schema do banco  de dados da API. Necessário também utilizar o recurso de paginação e ordenação nas funcionalidades de listagem de autores e de livros.


***Funcionalidades deste módulo:***
* Foi implementada uma nova funcionalidade: relatório de quantidade de livros publicados por autor;


***Requisitos:***
* Essa nova funcionalidade deverá retornar um JSON com os dados do relatório para que a aplicação FrontEnd consiga fazer a geração de um gráfico, similar ao demonstrado na seguinte figura: 

![image](https://user-images.githubusercontent.com/63201229/136712891-bf45a9e6-84df-43a9-b836-260d1ffb64d8.png)

