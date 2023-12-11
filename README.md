Análise de Teste API R2D2
Este projeto é parte do desafio para a vaga de QA na Trade Tech

JDK 1.8: Certifique-se de ter o JDK 1.8 instalado em seu sistema.
Maven: Todas as dependências necessárias estão listadas no arquivo pom.xml. Caso necessário, execute mvn clean install para garantir a instalação das dependências.
Como Executar
Clone o Repositório: git clone https://gitlab.com/MatFReis/R2D2_Api_Test

Navegue até o Diretório do Projeto: R2D2
Execute os Testes: mvn clean test site (Para gerar o Relatório)
IntelliJ Version : 2021.1.1

Ou

Abra o IntelliJ: Após importar os arquivos;
Clique em "Run Test" na classe Api_Test para executar todos os testes.

PLANO E ESTRATÉGIA DE TESTES
A Estratégia  de Testes inicial foi pensado para cobrir os seguintes itens :

Verificar a Integridade da API;
Verificar o retorno dos códigos;
Verificar o corpo do response;
Verificar a integraçao entre ações; (POST - GET)
Verificar se as ações batem diretamente com a documentação da API e vice e versa;
Identificar erros na Documentação da API;
Propor melhorias nas funcionalidades e retornos obtidos pelo usuário.

