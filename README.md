# Executar projeto:

1 - Clonar projeto:

```
https://github.com/MarhlonKorb/gerenciador-estacionamento.git
```

2- Instalar o Docker Hub;

[Link da aplicação para cada plataforma](https://www.docker.com/get-started/)

3 - Ter uma IDEA instalada e rodar comando para gerar artefato da aplicação:

```
mvn package
```

4 - Rodar comando para executar leitura dos arquivos Docker para gerar os containers:

```
docker compose up
```

 5 - Acessar URL: [URL para verificar se aplicação está sendo executada](http://localhost:8080/actuator/health)

## [Documentação](http://localhost:8080/swagger-ui/index.html)
