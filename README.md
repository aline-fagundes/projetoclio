 <samp>
  <h1 align="center"> Passaporte Clio </h1>
<p align="center">
Site que tem como proposta conectar pessoas a museus. Nele será possível conhecer mais sobre os museus do governo de São Paulo através de fotos, descrições, localização e informações sobre o funcionamento. Como atrativo, o site também terá gamificação da experiência dos usuários por meio de um sistema de pontuação. A partir de determinadas interações – como logar, relatar se esteve em uma instituição e avaliar como foi sua visita – será possível acumular pontos que, então, serão convertidos em títulos. 
<br>
  
<h2 align="center"> Conteúdo </h2>

  • <a href="#Features">Features</a> <br>
  • <a href="https://miro.com/app/board/uXjVO2d8Fmg=/">Diagrama de funcionalidades</a> <br>
  • <a href="https://miro.com/app/board/uXjVO0p2LbU=/">Diagrama de Classes e tabela de regras</a> <br>
  • <a href="#Banco de dados">Banco de dados</a> <br>
  • <a href="#Status">Status</a> <br>
  • <a href="#Tecnologias">Tecnologias</a> <br>
  • <a href="#Team">Team</a> <br>
</p>

<h2 align="center"> <a name="Features"> Features </a> </h2>

  <h3>INFORMAÇÕES MUSEUS</h3>
    
O site apresentará as seguintes informações de cada instituição:

- [x] Endereço (link com maps interativo da localização);

- [x] Breve descrição da instituição;

- [x] Ícones que redirecionem para Instagram e site oficial do museu;

- [x] Dados sobre funcionamento (dias e horários);

- [x] Foto oficial do museu;

<br>
 <h3>FUNCIONALIDADES USUÁRIO</h3>

Será possível fazer as seguintes interações:

- [x] Realizar cadastro, logar e deslogar no site;

- [x] Alterar e recuperar senha;

- [x] Consultar informações dos museus;

- [x] Consultar ranking de pontuação;

- [x] Postar registros descrevendo como foi a visita;

- [x] Avaliar o museu;

- [x] Denunciar comentários indevidos para que o conteúdo seja revisado por um administrador;

- [x] Registrar presença (check-in sem validação);

A partir de determinadas interações (realizar cadastro, postar avaliações, registrar presença e dar notas aos museus), será possível acumular pontos e, então, receber títulos.

 <br>
 <h3>FUNCIONALIDADES ADMINISTRADOR</h3>

Será possível fazer as seguintes interações:

- [x] Realizar cadastro, logar e deslogar no site;

- [x] Alterar e recuperar senha;

- [x] Moderar comentários (excluir registros indevidos);

- [x] Acessar informações sobre os usuários;

- [x] Cadastrar novos museus;

- [x] Atualizar informações sobre as instituições;

- [x] Gerenciar pontuação e títulos.

<br>

<h2 align="center"> <a name="Banco de dados"> Banco de dados </h2>

<h3>ENTIDADES</h3>
  
<h3>PESSOAS:</h3>

- Id Pessoa (PK) - INT AUTOINCREMENT;

- Nome - VARCHAR (45);

- Sobrenome - VARCHAR (45);

<br>
	
<h3>USER:</h3>

- Id User (PK) - INT AUTOINCREMENT;

- Id Pessoa (FK) - INT AUTOINCREMENT;

- Pontuação - INT;	

<br>	
	
<h3>ADMNISTRADOR:</h3>

- Id Adm (PK) - INT AUTOINCREMENT;

- Id Pessoa (FK) - INT AUTOINCREMENT;
	
<br>
	
<h3>LOGIN:</h3>

- Id Login (PK) - INT AUTOINCREMENT;

- Id Pessoa (FK) - INT AUTOINCREMENT;

- Email - VARCHAR (100);

- Username - VARCHAR (50);

- Senha - VARCHAR (300);

<br>
<h3>PERMISSÃO:</h3>

- Id Permissão (PK) - _INT AUTOINCREMENT_;

- Descrição - _VARCHAR (100)_;

<br>
	
<h3>PERMISSÃO-LOGIN:</h3>

- Id Permissão (FK) - _INT AUTOINCREMENT_;

- Id Login (FK) - _INT AUTOINCREMENT_;

<br>
	
<h3>MUSEUS:</h3>

- Id Museu (PK) - INT AUTOINCREMENT;

- Id Endereço (FK) - INT AUTOINCREMENT;

- Nome - VARCHAR (100);

- Descrição - VARCHAR (1000);

- Foto - BLOB (2048);

- Funcionamento - VARCHAR (500);
	
<br>
	
<h3>ENDEREÇO MUSEU:</h3>

- Id Endereço (PK) - INT AUTOINCREMENT;

- CEP - INT;

- Rua - VARCHAR (45);

- Número - INT;

- Bairro - VARCHAR (45);

- Cidade - VARCHAR (45);

- Estado - VARCHAR (2);

- País - VARCHAR (45);

<br>
	
<h3>PRESENÇA MUSEU:</h3>
	
- Id Presença Museu (PK) - INT AUTOINCREMENT;
	
- Id Museu (FK) - INT AUTOINCREMENT;
	
- Id User (FK) - INT AUTOINCREMENT;
	
- Data - DATETIME;
	
<br>
	
<h3>AVALIAÇÃO MUSEU:</h3>

- Id Avaliação (PK) - INT AUTOINCREMENT;

- Id User (FK) - INT AUTOINCREMENT;

- Id Museu (FK) - INT AUTOINCREMENT;

- Nota - INT;

- Avaliação - VARCHAR (300);
  
  <br>
  
  <h2 align="center"> <a name="Status">  Status </a> </h2>

  <h4 align="center"> 
	🚧 LOADING... 🚧
</h4>
  
  <br>
  
  <h2 align="center"> <a name="Tecnologias">  Tecnologias </a> </h2>

Ferramentas que serão usadas na construção do projeto:

- [Spring](https://spring.io/)
- [Angular](https://angular.io/)
  
  <br>

  <h2 align="center"> <a name="Team">  Team </a> </h2>
  
  <table align="center">
  <tr>
    <td align="center"><a href="https://github.com/aline-fagundes"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/102121711?s=400&u=65912f1e97edf6fc26f36aec52fff6089807cb36&v=4" width="100px;" alt=""/><br /><sub><b>Aline Napoli Fagundes</b></sub></a><br />
    <td align="center"><a href="https://github.com/brendabba"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/48896682?v=4" width="100px;" alt=""/><br /><sub><b>Brenda Alcântara</b></sub></a><br />
    <td align="center"><a href="https://github.com/deboraamattos"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/86445351?v=4" width="100px;" alt=""/><br /><sub><b>Débora Mattos</b></sub></a><br />
    <td align="center"><a href="https://github.com/bela-mad"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/97325464?v=4" width="100px;" alt=""/><br /><sub><b>Isabela Madureira</b></sub></a><br />
    <td align="center"><a href="https://github.com/julia-sousaa"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/93545198?v=4" width="100px;" alt=""/><br /><sub><b>Júlia Sousa</b></sub></a><br />
    <td align="center"><a href="https://github.com/PamelaBSNunes"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/102122167?v=4" width="100px;" alt=""/><br /><sub><b>Pâmela Nunes</b></sub></a><br /></td>
  </tr>
</table>

  </samp>

