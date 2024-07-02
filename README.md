# Bem vindo ao Videolândia 

## :computer: Sobre o projeto
Videolandia é um projeto que está sendo desenvolvido como desafio proposto pela ALURA, onde o maior desafio é colocar em prática os conhecimentos adquiridos durante a jornada de aprendizado e consolidar os conhecimentos. <br>
Nesse cenário foi proposto a criação de uma plataforma de videos com funcionalidades de cadastro, busca, atualização, deleção e também com login do usuário com autenticação.<br>
Toda semana será lançado um backlog no trello para atividade, onde simulará o dia a dia do programador. <br>
Vamos lá! 

## :scroll: História
Após alguns testes com protótipos feitos pelo time de UX de uma empresa, foi requisitada a primeira versão de uma plataforma para compartilhamento de vídeos.<br>
A plataforma deve permitir ao usuário montar playlists com links para seus vídeos preferidos, separados por categorias.<br>

Os times de frontend e UI já estão trabalhando no layout e nas telas. Para o backend, as principais funcionalidades a serem implementadas são: <br>

- API com rotas implementadas segundo o padrão REST;
- Validações feitas conforme as regras de negócio;
- Implementação de base de dados para persistência das informações;
- Serviço de autenticação para acesso às rotas GET, POST, PUT e DELETE.

 Temos um período de tempo de 4 semanas para desenvolver o projeto. Nas 3 primeiras, teremos tarefas a serem feitas e a última semana para ajustes ou para completar as tarefas pendentes. Vamos trabalhar com o sistema ágil de desenvolvimento, utilizando o Trello

 ## Segunda semana
 Depois de alguns testes com usuários, foi definido que a próxima feature a ser desenvolvida nesse projeto é a divisão dos vídeos por categoria, para melhorar a experiência de organização da lista de vídeos pelo usuário.

Dividimos a implementação dessa feat da seguinte forma:
1. Adicionar categorias e seus campos na base de dados;
2. Rotas CRUD para /categorias;
3. Incluir campo categoriaId no modelo video;
4. Escrever os testes necessários.


## Terceira semana
Nesta semana, o desafio será complementar a API adicionando paginação nas requisições de vídeos e categorias. Além disso, foi solicitado para segurança dos recursos proporcionados pela API, adicionar um método de autenticação.
Para finalizar, vamos realizar o deploy da API disponibilizando os recursos para todos.
 

## :gear: Funcionalidades
- CRUD
- Autenticação
- Segurança

## :hammer_and_wrench: Tecnologias
As seguintes tecnologias estão sendo utilizadas:

1. Java 17
2. Spring Boot 3
3. Maven
4. MySQL
5. Hibernate
6. Lombok
7. Postman
