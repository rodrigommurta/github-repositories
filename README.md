<a id="readme-top"></a>

[![LinkedIn][linkedin-shield]][linkedin-url]


<details>
  <summary>Sumário</summary>
  <ol>
    <li><a href="#sobre-o-projeto">Sobre o projeto</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#funcionamento">Funcionamento</a></li>
    <li>
      <a href="#estrutura">Estrutura</a>
      <ul>
        <li>
          <a href="#arquitetura">Arquitetura</a>
          <ul>
            <li><a href="#data-module">Data Module</a></li>
            <li><a href="#domain-module">Domain Module</a></li>
            <li><a href="#presentation-module">Presentation Module</a></li>
            <li><a href="#app-module">App Module</a></li>
          </ul>
        </li>
      </ul>
    </li>
    <li><a href="#contato">Contato</a></li>
  </ol>
</details>

## Sobre o projeto

Este projeto tem o objetivo de criar um aplicativo Android para consultar a API do GitHub e trazer
os repositórios mais
populares de Kotlin.

[![Repositories screen loading][repositories-loading]]

[![Repositories screen success][repositories-success]]

[![PullRequests screen loading][product-loading]]

[![PullRequests screen success][pullrequests-success]]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

Algumas referências de versões utilizadas no desenvolvimento:

* Android Gradle Plugin: 8.7.2
* Gradle: 8.9
* Gradle JDK: Azul Zulu 17.0.13

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Funcionamento

A tela inicial do app exibe uma lista de repositórios do GitHub baseados em Kotlin. A medida que a
lista é rolada e chega ao final, uma nova requisição para buscar mais repositórios é realizada, e
adicionada à lista existente.

Ao clicar em um repositório da lista, o app navega para a lista de Pull Requests do repositório
selecionado. Quando selecionar um pull request, o aplicativo irá redirecionar o usuário para uma
página web referente ao pull request clicado.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Estrutura

Apesar de o app possuir poucas funcionalidades, sua estrutura foi planejada para uma possível
escalabilidade.

### Arquitetura

A arquitetura utilizada é MVVM + Clean Arch, com modularização.

[![Diagram Arch][diagram-arch]]

Cada módulo possui sua própria camada de gradle, testes e injeção de dependência (Dagger).

Os recursos foram organizados em feature packages (telas).

#### Data Module

O módulo Data é o responsável por realizar consultas à API e realizar a persistência dos dados
obtidos.

Estruturação:

* Service: interface responsável por acessar a API;
* LocalDB: package com classes de persistência de dados utilizando Room;
* ProviderImpl (Repository): responsável por instrumentar as respostas da API, BD, e devolver ao
  UseCase (Domain);
* model: package com os modelos de dados remoto.
* Foi criada a função utilitária NetworkAdapter, que é responsável por receber instruções do
  ProviderImpl por meio de delegates, e realizar as ações no BD.

#### Domain Module

Estruturação:

* Classe utilitária State: abstração para representar o estado da tela, podendo ser Loading, Error
  ou Success. É implementado por toda Screen;
* Abstração do UseCase garantindo que todos os UseCases sigam um mínimo padrão de implementação;
* Cada feature possui:
    * Screen: representação de uma tela, contendo seu State e seu modelo de dados;
    * Modelo de dados;
    * Provider: interface que interliga os módulos Domain e Data;
    * UseCase: responsável por receber pedidos da ViewModel; contactar a camada de Data (não
      necessariamente; manipular os dados; manipular os estados da tela.

#### Presentation Module

Estruturação:

* ViewModel: é quem recebe as instruções da view e define o que deve ser feito;
* ComposeView: é a representação da tela para o usuário, utilizando compose;
* ComposeNavigation: representação das rotas de cada tela, utilizada para navegação entre as telas.

#### App Module

Estruturação:

* Single Activity (MainActivity): responsável pela criação da única activity do app;
* Responsável pela inicialização dos módulos de injeção de dependência;
* Responsável por gerenciar a navegação.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contato

Rodrigo Murta - rodrigommurta@gmail.com

LinkedIn: [https://www.linkedin.com/in/rodrigomurta/](https://www.linkedin.com/in/rodrigomurta/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


[diagram-arch]: images/diagram_arch.png

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/rodrigomurta

[repositories-loading]: images/repositories-loading.png

[repositories-success]: images/repositories-success.png

[product-loading]: images/pullrequests-loading.png

[pullrequests-success]: images/pullrequests-success.png