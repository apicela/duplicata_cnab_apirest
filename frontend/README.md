## ⚙️ Como configurar FRONTEND para teste da aplicação

### Método 1 - Utilizando Docker (recomendado)
1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/frontend/`.
3. Execute o seguinte comando no terminal: `docker build -t front . && docker run -it -p 3000:3000 front`
4. Acesse a interface pelo navegador: http://localhost:3000
   <br>
### Método 2 - Utilizando Node 20.8.1
1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/backend/`.
3. Execute o seguinte comando no terminal: ```npm run dev```
4. Acesse a interface pelo navegador: http://localhost:3000
   <br>