<p align="center">
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/ae0723a5cdc64e651feb224440760adb710a2965/Imagens/DescontOn.png" height="90" width="380" />
</p>
<p align="center"> Ferramenta flexível para criações de promoções em E-commerce. </p>

![Badge](https://img.shields.io/badge/STATUS-DESENVOLVIMENTO-yellow?style=flat-square&logo=)


## Tabela de Conteúdos

 * [Descrição](#descrição)
 * [Documentação](#documentação)
 * [Pré-requisitos e como executar a aplicação](#pré-requisitos-e-como-executar-a-aplicação)
 * [Funcionalidades desenvolvidas](#funcionalidades-desenvolvidas)
 * [Ilustração das funcionalidades](#ilustração-das-funcionalidades)
 * [Demonstração](#demonstração)
 * [Manual do Usuário](#manual-do-usuário)
 * [Backlog do Produto](#backlog-do-produto) 
 * [Story Cards](#story-cards)  
 * [User Story](#user-story)   
 * [Roadmap](#roadmap)
 * [Gráfico de Burndown](#gráfico-de-burndown)
 * [Cronograma do API](#cronograma-do-api)
 * [Tecnologias](#tecnologias)
 * [Team](#team)
 * [Licença](#licença)

## Descrição

Ferramenta para criar promoções de E-commerce, onde as mecânicas de promoções são feitas de forma flexível e de rápida atualização no sistema. As regras de promoções são cadastradas e posteriormente aplicadas no momento em que os itens sao adicionados ao carrinho. 
Atualmente implementamos e apresentaremos o cadastro dos produtos no servidor/ banco de dados e também como são adicionados na sacola de compras, para conferência e ajuste das quantidades e valores. Além da autonomia fornecida ao usuário para editar, remover, arquivar ou desarquivar seus produtos e da visualização prática e intuitiva dos produtos cadastrados através da listagem que possui um filtro para que seja possível diferenciar quais produtos estão disponíveis e quais produtos estão arquivados.

### Model Canvas do Projeto

<img src="https://github.com/DolphinDatabase/DescontOn/blob/f329e96e01693d44285ebd7471af6f972be88e68/Imagens/CANVAS.png">

## Documentação
  
  <details><summary>Modelo Conceitual</summary>
  
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/e84bd638cce1a75e6eb95c946bf9f7ee4ac3e9d0/Imagens/Modelo_Conceitual.png">
 
  </details>
  
  <details><summary>Modelo Relacional</summary>
  
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/e84bd638cce1a75e6eb95c946bf9f7ee4ac3e9d0/Imagens/Modelo_Relacional.png">
  
  </details>
  
![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) 
Para acessar a documentação completa do projeto, clique [aqui](https://github.com/DolphinDatabase/DescontOn/blob/b17cc62fbc310e60c331b61d3ee07657d79513d5/Documenta%C3%A7%C3%A3o/Documenta%C3%A7%C3%A3oDolphinDatabase.pdf).

## Pré-requisitos e como executar a aplicação
 
 <details><summary>Pré-requisitos</summary>
 
* Instalação do [Docker](https://docs.docker.com/desktop/windows/install/) 
* Instalação [Java](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-E3C75F92-D3B2-421D-A9BE-933C15F7CD1B)
* IDE - sugestão [Eclipse](https://www.eclipse.org/downloads/) 
* Project [Lombok](https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/)  (adicionado pelo Maven) 

</details>

<details><summary>Repositório</summary>

```bash 
$ git clone https://github.com/DolphinDatabase/DescontOn/tree/main
```
</details>

<details><summary>SETUP do Banco de Dados</summary>
  
```bash
# via docker
docker pull cembranelli/descontondatabase:1.0

docker run -d -it --name oracle-container -p 1521:1521 cembranelli/descontondatabse:1.0

docker exec -it oracle-container /bin/bash -l -c "sqlplus / as sysdba"

connect sys as sysdba;

alter session set "_ORACLE_SCRIPT"=true;

create user ADMIN identified by ADMIN;

GRANT ALL PRIVILEGES TO ADMIN;
```

</details>

Com o SETUP do Banco de Dados pronto, importe a pasta _“ecommerce”_
como [_“Existing Maven Project”_](https://medium.com/@alex.girao/importar-um-projeto-maven-spring-boot-ea10078b2bde) na IDE. Por fim, execute a _Classe Main_
_“EcommerceApplication”_, acesse no navegador o endereço:
_localhost:8080/administrativo_

**IMPORTANTE:** Usuário e senha do banco de dados já estão definidos como
_ADMIN e ADMIN_, não é necessário fazer alterações

![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) 
Para acessar o manual do usuário, clique [aqui](https://github.com/DolphinDatabase/DescontOn/blob/57276b22f2573393d75bf36c32f89e020ee80481/Manual/Manual%20do%20Usuario_sprint1%20-%20DescontOn.pdf).

## Funcionalidades desenvolvidas 
- [x] Cadastro de Produtos
- [x] Listar Produtos
- [x] Remover Produtos Cadastrados
- [x] Arquivar e Desarquivar Produtos Cadastrados (para armazenar dados dos produtos que já foram comprados)
- [x] Filtrar a Listagem de Produtos (produtos disponíveis ou arquivados)
- [x] Editar um Produto Cadastrado
- [x] Adicionar Produtos na Sacola
- [x] Alterar a Quantidade de Produtos na Sacola
- [x] Remover Produtos da Sacola
- [x] Finalizar Compra

![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) Para mais informações das funcionalidades entregues, acesse a [release](https://github.com/DolphinDatabase/DescontOn/releases/tag/sprint1).

## Ilustração das funcionalidades

Para acessar nossos *wireframes*, clique [aqui](https://www.figma.com/file/DfQM30USXikeDM0jaHwQ9T/Untitled?node-id=17%3A24).
 
## Demonstração
 
Para acessar o vídeo[^1] de demonstração da aplicação em uso, clique abaixo:

[<img src="https://github.com/DolphinDatabase/DescontOn/blob/47f0f23ee3d7710b472fc1ff26d06da50237681e/Imagens/imagem_2022-04-15_155641874.png" width="40%">](https://www.youtube.com/watch?v=n5GK4uJpNkk&t=1s "DescontOn vídeo Demonstração")

## Manual do Usuário

Para acessar o manual do usuário, clique [aqui](https://github.com/DolphinDatabase/DescontOn/blob/c5cdb4cb728824b77bcf054276af7143d2f545cb/Manual/Manual%20do%20Usuario_sprint1%20-%20DescontOn.pdf).

## Backlog do Produto

- [x] ![EPIC](https://github.com/DolphinDatabase/DescontOn/blob/4502e6020cfe3e2e89c17adb2fa5473c842d215d/Imagens/EPIC%20(1).svg) **SPRINT 1:**  Base da ferrramenta
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Cadastro de Produtos
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Listagem de Produtos
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Sacola de Compras
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Alterar a Quantidade de Produtos na Sacola
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Finalizar uma Compra
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Remover um Item da Sacola
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Remover um Produto Cadastrado
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Editar um Produto Cadastrado
- [x] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Filtrar Produtos Disponíveis e Arquivados
- [ ] ![EPIC](https://github.com/DolphinDatabase/DescontOn/blob/4502e6020cfe3e2e89c17adb2fa5473c842d215d/Imagens/EPIC%20(1).svg) **SPRINT 2:** Promoções
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Tipo de Desconto: Simples
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Tipo de Desconto: Progressivo
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Tipo de Desconto: Brinde
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Tipo de Desconto: Menor Valor
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Listar Promoções
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Editar Promoções
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Remover Promoções
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Filtrar Promoções Ativas e Interrompidas
- [ ] ![EPIC](https://github.com/DolphinDatabase/DescontOn/blob/4502e6020cfe3e2e89c17adb2fa5473c842d215d/Imagens/EPIC%20(1).svg) **SPRINT 3:**  Recomendar Produtos
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Categoria
- [ ] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Mais Vendidos


## Story Cards

<p align="center">
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/6fa887b9e924f9a573776f546e20421438901d9e/Imagens/STORYGIF.gif"/>
</p>

## User Story
  
<details><summary>Story 1 - Cadastro de Produtos</summary>

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Visualizar os produtos que foram cadastrados em uma lista. | Seja possível conferir as informações do produto e adicioná-los na sacola. |
  
</details>

<details><summary>Story 2 - Listagem de Produtos</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Adicionar produtos no meu carrinho de compras. | Seja possível listar os itens escolhidos para conferir e ajustar a quantidade, subtotal e os descontos que refletirão no valor final. |
  
</details>


<details><summary>Story 3 - Sacola de Compras</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Adicionar produtos no meu carrinho de compras. | Seja possível listar os itens escolhidos para conferir e ajustar a quantidade, subtotal e os descontos que refletirão no valor final. |
  
</details>


<details><summary>Story 4 - Alterar a Quantidade de Produtos na Sacola</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Alterar a quantidade de produtos na sacola. | Seja possível comprar mais itens sem precisar retornar a lista de produtos. |
  
</details>

<details><summary>Story 5 - Finalizar uma Compra</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Finalizar uma compra. | Seja possível efetuar seu fechamento. |
  
</details>

<details><summary>Story 6 - Remover um Item da Sacola</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Remover um item da sacola. | Exista a possibilidade de exclusão, caso não deseje mais comprar determinado produto. |
  
</details>

<details><summary>Story 7 - Remover um Produto Cadastrado</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Remover um item cadastrado. | Exista a possibilidade de exclusão, caso as informações do produto estejam erradas ou não  seja mais vendido. |
  
</details>


<details><summary>Story 8 - Editar um Produto Cadastrado</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Editar um produto cadastrado. | Seja possível alterar as informações do produto, como nome e valor. |

</details>

<details><summary>Story 9 - Filtrar Produtos Disponíveis e Arquivados</summary>
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. |  filtrar os produtos disponíveis ou arquivados.|  seja possível visualizar quais produtos estão disponíveis e quais produtos estão arquivados. |
  
</details>

## Roadmap

Para acessar nosso *Roadmap*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/API/boards/1/roadmap).

## Gráfico de Burndown

Para acessar nosso *Burndown*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/API/boards/1/reports/burndown).

## Cronograma do API
 
| Data | Evento |
| -------| --------- |
| 16/03 às 19h  | Project kick-off. |
| 24/03 a 14/04 | [Sprint 1](https://github.com/DolphinDatabase/DescontOn/blob/b9ffbd159b0b61985e81af19b72bf1dd6d6a977d/Sprints/SPRINT1.md). |
| 25/04 a 15/05 | [Sprint 2](#). |
| 16/05 a 05/06 | [Sprint 3](#). |
| 15/06 às 19h | Feira de Soluções. |

## Tecnologias

<details><summary>Organização e Comunicação</summary>
  
<a href="https://dolphin-database.atlassian.net/jira/software/projects/API/boards/1" target="_blank">![Jira](https://img.shields.io/badge/Jira-blue?style=flat-square&logo=jira) </a>
<a href="https://slack.com/intl/pt-br/" target="_blank"><img src="https://img.shields.io/badge/-Slack-E01563?style=flat-square&logo=Slack&logoColor=white"/></a>
<a href="https://www.notion.so/pt-br" target="_blank"><img src="https://img.shields.io/badge/-Notion-000000?style=flat-square&logo=Notion&logoColor=white"/></a><br/>
<a href="https://www.figma.com" target="_blank">![Figma](https://img.shields.io/badge/Figma-lightgray?style=flat-square&logo=figma)
</a><br/>
  
</details>

<details><summary>Linguagens</summary>

<a href="https://www.java.com/pt-BR/download/help/java8_pt-br.html" target="_blank">![Java](https://img.shields.io/badge/-java-E34A86?style=flat-square&logo=java)</a>
<img width="55" height="20" src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
<img width="55" height="20" src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"/><br/>
![SQL](https://img.shields.io/badge/SQL-blue?style=flat-square&logo=sql)
<a href="https://www.javascript.com" target="_blank">![JS](https://img.shields.io/badge/JavaScript-lightgrey?style=flat-square&logo=javascript)</a>

</details>

<details><summary>Framework</summary>

<a href="https://boxicons.com" target="_blank">![Boxicons](https://img.shields.io/badge/Boxicons-brown?style=flat-square&logo=hackthebox)</a>
<a href="https://www.thymeleaf.org" target="_blank">![Thymeleaf](https://img.shields.io/badge/Thymeleaf-darkgreen?style=flat-square&logo=thymeleaf)</a>
<a href="https://getbootstrap.com" target="_blank"><img src="https://img.shields.io/badge/Bootstrap-563D7C?style=flat-square&logo=bootstrap&logoColor=white"/></a><br/>
<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=spring&logoColor=white"/></a>

</details>

<details><summary>Bibliotecas</summary>

<a href="https://jquery.com" target="_blank"><img width="65" height="20" src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"/></a>

</details>

<details><summary>SGBD</summary>

<a href="https://www.oracle.com/br/downloads/">![Oracle](https://img.shields.io/badge/Oracle-red?style=flat-square&logo=oracle)</a>

</details>

<details><summary>Plataforma</summary>

<a href="https://www.docker.com">![Docker](https://img.shields.io/badge/Docker-gray?style=flat-square&logo=docker)</a>

</details>

<details><summary>Board</summary>

Para acessar nosso Board escaneie o *QR Code* abaixo:
<p align="left">
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/4d4fa096757fc676386f115b4d18f4a2106f5464/Imagens/QR%20CODE.png" height="150" width="150"/>
</p>

 </details>
 
## Team

<a href="https://github.com/beamedeiros" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/7c2c3004a0b81c5232b0d5b0929eae8ffcb4c6ed/Imagens/Team/BeatrizMedeiros.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/gui-akinyele" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/30b6ebe8706a4ed9424e32da5fcf4ed6574e3fe3/Imagens/Team/GuilhermeAkinyele.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/stephani-ss" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/30b6ebe8706a4ed9424e32da5fcf4ed6574e3fe3/Imagens/Team/StephaniSoares.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Borgarelli" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/30b6ebe8706a4ed9424e32da5fcf4ed6574e3fe3/Imagens/Team/KauaBorgarelli.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Jose0588" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/b6926b10f7e45d67139f2420d6490ba39ebc2b08/Imagens/Team/JoseMaria.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/camilaffpacheco" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/24d29d4cdb3e00acc447f5a3cc9bb67f1934867c/Imagens/Team/CamilaPacheco.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/pdrMottaS" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/24d29d4cdb3e00acc447f5a3cc9bb67f1934867c/Imagens/Team/PedroMotta.png" height="65" width="65" align="left" target="_blank" ></a> <br/>
<br></br>
Para mais informações[^2], clique [aqui](https://github.com/DolphinDatabase/API3/wiki/Development-Team).
<br></br>

## Licença  

Este projeto esta sob licença [MIT](https://github.com/DolphinDatabase/SGBD_Health/blob/main/LICENSE).<br/>
<a href="https://github.com/dolphindatabase" target="_blank" align="center">![Badge](https://img.shields.io/badge/MADE&nbsp;by&nbsp;DolphinDatabase-grey?style=for-the-badge&logo=dev.to)</a>

[^1]: Vídeo produzido e editado pelos integrantes do grupo.
[^2]: Equipe responsável pelo desenvolvimento do Projeto Integrador.


