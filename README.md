# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.1/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.6.1/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


### Instruções

* Para executar a aplicação, é necessário:
- Docker-compose
- JDK 11
- [IntelliJ](https://www.jetbrains.com/idea/), Eclipse ou IDE com plugin para o Gradle

* Importe o projeto na IDE como projeto gradle

* Iniciar MySQL localmente:
```
    # Navegue até o diretório platform/docker
    docker-compose up

    # Ou na raiz do projeto
    docker-compose -f platform/docker/docker-compose.yml up
```



### Arquitetura

A aplicação foi desenvolvida usando os princípios de proposto por Alistair Cockburn como pode ser visto no site:

https://softwarecampament.wordpress.com/portsadapters/

O objetivo é sempre proteger o domain de mudanças em componentes que não são centrais da aplicação.

Para isso temos o conceito de Port que é uma interface que os componentes de infraestrutura tem que implementar para chegar ao resultado final. Essa implementação tem o nome de adapters.

[<img src="https://softwarecampament.files.wordpress.com/2018/08/figure4.png">](http://google.com.au/)

Seguindo esse princípio, é possivel obter uma arquitetura mais desacoplada e qualquer alteração nas camadas de infraestrutura
não impactam na camada de domain onde ficam as regras de negócio o que é extremamente interessante para aplicações
com vida útil longa.

Para chegar a esse nível de desacoplamento, foi utilizado o Gradle para modularizar a aplicação e controlar a visibilidade
entre os componentes.
