<h1 align="center">
 🖥️ Sistema de Gerenciamento de Livros
</h1>

## Funcionamento das Telas do Programa

Abaixo você verá como as telas do meu programa funcionam. A versão do java é a 17.0.10, as versões mais atuais podem dar erros ao executar `.jar` no cmd.

O sistema foi estruturado com base no padrão MVC (Model-View-Controller), há tratamento robusto de exceções usando blocos try-catch, o que previne falhas inesperadas e o armazenamento de dados, implementei serialização em disco, o que possibilita que os dados dos usuários e livros sejam salvos de forma persistente.

Para executar no terminal execute o arquivo `Main`. Para executar a interface gráfica execute o arquivo Projeto_Livros-MVC.jar.

![image](https://github.com/user-attachments/assets/5642a4c8-5080-45b5-81dd-ba8f98bdf5f9)

Ao executar o Main pela primeira vez vai aparacer uma pasta chamada `out` (onde está amarelo) que é o código compiliado e arquivo jar, se quiser pode apagar o `out` que está em azul

![image](https://github.com/user-attachments/assets/80b21f72-328e-408f-8b45-c97bd17c4167)

A imagem acima mostra o caminho até o `jar` em ambos os `out`.


Se preferir baixar só o executável por aqui, ele está na pasta out/artifacts/Projeto_Livros_MVC_jar, basta baixar e executar em java. Os dados serão salvos em uma pasta chamada `dados`.
ou clicando [aqui](https://drive.usercontent.google.com/u/1/uc?id=18etojLItY5qqdJ2QB3yQs6HS1Wqxt2h6&export=download)
### Atenção

- Não coloquei as mensagens de erros, por exemplo(mensagem quando os campos de textos estão vazios, etc) para não ficar extenso
- Vale resaltar que os livros são salvos globalmente, ou seja, todos que fizerem login na sua maquina, vai ter acesso aos seus livros. Apenas o histórico é individual para cada usuário
- O histórico é feito pelo nome do usuário, logo, o programa não aceita nomes iguais

### Tela de Login

Ao abrir o arquivo `.jar`, a primeira tela que aparecerá para o usuário é a tela de login:

![Tela de login](https://github.com/user-attachments/assets/68f18c4d-5c5f-4983-923b-33ae6c5f7b25)

Nesta tela, há dois campos de texto para o usuário inserir o nome de usuário e um campo de senha utilizando o `JPasswordField` para deixar a senha codificada. 


### Tela de Cadastro

Há também um botão para cadastro. Ao apertar esse botão, abrirá uma nova tela para o usuário se cadastrar, basicamente com as mesmas funções da tela de login:

![Tela cadastro](https://github.com/user-attachments/assets/5fc530db-2141-4e6f-b09e-0946efdaba1f)

### Tela de Login Bem-sucedido

Ao se cadastrar, o usuário voltará para a tela de login. Ao preencher os campos corretamente, aparecerá uma mensagem indicando que o login foi bem-sucedido, e a tela de menu será aberta:

![Tela login certo](https://github.com/user-attachments/assets/8a8ce27f-9722-4df0-af94-9797178d73e8)

### Tela de Menu

A tela de menu contém 6 botões: "Ver Livros", "Adicionar Livro", "Escolher Livro", "Ver Histórico", "Remover Livro" e "Sair".

![Tela de menu](https://github.com/user-attachments/assets/471e5602-1871-4742-95c3-cc6121c02e56)

### Tela de Ver Livros

A tela de "Ver Livros" mostra os livros disponíveis. Há também um campo de pesquisa para procurar um livro, seja pelo nome, autor ou gênero, sem necessidade de digitar corretamente. Ao lado do botão "Voltar" para o menu, há um botão para limpar o filtro de pesquisa. Ao apertar o botão "Voltar", a tela de menu será exibida.

![Tela de ver livro](https://github.com/user-attachments/assets/9f61b53c-ce3f-4fb2-92b3-ddb343312a3e)

### Tela de Adicionar Livro

Na tela de "Adicionar Livro", há 4 campos de texto para o usuário. Dou atenção especial ao campo de URL, que é responsável pelo link do livro na internet. Também há um botão para adicionar uma imagem. Todos são obrigatórios

![Tela de adicionar livro](https://github.com/user-attachments/assets/ccb0c003-c0e5-4661-b4c9-87b0bf790952)

Ao adicionar um livro corretamente, aparecerá uma mensagem indicando que o livro foi adicionado com sucesso, e a tela de menu será exibida


### Tela de Escolher Livro

A tela de "Escolher Livro" é semelhante à tela de "Ver Livros", com a diferença de que os livros são clicáveis.

![Tela de escolher](https://github.com/user-attachments/assets/07826d34-916c-4b43-bced-22453d17f764)

Ao clicar no livro, haverá uma confirmação, e ao apertar em "Yes", o navegador do usuário abrirá o link do livro que ele colocou, além disso o livro vai ser adicionado ao histórico

![image](https://github.com/user-attachments/assets/8458364b-693e-4a80-816e-96f2d2308d88)

![Tela de navegador](https://github.com/user-attachments/assets/55eaec66-7769-40e0-8c88-6da8df0473b6)

### Tela de Ver Histórico

A tela de "Ver Histórico" é semelhante às telas já vistas, com a diferença de haver um botão no lado direito para limpar o histórico e ser individual para cada usuário:

![Tela de historico](https://github.com/user-attachments/assets/ed0ebb62-7213-485f-ba97-dc58845b382d)


### Tela de Remover Livro

A tela de "Remover Livro" é semelhante à tela de "Escolher Livro", com um botão para remover o livro:

![Tela de remover](https://github.com/user-attachments/assets/46143abd-281a-4601-81ac-ecfc6de49e3f)

![Tela de mensagem remover](https://github.com/user-attachments/assets/d848533d-07d6-414c-95d8-cc649d59ed38)

### Tela de Sair

A última tela é a de sair:

![Tela de sair](https://github.com/user-attachments/assets/cab58c52-f7bd-4d92-a77f-74481f80d04f)

Ao apertar "Yes", o aplicativo fecha e encerra o programa. Caso o usuário aperte "No", a aplicação continua na tela de menu.
