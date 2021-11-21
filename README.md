# API PARA GESTÃO DE LIVRARIA ONLINE

Projeto proposto para o Bootcamp de Java da ALURA CURSOS

***Observações:***
* O módulo 1 consistia em criar uma aplicação Web em Java para gestão de uma livraria online utilizando Servlet, JSP e JDBC.

* Link do repositório que contém o desenvolvimento proposto para o módulo 1: 
https://github.com/gislainemendes/bookstore

* A partir do módulo 2 o objetivo é recriar a mesma aplicação utilizando Spring Boot. 

* Link da API desenvolvida para este bootcamp - deploy realizado no Heroku:
https://bookstore-api-gislaine.herokuapp.com/swagger-ui.html



* Disponibilizo um usuário e senha para testes na API. Com este acesso é possível cadastrar, editar, consultar e deletar autores e livros. Para realizar estas operações é necessário primeiramente realizar a etapa de autenticação que irá gerar um token de acesso. Desta forma, é possível utilizar o token para realizar as demais operações.

* Informações para acesso: 
**login:** gislaine,
**senha:** 488396

* Para cadastrar um autor, por exemplo, é necessário informar a palavra Bearer + o token gerado na etapa de autenticação e também informar os dados básicos do autor no cadastro, conforme imagem a seguir:

![image](https://user-images.githubusercontent.com/63201229/142778863-c136370f-d611-403b-8f58-27a8a2dd5b54.png)



## ETAPAS PARA A CRIAÇÃO DE API REST: 

* A seguir, descrição dos requisitos utilizados para o desenvolvimento da API, organizados em módulos: 


## Módulo 2
Neste módulo foi utilizado Spring Boot para criação de uma API Rest


***Funcionalidades deste módulo:***
* Criação de cadastro e listagem de Autores;
* Criação de cadastro e listagem de Livros;


***Requisitos:***
* Criar uma API Rest utilizando o Spring Boot, gerada pelo site Spring Initializr;
* Utilizar as bibliotecas Lombok, ModelMapper e Bean Validation;
* As funcionalidades devem ser implementadas no modelo de API, consumindo e devolvendo as informações no formato JSON;
* Para o cadastro de livros:

    1- Um livro deve possuir as seguintes informações: título, data de lançamento, número de páginas e autor;

    2- Título deve ser obrigatório e ter no mínimo 10 caracteres;

    3- Data de lançamento deve ser uma data menor ou igual a data atual;

    4- O número de páginas deve ser maior ou igual a 100.
    
    
## Módulo 3 
Neste módulo foi implementada a camada de persistência na API da livraria, substituindo todo o código que simulava um banco de dados em memória.
Deve ser adicionado o módulo do Spring Data JPA, como como o driver de algum banco de dados, como por exemplo o MySQL.

Foi necessário realizar as configurações do banco via propriedades no arquivo application.properties, bem como realizar o mapeamento das entidades JPA da API.

Foi utilizado o FlyWay como ferramenta de migration, para o controle da evolução do schema do banco de dados da API. Foi necessário também utilizar o recurso de paginação e ordenação nas funcionalidades de listagem de autores e de livros.


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
* Foi criada uma classe que contém os códigos para realizar as configurações de segurança, como os detalhes de autenticação e autorização da API de maneira Stateless, criadas também as classes Usuário e Perfil, implementando as interfaces do Spring Security.

Criado um filter para verificação do token de acesso à API. 

Os testes automatizados precisarão ser atualizados para contemplar essas mudanças relacionadas com a segurança.


## Módulo 6
Neste módulo foi realizado deploy da API do projeto livraria no Heroku utilizando Docker.
Também foi configurado o GitHub Actions, para implementar os conceitos de integração e deploy contínuos.


***Requisitos***
* Foi necessário criar o arquivo DockerFile no projeto, além de realizar as configurações das variáveis de ambiente para que o deploy fosse realizado sem problemas no Heroku.
Também foi necessário criar o arquivo .github/workflows/ci-testes.yml para realizar as configurações do GitHub Actions no projeto e com isso conseguir disparar o processo de execução automatizada dos testes, bem como o deploy automatizado no Heroku sempre que um push com commits for enviado para este repositório no GitHub.


***Funcionalidades deste módulo***
* Além de implementar os recursos citados anteriormente, foi implementada a funcionalidade de envio de email, de maneira assíncrona, ao cadastrar um novo usuário na API.
Foram criadas duas classes para o envio de emails, sendo que uma delas foi ativada apenas em ambiente de produção e a outra, que não envia email de verdade e apenas imprime as informações do email no console, foi ativada nos ambientes de desenvolvimento e testes. 



## Encerramento
* O módulo 6 encerra o projeto proposto para o Bootcamp Java da ALURA CURSOS.


***Conclusão***

Considero que participar deste bootcamp foi uma ótima experiência. Pude ter contato com diversos recursos usados na construção de uma API Rest, e realizar desafios que simulam de maneira muito próxima o dia a dia de um desenvolvedor backend. 
