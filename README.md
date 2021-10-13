# Star Wars Network

## Servidor de aplicação
Foi configurado o projeto em um servidor externo, para facilitar os testes da equipe. 

Link para testes de requisições a API: `https://star-wars-network.herokuapp.com`

## Resumo
A aplicação foi realizada na linguagem Java, utilizado o framework Spring Boot na versão 2.5.5 e Gradle para controle de dependências. Junto ao Spring, foi utilizado o framework Hibernate, para realizar o mapeamento das classes Java e conversão em tabelas no banco de dados. A API REST desenvolvida possui endpoints com métodos dos tipos POST, PATCH e GET, para criação, alteração e busca de dados.

## Executar projeto
No CMD, para o sistema operacional Windows, acesse o pacote .jar do projeto e execute o seguinte comando para rodar o código compilado:
`java -jar starwars-0.0.1-SNAPSHOT.jar`

## Banco de dados
Para facilidade nos testes, foi utilizado o banco de dados H2.

## Endpoints
* Os métodos descritos na continuidade podem ser escritos completando o link inicial deste documento. 

API: `https://star-wars-network.herokuapp.com/api`

### GET
Buscar rebelde específico : `/rebels/{id}`

Buscar estatística de rebeldes: `/statistics/rebels`

Buscar estatística de traidores: `/statistics/traitors`

Buscar estatística de itens: `/statistics/items`

### POST
Cadastrar rebelde: `/rebels`

Reportar traidor: `/rebels/{id}/reports`

### PATCH
Negociar itens: `/rebels/{id}/negotiations`

Alterar localização: `/rebels/{id}/locations`

## Tratamentos de erros da API
### Rebelde
- Nenhum resultado foi encontrado para este ID!
- Você já reportou este traidor!
- Itens insuficientes para pagamento!
- Itens insuficientes para venda!
- Soma de pontos para compra e venda incompatíveis!

## Tratamentos de sucesso da API
- Rebelde cadastrado com sucesso!
- Traidor reportado com sucesso!
- Localizacao alterada com sucesso!

## Exemplos de uso
### Cadastrar Rebelde
```
POST:
Endpoint: https://star-wars-network.herokuapp.com/api/rebels

Corpo:
  {
    "name": "Tiago",
    "age": 24,
    "gender": "MALE",
    "location": {
        "lat": -23.569265,
        "lng": -46.6944947,
        "galaxyName": "Let's Code Galaxy"
    },
    "inventory": [
        {
            "item": "GUN",
            "quantity": 2
        },
        {
            "item": "MUNITION",
            "quantity": 30
        },
        {
            "item": "WATER",
            "quantity": 3
        },
        {
            "item": "FOOD",
            "quantity": 4
        }
    ]
  }

Retorno:
  {
    "message": "Rebelde cadastrado com sucesso!",
    "object": {
        "id": 2,
        "name": "Tiago",
        "age": 24,
        "gender": "MALE",
        "location": {
            "lat": -23.569265,
            "lng": -46.6944947,
            "galaxyName": "Let's Code Galaxy"
        },
        "inventory": [
            {
                "item": "GUN",
                "quantity": 2
            },
            {
                "item": "MUNITION",
                "quantity": 30
            },
            {
                "item": "WATER",
                "quantity": 3
            },
            {
                "item": "FOOD",
                "quantity": 4
            }
        ],
        "links": [
            {
                "rel": "self",
                "href": "https://star-wars-network.herokuapp.com/api/rebels/2"
            }
        ]
    }
  }
```

O arquivo JSON com todas as requisições de exemplo pode ser encontrado no seguinte link: https://drive.google.com/file/d/1mN93WzJtQqNyACbqG1ozr-EgAb_QkZj3/view?usp=sharing

Para utilizar é necessário realizar a importação do arquivo da collection no postman ou outro programa semelhante.
