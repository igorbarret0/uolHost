<h1 align="center">
  Uol Host
</h1>

Solução desafio backend [UOL](https://github.com/Gahbr/UOL-BackEnd-Challenge)

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Rest Client](https://spring.io/blog/2023/07/13/new-in-spring-6-1-restclient)



## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/uolHost.git
```

- Construir o projeto:
```
./mvnw clean package
```


A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  PLAYER
```
POST /player - Registrar player

{
    "name": "name",
    "email": "name@email.com",
    "phone": "00009999",
    "heroGroup": "VINGADORES"
}
```

```
GET /player - Obter todos os players
```