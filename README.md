# emprestimo-microservice
Este projeto é um microserviço de gerenciamento de empréstimos desenvolvido em Java com Spring Boot. Ele inclui operações de CRUD para pessoas e empréstimos, validações de identificadores e integração com Kafka.


## Link para analisar o system design
https://excalidraw.com/#json=JzVtjeFzkjUte7LjAIjY3,9s98r-DwyxZjnMMQuuP2Ew

## Pré-requisitos

- **Java 17+**
- **Maven**
- **Docker e Docker Compose**
- **Kafka** (instalado via Docker Compose)

## Como Executar a Aplicação

Siga os passos abaixo para configurar e rodar a aplicação:

### 1. Configurar e Executar os Serviços com Docker Compose

Primeiro, vamos configurar os serviços necessários (PostgreSQL, Kafka, etc.) usando o Docker Compose.

1. Certifique-se de que o Docker e o Docker Compose estejam instalados e em execução na sua máquina.
2. Navegue até o diretório raiz do projeto onde está localizado o arquivo `docker-compose.yaml`.
3. Execute o comando abaixo para iniciar os serviços:

```bash
1. docker-compose up -d
2. mvn clean install
3. mvn spring-boot:run
4. java -jar target/emprestimo-microservice-0.0.1-SNAPSHOT.jar

