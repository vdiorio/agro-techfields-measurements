## Getting Started

First, install the dependencies:

```bash
mvn install
```


```bash
mvn spring-boot:run
```

Open [http://localhost:3001](http://localhost:3001) with your browser to see the result.

## Ending Points
GET: http://localhost:3001/fotos Para listar todas as fotos do banco
POST: http://localhost:3001/fotos Para cadastrar uma nova foto no banco.

GET: http://localhost:3001/ilhas Para listar todas as ilhas
POST http://localhost:3001/ilhas Para cadastrar uma nova ilha
PUT: http://localhost:3001/ilhas/{id} Para atualizar as informações de uma ilha
DELETE: http://localhost:3001/{id} Para deletar uma ilha

GET: http://localhost:3001/ilhas/{idDaIlha}/registros Para listar todos os registros
POST http://localhost:3001/ilhas/{idDaIlha}/registros Para adicionar um registro a ilha.
PUT: http://localhost:3001/ilhas/{idDaIlha}/registros/{id} Para atualizar as informações do registro
DELETE: http://localhost:3001/{id}/{idDaIlha}/registros/{id} Para deletar um Registro