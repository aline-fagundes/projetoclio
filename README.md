<div align="center">
  <img align="center" src="https://github.com/bela-mad/projetoclio/blob/master/src/assets/logo_clio.png?raw=true" width="200px"/>
</div>

<samp>
  <h1 align="center"> Passaporte Clio </h1>
<p align="center">
Site que tem como proposta conectar pessoas a museus. Nele será possível conhecer mais sobre os museus do estado de São Paulo através de fotos, descrições, localização, informações sobre o funcionamento e avaliações de visitantes que estiveram nas instituições. Como atrativo, a aplicação também terá gamificação da experiência dos usuários por meio de um sistema de pontuação. A partir de determinadas interações – como logar, relatar se esteve em uma instituição e avaliar como foi sua visita – será possível acumular pontos que, então, serão convertidos em títulos. 

<br>
  
<h2 align="center"> Conteúdo </h2>
  
  • <a href="#Repositories">Repositories</a> <br>
  
  • <a href="#Features">Features</a> <br>
	
  • <a href="#UML">UML</a> <br>
	
  • <a href="#Documentation">Documentation</a> <br>

  • <a href="#Deploy">Deploy</a> <br>

  • <a href="#Flowchart">Flowchart</a> <br>

  • <a href="#Prototype">Prototype</a> <br>
	
  • <a href="#Status">Status</a> <br>
	
  • <a href="#Technologies">Technologies</a> <br>

  • <a href="#Links">Links</a> <br>
	
  • <a href="#Team">Team</a> <br>

</p>

<h2 align="center"> <a name="Repositories"> Repositories </a> </h2>

Acessos: 

<a href="https://github.com/aline-fagundes/projetoclio">Back-end</a> <br>

<a href="https://github.com/bela-mad/projetoclio">Front-end</a> <br>

<br>

<h2 align="center"> <a name="Features"> Features </a> </h2>

 <div align="center">
  <img src="https://user-images.githubusercontent.com/102121711/183476595-a8baf2e7-3788-4317-a4f7-56a7b68824bc.png" width="700px"/>
</div> 
	
<br><br>

  <h3>INFORMAÇÕES MUSEUS</h3>
    
O site apresentará as seguintes informações de cada instituição:

- [x] Endereço;

- [x] Breve descrição da instituição;

- [x] Ícones que redirecionem para Instagram e site oficial do museu;

- [x] Dados sobre funcionamento (dias e horários);

- [x] Foto oficial do museu;

- [x] Avaliações feitas por visitantes;

<br>

 <h3>FUNCIONALIDADES USUÁRIO</h3>

Será possível fazer as seguintes interações:

- [x] Realizar cadastro, logar e deslogar no site;

- [x] Alterar senha;

- [x] Ler sobre o projeto;

- [x] Consultar informações dos museus;

- [x] Buscar uma instituição através do nome;

- [x] Consultar ranking de pontuação;

- [x] Consultar seu perfil;

- [x] Registrar presença em um museu;

- [x] Postar avaliação descrevendo como foi a visita;

- [x] Dar nota ao museu visitado;

- [x] Consultar suas avaliações, filtrando ou não por um museu;

- [x] Editar e excluir suas avaliações;

- [x] Consultar lista de museus que visitou;

- [x] Denunciar avaliações indevidas para que o conteúdo seja revisado por um administrador;

A partir de determinadas interações (realizar cadastro, postar avaliações, registrar presença e dar notas aos museus), será possível acumular pontos e, então, receber títulos.

 <br>
 
 <h3>FUNCIONALIDADES ADMINISTRADOR</h3>

Será possível fazer as seguintes interações:

- [x] Logar e deslogar no site;

- [x] Alterar senha;

- [x] Moderar avaliações (excluir registros indevidos);

- [x] Acessar informações sobre os visitantes cadastrados no site;

- [x] Cadastrar novos museus;

- [x] Atualizar informações sobre as instituições;

- [x] Gerenciar pontuação e títulos.

<br>

<h2 align="center"> <a name="UML">  UML </h2>

Diagrama de classes que compõem a aplicação:
<div align="center">
  <img src="https://user-images.githubusercontent.com/102121711/183477754-d00adca9-16d5-46d6-8f1b-f5e0ad77b711.png" width="700px"><br>
</div> 

<br>
		
<h2 align="center"> <a name="Documentation">  Documentation </h2>

- Acesso:

Para consultar as funcionalidades da API implementadas, acesse a documentação Swagger através do link: 
<br>
https://api-passaporteclio.herokuapp.com/swagger-ui/index.html
<br>
	
- Autenticação:

Para fazer requisições com `Try it out` que requerem permissão, é preciso obter um token de autenticação através do endpoint `/auth`.

![image](https://user-images.githubusercontent.com/102121711/184142546-2343e686-fd3b-4276-a6b8-5a9a34b7d5ca.png)

Credencial com permissão de administrador para testes:
```json
{
  "email": "admin@email.com",
  "senha": "123"
}
```
<br>	
	
- Endpoints:
	
<details>
<summary>Autenticação</summary><br>
 
  |![image](https://user-images.githubusercontent.com/102121711/184140603-8bc2b0ee-85d0-4b09-b9e5-6c57dedbb693.png)|
  |:--:|
  | <b>Autenticação Endpoint</b>|
</details>
	
<details>
<summary>Visitante</summary><br>
 
  |![image](https://user-images.githubusercontent.com/102121711/184139061-20e8f03d-a9df-46b8-861e-192c106ec175.png)|
  |:--:|
  | <b>Visitante Endpoint</b>|
</details>

<details>
<summary>Museus</summary><br>
 
  |![image](https://user-images.githubusercontent.com/102121711/184140814-a17e852d-05dd-4d14-98b7-cdc61d1d2ba1.png)|
  |:--:|
  | <b>Museus Endpoint</b>|
</details>
	
<details>
<summary>Avaliação</summary><br>
 
  |![image](https://user-images.githubusercontent.com/102121711/184140957-e5533fb8-345d-486d-98f0-d0d4e69fd7e7.png)|
  |:--:|
  | <b>Avaliação Endpoint</b>|
</details>

<details>
<summary>Presença</summary><br>
 
  |![image](https://user-images.githubusercontent.com/102121711/184141055-4a585e95-b8c8-43fa-9a07-eb5cb732ceeb.png)|
  |:--:|
  | <b>Presença Endpoint</b>|
</details>

<br>

<h2 align="center"> <a name="Deploy">  Deploy </h2>

A API e o banco de dados estão hospedados nos seguintes links: 

- https://db-passaporteclio.herokuapp.com

- https://api-passaporteclio.herokuapp.com

<br>

<h2 align="center"> <a name="Flowchart">  Flowchart</h2>

Fluxogramas de rotas:
<div align="center">
  <img src="https://user-images.githubusercontent.com/102121711/183488191-0c0f6db9-d6d3-4c5c-a12a-34ccb31e9c6c.jpg" width="850px"><br>
</div> 

<br><br>

<div align="center">
  <img src="https://user-images.githubusercontent.com/102121711/183488126-db175095-9b77-4d6c-99c0-9b093696fefa.jpg" width="850px"><br>
</div> 

<br>

 <h2 align="center"> <a name="Prototype"> Prototype </a> </h2>

- Acesso:

Para consultar as telas prototipadas no Figma, acesse o link: 
<br>
<a href="https://www.figma.com/file/oJyw8wJ8eycPPS7B9FbzYl/Passaporte-Clio?node-id=340%3A620">Figma - Passaporte Clio</a>
<br>

- Paleta de cores: 

<br>

<div align="center">
  <img src="https://user-images.githubusercontent.com/102121711/185117201-34d3382d-2e19-4857-a0ef-11ce20b14ffc.jpg" width="400px"><br>
</div> 

<br>

- Telas:

<details>
<summary>Sobre</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185141425-6b86e71f-7a82-4144-8d90-de35f74b0306.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Iniciar</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185140842-e00ef8c5-b6b2-4575-82a0-2719886ff5d4.png" width="300px"><br>
</div>
</details>

<details>
<summary>Logar</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185129738-15563a56-e624-4801-8eaa-10373184e7e4.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Cadastrar</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185137391-da6d85a2-be47-44d5-adb2-77c031fe3849.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Alterar Senha</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185138175-a6b2e18a-07c8-47ba-a082-b1fc028e290d.png" width="300px"><br>
</div>
</details>	

<details>
<summary>Consultar Ranking</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185140910-7ae99f55-c56e-47df-86d1-37b38f6122c8.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Consultar Museus</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185140997-934d2743-5a05-4aef-ab7e-24f368649f3d.png" width="300px"><br>
</div>
</details>

<details>
<summary>Consultar Museu</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185141864-1c31201f-4e59-492c-8544-4be54995024e.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Cadastrar Museu</summary><br>
 <div>
  <img src="https://user-images.githubusercontent.com/102121711/185139347-47cd96c2-e62f-4706-9673-92a7e37b3c4d.png" width="300px"><br>
</div>
</details>

<details>
<summary>Alterar Museu</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185140345-bd5c22ba-c485-411d-a6e0-0a6fe98aa718.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Perfil Visitante</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185146597-1b05ffa2-adaf-4dff-a9f3-5077ab05cbfd.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Perfil Administrador</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185139789-889cada1-18b7-4e2b-aa0c-6f4bfee94158.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Moderar Avaliações</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185140662-fe0061a9-370e-45d2-93b9-8a774b40a67c.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Termos e Condições</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185141553-f8c9e28f-b4d5-4a61-8931-6f41bf3b091f.png" width="300px"><br>
</div> 
</details>

<details>
<summary>Termos de Privacidade</summary><br>
<div>
  <img src="https://user-images.githubusercontent.com/102121711/185145619-efde541f-be6d-4ee1-8b94-1789252e4ae3.png" width="300px"><br>
</div> 
</details>

<br>

  <h2 align="center"> <a name="Technologies">  Technologies </a> </h2>

Ferramentas usadas na construção do projeto:

- Gerenciamento de sprints:
<div>
  <img alt="Clio-Slack" src="https://img.shields.io/badge/Slack-4A154B?style=flat&logo=slack&logoColor=white">
  <img alt="Clio-Trello" src="https://img.shields.io/badge/Trello-0052CC?style=flat&logo=trello&logoColor=white">
</div> 
<br>

- Diagramação e prototipação:
<div>
  <img alt="Clio-Miro" src="https://img.shields.io/badge/Miro-050038?style=flat&logo=Miro&logoColor=white">
  <img alt="Clio-Figma" src="https://img.shields.io/badge/Figma-F24E1E??style=flat&logo=figma&logoColor=white">
</div>  
<br>
	
- Back-end:
<div>
  <img alt="Clio-Java" src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white">
  <img alt="Clio-MySQL" src="https://img.shields.io/badge/MySQL-00000F?style=flat&logo=mysql&logoColor=white">
  <img alt="Clio-Postman" src="https://img.shields.io/badge/Postman-FA7343?style=flat&logo=postman&logoColor=white">
  <img alt="Clio-JUnit" src="https://img.shields.io/badge/JUnit-CC0000?style=flat&logo=JUnit5&logoColor=white">
  <br>
  <img alt="Clio-Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=spring&logoColor=white">
  <img alt="Clio-SpringBoot" src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=Spring-Boot&logoColor=white">
  <img alt="Clio-SpringData" src="https://img.shields.io/badge/SpringData-6DB33F?style=flat&logo=spring&logoColor=white">
  <img alt="Clio-SpringSecurity" src="https://img.shields.io/badge/Spring_Security-6DB33F?style=flat&logo=Spring-Security&logoColor=white">
</div>	
<br>

- Versionamento:
<div>
  <img alt="Clio-Git" src="https://img.shields.io/badge/Git-E44C30?style=flat&logo=git&logoColor=white">
  <img alt="Clio-GitHub" src="https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white"><br>
</div>
<br>

	
- Documentação:
<div>
  <img alt="Clio-Swagger" src="https://img.shields.io/badge/Swagger-59666C?style=flat&logo=Swagger&logoColor=white"><br>
</div>
<br>

- Deploy:
<div>
  <img alt="Clio-Docker" src="https://img.shields.io/badge/Docker-0078D4?style=flat&logo=Docker&logoColor=white">
  <img alt="Clio-Heroku" src="https://img.shields.io/badge/Heroku-430098?style=flat&logo=heroku&logoColor=white">
</div>
<br>

- Front-end:
<div>
  <img alt="Clio-HTML" src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white">
  <img alt="Clio-CSS" src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white">
  <img alt="Clio-Bootstrap" src="https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white">
  <img alt="Clio-TypeScript" src="https://img.shields.io/badge/TypeScript-007ACC?style=flat&logo=typescript&logoColor=white">
  <img alt="Clio-Angular" src="https://img.shields.io/badge/Angular-DD0031?style=flat&logo=angular&logoColor=white">
</div> 
<br>
	
- IDE's:
<div>
  <img alt="Clio-Eclipse" src="https://img.shields.io/badge/Eclipse-2C2255?style=flat&logo=eclipse&logoColor=white">
  <img alt="Clio-IntelliJ" src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=flat&logo=intellij-idea&logoColor=white">
  <img alt="Clio-VSCode" src="https://img.shields.io/badge/-VS%20Code-260B02?style=flat&logo=visual-studio-code&logoColor=white">
</div> 

<br>

<h2 align="center"> <a name="Status">  Status </a> </h2>

  <h4 align="center"> 🚧 LOADING... 🚧 </h4>

<h4>RELEASES FUTUROS</h4>

- [x] Implementação de pontuação e ranking;

- [x] Construção de “Esqueceu sua senha”;

- [x] Correção de bugs;

- [x] Elaboração de testes automatizados no front-end;

- [x] Publicação do front-end.

<br>
	
<h2 align="center"> <a name="Links">  Links </a> </h2>

Diagramas elaborados no Miro:

• <a href="https://miro.com/app/board/uXjVO0p2LbU=/">Diagrama de classes e regras</a> <br> 

• <a href="https://miro.com/app/board/uXjVO2d8Fmg=/">Diagrama de funcionalidades</a> <br>

• <a href="https://miro.com/app/board/uXjVOsfh1Sw=/">Diagrama de rotas do site</a> <br>

<br>
	
<h2 align="center"> <a name="Team">  Team </a> </h2>
  
<table align="center">
  <tr>
    <td align="center"><a href="https://github.com/aline-fagundes"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/102121711?s=400&u=65912f1e97edf6fc26f36aec52fff6089807cb36&v=4" width="100px;" alt=""/><br /><sub><b>Aline Napoli Fagundes</b></sub></a><br />
    <td align="center"><a href="https://github.com/brendabba"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/48896682?v=4" width="100px;" alt=""/><br /><sub><b>Brenda Alcântara</b></sub></a><br />
    <td align="center"><a href="https://github.com/deboraamattos"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/86445351?v=4" width="100px;" alt=""/><br /><sub><b>Débora Mattos</b></sub></a><br />
    <td align="center"><a href="https://github.com/bela-mad"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/97325464?v=4" width="100px;" alt=""/><br /><sub><b>Isabela Madureira</b></sub></a><br />
    <td align="center"><a href="https://github.com/PamelaBSNunes"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/102122167?v=4" width="100px;" alt=""/><br /><sub><b>Pâmela Nunes</b></sub></a><br /></td>
  </tr>
</table>

</samp>
