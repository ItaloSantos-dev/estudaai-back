# API estuda-ai (Spring boot)

## Descrição
Esta é uma aplicação com backend e BD feita em spring e postgres, ela centraliza um ponto de partida de estudos para o enem e outros cursos, ela centraliza um ponta pé inicial, disponibilizando cursos, modulos, submodules links e questões para o usuário ter um ponto de partida para estudar

A API permite
* Consultar os cursos, modules submodulos, links e questões disponíveis
* Authenticação e autorização utilizando spring security e token JWT
* BD via postgres

## O projeto foi feito pensando em 
* Fornecer um ponto de partida para estudantes
* Relações etre entidade
* Solucionar um problema real
* Dockerizar aplicação usando compose para múltiplos serviços
  
## Endpoints disponíveis
# Para POST e PUT o Body tem que seguir o padrão dos DTO´s
```bash

## Para: users(menos put e delet), courses, modules, submodules, questions, links  ##
GET    | /'item'
POST   | /'item'
PUT    | /'item'/{id}
DELETE |/'item'/{id}

## Rotas de relacionamentos ##
GET    | /course/{id}/modules        -> Módulos do curso {id}
GET    | /modules/{id}/submodules    -> Submodulos do módulo {id}
GET    | /submodules/{id}/links      -> Links do submodulo {id}
GET    | /submodules/{id}/questions  -> Questions do submodulo {id}


```
### Arquitetura
O projeta utiliza a arquitetura em camadas, com DTOs, controllers, services, models e infra/security para configuração da segurança do servidor

### Como instalar
```bash
git clone https://github.com/ItaloSantos-dev/estudaai
cd estudaai
mvnw.cmd clean package -DskiptTests

-- Via docker (docker desktop aberto e docker compose instalado)
  docker compose up -d -> póssivel erro caso back suba antes do bd, aplicação morre,
  caso tenha acontecido apenas suba o compose novamente

--Via .jar/postgres (Ter java e postgres instalado)
  Acessar postgres para criar bd estudaai
  Configurar senha/ usuario no application.properties
  buldar novamente
  rodar java -jar target/estuda-ai-0.0.1-SNAPSHOT.jar
```
### Testando a aplicação
- No insomnia acesse 
  POST | http://localhost:8080/auth/login
  body{
	  "email":"admin@.com",
	  "password":"1131"
  }

  --FUTURAMENTE ADICIONAR UM FRONT END COM ANGULAR







