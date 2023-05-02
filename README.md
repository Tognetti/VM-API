[Link para acesso](https://vm-api-production.up.railway.app/)

## Credencial padrão

Usuário: adolfo<br/>
Senha: 123

Configuração da autenticação é feita na classe **SecurityConfiguration**

## Endpoints

| Método  | URI | Descrição | Parâmetros opcionais
| ------------- | ------------- | -----------| -----------
| POST  | /usuarios  | Cria um usuário |
| GET  | /usuarios  | Retorna todos os usuários em formato usado para paginação, filtrando pelo nome caso preenchido | nome: Filtra resultado pelo nome dos usuários<br/> page: Determina qual página será retornada<br/> size: Determina qual o tamanho da página retornada<br/>
| GET  | /usuarios/:id | Retorna usuário com o id especificado |

Exemplo de body para requisição `POST /usuarios`:
```
{
    "nome": "João",
    "senha": "123",
    "email": "joao@gmail.com"
}
```

## Análise de possibilidades para front-end

A escolha da tecnologia usada na implementação do front-end da aplicação 
depende de alguns fatores. 

Considerando a complexidade mais baixa do projeto desenvolvido nesta atividade
o uso de **JavaScript** "puro", sem nenhum framework, pode ser uma escolha válida. Neste caso
o tempo de desenvolvimento provavelmente será menor e não será necessário o conhecimento de frameworks específicas pelos
desenvolvedores.

Porém, caso o projeto se torne mais complexo e com mais funcionalidades o uso de um framework no front-end pode se tornar necessário. 
As opções mais populares são: **React, Angular e Vue.js**. A escolha depende de pontos como as funcionalidades e requisitos específicos do projeto, experiências anteriores da 
equipe de desenvolvimento, curva de aprendizado das tecnologias, e quantidade e qualidade da documentação disponível.
