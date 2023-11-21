## Padrão formato CNAB

![formato cnab](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/9e0c2aee-9221-4b93-9e88-1761b42b0c59)

#### Exemplo de arquivo válido
```
001Empresa A_____________________2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002C3020____________123456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D500030__________55576905________992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM
003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
##### Saída

![duplicata valida 01](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/0dcec49c-17c0-42c1-a0d9-54be89302b9a)


#### Exemplo de arquivo inválido 1 
```
001Empresa A_____________________2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002C302S____________12S456__________65S321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D500030__________55576905________992224498_______MMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
##### Saída

![duplicata 04 invalida](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/7155bd0b-d813-4a3c-8e34-0544bc0f979c)


#### Exemplo de arquivo inválido 2 
```
001Empresa A__________________D___2345678901234_MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D3020____________12S456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002P3020____________12S456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
022D3020____________123456__________654W321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
002D3020____________123456__________654321__________MMMMMMMMMMMMMMMMMMMMMMMMMMMM
003MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
```
##### Saída

![duplicata invalida 01](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/ddef6311-6ee7-42b9-b501-779d64bff1cb)


## Documentação Swagger
![image](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/da4c59e1-32ff-457e-88a0-dbe0cb95003b)
![image](https://github.com/apicela/duplicata_HomeDeveloper_teste/assets/105384228/9fce14b2-10db-442e-a05e-ae2e95ccbce0)




## ⚙️ Como configurar aplicação para rodar frontend + backend juntos
1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/
3. Execute o seguinte comando no terminal: `docker-compose up --build`
4. Acesse o link: https://localhost:3000/ 
