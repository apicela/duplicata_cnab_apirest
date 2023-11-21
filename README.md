# API Rest de duplicatas padrão CNAB

<p align="center">
  <img src="https://img.shields.io/badge/Java-000?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-000?style=for-the-badge&logo=spring&logoColor=green"/>
  <img src="https://img.shields.io/badge/Vue.js-000?style=for-the-badge&logo=vue.js&logoColor=4FC08D"/>
  <br> <img src="http://img.shields.io/static/v1?label=STATUS&message=concluido&color=RED&style=for-the-badge"/>
</p>

## O Desafio

O desafio consiste na criação de uma aplicação Full Stack utilizando  Java no backend e Vue.js no frontend, o foco da aplicação é a criação de uma API responsável por receber e válidar arquivos(.txt, .pdf) que sigam o layout do padrão CNAB, com o sistema sendo capaz de identificar diferentes tipos de registros.
Onde, caso aja erros, o sistema deverá rejeitar a transação e enviar mensagens de erros apropriadas para o usuário buscar solucionar o problema. Caso o arquivo esteja dentro dos padrões, deverá registrar as informações da transação no banco de dados.

## Resumo da aplicação

Desenvolvimento de uma aplicação Full Stack de API Rest, capaz de receber e validar arquivos financeiros que seguem o padrão CNAB.

## Aplicação na prática

https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/00f72bc7-6d92-4952-904a-8f1626566861

## ⚙️ Como configurar aplicação para rodar frontend + backend juntos
 - Requisitos obrigatórios: Docker
1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/
3. Execute o seguinte comando no terminal: `docker-compose up --build`
4. Acesse o link: https://localhost:3000/ 


## Padrão formato CNAB

![formato cnab](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/9e0c2aee-9221-4b93-9e88-1761b42b0c59)

#### Exemplo de arquivo válido
```
001Empresa A_____________________2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002C3020____________123456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D500030__________55576905________992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM
003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
#### Exemplo de arquivo inválido 1 
```
001Empresa A_____________________2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002C302S____________12S456__________65S321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D500030__________55576905________992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
#### Exemplo de arquivo inválido 2 
```
001Empresa A__________________D___2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D3020____________12S456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002P3020____________12S456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
022D3020____________123456__________654W321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D3020____________123456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
