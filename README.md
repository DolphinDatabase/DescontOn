<h1 align="center"> Story Cards  </h1>  

<p align="center">
  <img src="https://github.com/DolphinDatabase/DescontOn/blob/6fa887b9e924f9a573776f546e20421438901d9e/Imagens/STORYGIF.gif"/>
</p>

![Badge](https://img.shields.io/badge/STATUS-DESENVOLVIMENTO-yellow?style=flat-square&logo=)



<!--<h1 align="center"> DescontOn </h1>-->


## Tabela de Conteúdos

 * [Descrição](#descrição)
 * [User Story](#user-story)
 * [Funcionalidades Desenvolvidas](#funcionalidades-desenvolvidas) 
 * [Ilustração das Funcionalidades](#ilustração-das-funcionalidades)   
 * [Benefícios](#benefícios)
 * [Gráfico de Burndown](gráfico-de-burndown)



## Descrição

Nessa sprint apresentaremos como foi feito o cadastro dos produtos no servidor/ banco de dados e também como são adicionados no carrinho de compras, para conferência e ajuste das quantidades e valores. Além da autonomia fornecida ao usuário para editar, remover, arquivar ou desarquivar seus produtos e da visualização prática e intuitiva dos produtos cadastrados através da listagem que possui um filtro para que seja possível diferenciar quais produtos estão disponíveis e quais produtos estão arquivados.

## User Story
  
 #### **Story 1 - Cadastro de Produtos** 
  
| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Visualizar os produtos que foram cadastrados em uma lista. | Seja possível conferir as informações do produto e adicioná-los na sacola. |

 #### **Story 2 - Listagem de Produtos**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Adicionar produtos no meu carrinho de compras. | Seja possível listar os itens escolhidos para conferir e ajustar a quantidade, subtotal e os descontos que refletirão no valor final. |

  #### **Story 3 - Sacola de Compras**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Adicionar produtos no meu carrinho de compras. | Seja possível listar os itens escolhidos para conferir e ajustar a quantidade, subtotal e os descontos que refletirão no valor final. |

 #### **Story 4 - Alterar a Quantidade de Produtos na Sacola**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Alterar a quantidade de produtos na sacola. | Seja possível comprar mais itens sem precisar retornar a lista de produtos. |

 #### **Story 5 - Finalizar uma Compra**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Finalizar uma compra. | Seja possível efetuar seu fechamento. |

#### **Story 6 - Remover um Item da Sacola**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Usuário de um E-commerce. | Remover um item da sacola. | Exista a possibilidade de exclusão, caso não deseje mais comprar determinado produto. |

#### **Story 7 - Remover um Produto Cadastrado**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Remover um item cadastrado. | Exista a possibilidade de exclusão, caso as informações do produto estejam erradas ou não  seja mais vendido. |

#### **Story 8 - Editar um Produto Cadastrado**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. | Editar um produto cadastrado. | Seja possível alterar as informações do produto, como nome e valor. |

#### **Story 9 - Filtrar Produtos Disponíveis e Arquivados**

| Como | Eu quero | Para que |
| ------- | ------- | ------- |
| Assistente de cadastro de um E-commerce. |  filtrar os produtos disponíveis ou arquivados.|  seja possível visualizar quais produtos estão disponíveis e quais produtos estão arquivados. |

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

 Para mais detalhes das funcionalidades entregues, acesse a [release](https://github.com/DolphinDatabase/DescontOn/releases/tag/v1.0.0).
 
 ## Informações Importantes
**Cadastro de Produtos**
- Necessário selecionar a categoria do produto e informar o nome e  valor de venda
- O cadastro de um produto só é efetuado quando todos os campos são preenchidos, o sistema informa quais campos faltam ser preenchidos e também quando um cadastro é efetuado com sucesso
- O código (ID) do produto é gerado automaticamente

**Remover Produtos**
- Não é possível remover produtos que já foram comprados, nesse caso ao tentar remove-lo, será arquivado e ficará disponível na listagem de produtos arquivados

**Desarquivar Produtos**
- Para desarquivar um produto é necessário acessar os produtos arquivados através do filtro de listagem

**Finalizar Compra**
- É necessário adicionar itens na sacola para finalizar uma compra, caso contrário o sistema exibirá um alerta ("Não foi possível finalizar a compra")
- Ao finalizar uma compra, a ferramenta verifica se o produto está disponível e se as informações essenciais como Nome e Valor não sofreram alterações (funcionalidade editar produto). Exibindo 4 possíveis alertas ("Produto não existe", "Produto não está disponível", "O Valor do Produto foi alterado" ou "O nome do Produto alterado"), com o ID de cada item que se encaixa nos casos mencionados anteriormente
- Em casos de erro ao finalizar a compra, onde é exibido um alerta ("Não foi possível finalizar a compra"), o sistema esvazia a sacola de compras


## Ilustração das funcionalidades

## Gráfico de Burndown
