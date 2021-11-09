# aeronave-api

## Sobre este Projeto
Este é o back-end do [aeronave-aplicacao](https://github.com/filiperdt/aeronave-aplicacao).

O objetivo desta API REST é gerenciar cadastro de aeronaves.

O projeto faz parte do meu portfólio pessoal.

## Começando
No PostgreSQL, instale o dicionário de pesquisa de texto unaccent, que remove acentos, com o seguinte comando:
```
  CREATE EXTENSION unaccent;
```

Se você receber um erro como:
```
  ERROR: could not open extension control file
  "/usr/share/postgresql/<version>/extension/unaccent.control": No such file or directory
```

Instale o pacote "postgresql contrib" em seu servidor de banco de dados, com o seguinte comando:
```
  sudo apt-get install postgresql-contrib-9.1
```

Adapte ao número da sua versão. Aqui está a lista de pacotes atualmente disponíveis (https://packages.debian.org/search?keywords=postgresql-contrib).

## Rotas

> O caminho base para a API é /aeronave

### Rota de Teste

- **Esta é a rota que você pode usar para verificar se a API está funcionando corretamente.**

> http://localhost:8080/aeronave

| ENDPOINT | Método | Parâmetros | Parâmetros de URL | Resposta de Sucesso | Resposta de Erro
|--|--|--|--|--|--|
| /aeronaves | `GET`  | - | - |**Code:** 200 - OK<br>**Content:** [ [Aeronave](#valor-de-exemplo-de-resposta) ]  | - |
| /aeronaves | `POST` | [Aeronave](#parâmetros-de-aeronave) | - |**Code:** 201 - CREATED <br> **Content:** [Aeronave](#valor-de-exemplo-de-resposta) |  **Code:** 400 - BAD REQUEST<br>**Content:** [Mensagem de erro 400](#parâmetros-de-resposta-do-erro-400-de-aeronave)|
| /aeronaves/:id | `GET` | - | id=Long |**Code:** 200 - OK <br> **Content:** [Aeronave](#valor-de-exemplo-de-resposta) |  **Code:** 404 - NOT FOUND<br>**Content:** [Mensagem de erro 404](#parâmetros-de-resposta-do-erro-404-de-aeronave)|
| /aeronaves/:id | `PUT` | [Aeronave](#parâmetros-de-aeronave) | id=Long |**Code:** 200 - OK <br> **Content:** [Aeronave](#valor-de-exemplo-de-resposta) | **Code:** 400 - BAD REQUEST<br>**Content:** [Mensagem de erro 400](#parâmetros-de-resposta-do-erro-400-de-aeronave)<br>*or*<br>**Code:** 404 - NOT FOUND<br>**Content:** [Mensagem de erro 404](#parâmetros-de-resposta-do-erro-404-de-aeronave)|
| /aeronaves/:id | `DELETE` | - | id=Long |**Code:** 200 - OK <br> **Content:** [Sucesso na exclusão de Aeronave](#parâmetros-de-sucesso-na-exclusão-de-aeronave) |  **Code:** 404 - NOT FOUND<br>**Content:** [Mensagem de erro 404](#parâmetros-de-resposta-do-erro-404-de-aeronave)|
| /aeronaves/find/:termo | `GET` | - | termo=String |**Code:** 200 - OK <br> **Content:** [Aeronave](#valor-de-exemplo-de-resposta)|
| /aeronaves/quantidade-nao-vendida | `GET` | - | - |**Code:** 200 - OK <br> **Content:** Long|
| /aeronaves/quantidade-por-decada | `GET` | - | - |**Code:** 200 - OK <br> **Content:** <Integer, Long>|
| /aeronaves/quantidade-por-marca | `GET` | - | - |**Code:** 200 - OK <br> **Content:** <String, Long>|
| /aeronaves/registradas-ultima-semana | `GET` | - | - |**Code:** 200 - OK <br> **Content:** Long|
| /aeronaves/marcas | `GET` | - | - |**Code:** 200 - OK <br> **Content:** [String]|

### CRUD Aeronave
> - GET /aeronaves - Listar todas as Aeronaves
> - POST /aeronaves - Criar uma Aeronave (Necessário fornecer uma [Aeronave](#parâmetros-de-aeronave), dentro do corpo da solicitação)
> - GET /aeronaves/:id - Exibir uma única Aeronave
> - PUT /aeronaves/:id - Atualizar uma Aeronave (Necessário fornecer os campos de [Aeronave](#parâmetros-de-aeronave) a ser atualizado, dentro do corpo da solicitação)
> - DELETE /aeronaves/:id - Excluir uma única Aeronave
> - GET /aeronaves/find/:termo - Listar todas as Aeronaves pesquisadas pelo termo
> - GET /aeronaves/quantidade-nao-vendida - Pega a quantidade de Aeronaves não vendidas
> - GET /aeronaves/quantidade-por-decada - Pega a quantidade de Aeronaves por década
> - GET /aeronaves/quantidade-por-marca - Pega a quantidade de Aeronaves por marca
> - GET /aeronaves/registradas-ultima-semana - Pega a quantidade de Aeronaves registradas na última semana

#### Parâmetros de Aeronave
nome

marca

ano

descricao

vendido

#### Modelo para requisição
```
Aeronave {
  nome (String, required),
  marca (String, required),
  ano (Integer, required),
  descricao (String, required),
  vendido (boolean, required)
}
```

#### Valor de exemplo de requisição
```json
{
  "nome": "String",
  "marca": "String",
  "ano": "Integer",
  "descricao": "String",
  "vendido": "boolean"
}
```

#### Valor de exemplo de resposta
```json
{
  "id": "Long",
  "nome": "String",
  "marca": "String",
  "ano": "Integer",
  "descricao": "String",
  "vendido": "boolean",
  "created": "String",
  "updated": "String"
}
```

### Conteúdo das respostas de Sucesso e de Erro
#### Parâmetros de sucesso na exclusão de Aeronave
> *erro*: false

> *message*: "Aeronave #*id* excluída com sucesso!"

#### Parâmetros de resposta do erro 400 de Aeronave
> *erro*: true

Os parâmetros abaixo existirão apenas se houver erro no preenchimento de seus respectivos campos no formulário:
> *nome*: Seu valor pode ser:
> - "Nome não pode ser null"; *ou*
> - "Nome é obrigatório".

> *marca*: Seu valor pode ser:
> - "Marca não pode ser null".

> *ano*: Seu valor pode ser:
> - "Ano não pode ser null"; *ou*
> - "Ano não pode ser negativo".

> *descricao*: Seu valor pode ser:
> - "Descrição não pode ser null"; *ou*
> - "Descrição é obrigatório".

> *vendido*: Seu valor pode ser:
> - "Vendido não pode ser null".

#### Parâmetros de resposta do erro 404 de Aeronave
> *erro*: true

> *message*: Seu valor será:
> - "Aeronave #*id* não encontrada no banco de dados".

#### Possíveis parâmetros das respostas de Sucesso e de Erro
```
"erro": "boolean",
"message": "String",
"nome": "String",
"marca": "String",
"ano": "String",
"descricao": "String",
"vendido": "String"
```
