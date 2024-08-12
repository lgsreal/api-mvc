# api-mvc

Projeto de API RESTful + MVC
## Tecnologias

- [Java 21](https://docs.oracle.com/en/java/javase/21/)
- [GraalVM](https://www.graalvm.org/)
- [Gradle](https://gradle.org/)
- [Thymeleaf](https://www.thymeleaf.org/index.html)
- [Spring Boot 3.3.2](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/)
- [Spring Web Starter](https://docs.spring.io/spring-boot/reference/web/index.html)
- [Spring Validation Starter](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [Spring HATEOAS](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
- [Springdoc OpenAPI + Swagger](https://springdoc.org/)
- [H2 Database](http://h2database.com/html/main.html)
- [REST](https://ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
- [DTO](https://martinfowler.com/eaaCatalog/dataTransferObject.html)

## Ferramentas

- [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
- [Postman](https://www.postman.com/)
## API Reference

#### Obter todos os livros

```http
  GET /livros
```

#### Gravar livro

```http
  POST /livros
```

Body:

```json
{
  "nome": "Exemplo",
  "preco": 20.00,
  "categoria": "ROMANCE",
  "autoria": "Fulano"
}
```

| Parâmetro   | Tipo         | Descrição                        |
|:------------|:-------------|:---------------------------------|
| `nome`      | `String`     | Nome do livro a ser gravado      |
| `preco`     | `BigDecimal` | Preço do livro a ser gravado     |
| `categoria` | `Categoria`  | Categoria do livro a ser gravado |
| `autoria`   | `String`     | Autoria do livro a ser gravado   |