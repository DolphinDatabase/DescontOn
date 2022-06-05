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
 * [Roadmap](#roadmap)
 * [Gráfico de Burndown](#gráfico-de-burndown)
 * [Cronograma do API](#cronograma-do-api)
 * [Tecnologias](#tecnologias)
 * [Team](#team)
 * [Licença](#licença)

## Descrição
<p align="justify">
Ferramenta para criar promoções de E-commerce, onde as mecânicas de promoções são feitas de forma flexível e de rápida atualização no sistema. As regras de promoções são cadastradas e posteriormente aplicadas no momento em que os itens são adicionados ao carrinho. 
Atualmente implementamos e apresentaremos o cadastro dos produtos e promoções no servidor/ banco de dados e também como são adicionados na sacola de compras, para conferência e ajuste das quantidades e valores. Além da autonomia fornecida ao usuário para editar, remover, arquivar ou desarquivar seus produtos e para editar, deletar, interromper ou ativar promoções e da visualização prática e intuitiva dos produtos e promoções cadastradas através da listagem que possui um filtro para que seja possível diferenciar quais os status de produtos e promoções.

### Model Canvas do Projeto

<img src="Documentação/SPRINT3/Imagens/CANVAS.png">

## Documentação
  
![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) 
Para acessar a documentação completa do projeto, clique [aqui](Documentação/DocumentaçãoDolphinDatabase.pdf).

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
Para acessar o manual do usuário, clique [aqui](Manual/ManualdoUsuario_sprint2-DescontOn.pdf).

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
- [x] Cadastro de Promoções
- [x] Listar Promoções
- [x] Remover Promoções
- [x] Ativar ou Interromper Promoções
- [x] Filtrar a Listagem de Promoções (promoções ativas ou interrompidas)
- [x] Editar uma Promoção
- [x] Aplicar Descontos na Sacola de Compras
- [x] Criar Mecânicas com Modelos Lógicos
- [x] Cadastrar Produtos em (N) Promoções
- [x] Listar desconto por produto na Sacola de Compras
- [x] Visualizar promoções por produto na Sacola de Compras


![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) Para mais informações das funcionalidades entregues, acesse a [release](https://github.com/DolphinDatabase/DescontOn/releases/tag/sprint2).

## Ilustração das funcionalidades

Para acessar nossos *wireframes*, clique [aqui](https://www.figma.com/file/DfQM30USXikeDM0jaHwQ9T/Untitled?node-id=17%3A24).
 
## Demonstração
 
Para acessar o vídeo[^1] de demonstração da aplicação em uso, clique [aqui](https://youtu.be/lNfCtBOqPcc):

[<img src="https://github.com/DolphinDatabase/DescontOn/blob/47f0f23ee3d7710b472fc1ff26d06da50237681e/Imagens/imagem_2022-04-15_155641874.png" width="40%">](https://youtu.be/lNfCtBOqPcc "DescontOn vídeo Demonstração")

## Manual do Usuário

![ALERTA](https://github.com/DolphinDatabase/DescontOn/blob/7a4ebd6388cc21faa5963f4bd59ef15fd880fbc0/Imagens/alerta.svg) Para acessar o manual do usuário, clique [aqui](Manual/ManualdoUsuario_sprint2-DescontOn.pdf).

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
- [X] ![EPIC](https://github.com/DolphinDatabase/DescontOn/blob/4502e6020cfe3e2e89c17adb2fa5473c842d215d/Imagens/EPIC%20(1).svg) **SPRINT 2:** Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Cadastrar Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Listar Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Editar Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Remover Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Descontos na Sacola
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Filtrar Promoções Ativas e Interrompidas
- [X] ![EPIC](https://github.com/DolphinDatabase/DescontOn/blob/4502e6020cfe3e2e89c17adb2fa5473c842d215d/Imagens/EPIC%20(1).svg) **SPRINT 3:**  Melhorias para o Usuário
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Criar Mecânicas com Modelos Lógicos
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Verificar Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Criar Novas Condições/Ações de Promoções
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Exibir o Desconto e Promoção de Cada Produto na Sacola de Compras
- [X] ![STORY](https://github.com/DolphinDatabase/DescontOn/blob/7ccc37184d9e7dda6469c7420ca16ba0b2337816/Imagens/STORY%20(1).svg) Cadastrar Produtos em (N) Promoções

  
## Roadmap

Para acessar nosso *Roadmap*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/API/boards/1/roadmap).

## Gráfico de Burndown

Para acessar nosso *Burndown*, clique [aqui](https://dolphin-database.atlassian.net/jira/software/projects/API/boards/1/reports/burndown).

## Cronograma do API
 
| Data | Evento |
| -------| --------- |
| 16/03 às 19h  | Project kick-off. |
| 24/03 a 14/04 | [Sprint 1](Sprints/SPRINT1.md). |
| 25/04 a 15/05 | [Sprint 2](Sprints/SPRINT2.md). |
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
<a href="https://github.com/stephani-ss" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/30b6ebe8706a4ed9424e32da5fcf4ed6574e3fe3/Imagens/Team/StephaniSoares.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/Borgarelli" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/30b6ebe8706a4ed9424e32da5fcf4ed6574e3fe3/Imagens/Team/KauaBorgarelli.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/camilaffpacheco" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/24d29d4cdb3e00acc447f5a3cc9bb67f1934867c/Imagens/Team/CamilaPacheco.png" height="65" width="65" align="left" target="_blank" ></a>
<a href="https://github.com/pdrMottaS" target="_blank"><img src="https://github.com/DolphinDatabase/DescontOn/blob/24d29d4cdb3e00acc447f5a3cc9bb67f1934867c/Imagens/Team/PedroMotta.png" height="65" width="65" align="left" target="_blank" ></a> <br/>
<br></br>
Para mais informações[^2], clique [aqui](https://github.com/DolphinDatabase/API3/wiki/Development-Team).
<br></br>

## Licença  

Este projeto esta sob licença [MIT](LICENSE).<br/>
<a href="https://github.com/dolphindatabase" target="_blank" align="center">![Badge](https://img.shields.io/badge/MADE&nbsp;by&nbsp;DolphinDatabase-grey?style=for-the-badge&logo=dev.to)</a>

[^1]: Vídeo produzido e editado pelos integrantes do grupo.
[^2]: Equipe responsável pelo desenvolvimento do Projeto Integrador.
