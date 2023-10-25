## ⚙️ Como configurar BACKEND para teste da aplicação

### Método 1 - Utilizando Docker (recomendado) - não requer Java e Gradle instalados em sua máquina:

1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/backend/`.
3. Execute o seguinte comando no terminal: ```docker build -t duplicata . && docker run -p 8080:8080 duplicata```
4. Acesse a interface pelo navegador: http://localhost:8080/swagger-ui/index.html
   <br>

### Método 2 - Utilizando Java 17+ e Gradle 6.4+

1. Clone este repositório: ``` git clone https://github.com/apicela/duplicata_HomeDeveloper_teste.git ```
2. Abra o terminal no diretório do arquivo `duplicata_HomeDeveloper_teste/backend/`.
3. Execute o seguinte comando no terminal: ```./gradlew build && ./gradlew bootRun```
4. Acesse a interface pelo navegador: http://localhost:8080/swagger-ui/index.html
   <br>