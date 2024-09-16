<h1 align="center">🖥️ Sistema de Gerenciamento de Livros</h1>
<p> Projeto desenvolvido na disciplina de Programação Orientada a Objetos (POO). </p>

## 🎯 Funcionamento das Telas do Programa

Este sistema de gerenciamento de livros foi desenvolvido com Java 17.0.10 e segue o padrão MVC (Model-View-Controller). Ele oferece persistência de dados através de serialização e inclui tratamento robusto de exceções para prevenir falhas.

Para rodar o projeto, basta executar o arquivo `Main` no terminal ou o arquivo `Projeto_Livros-MVC.jar` para a interface gráfica.

<p align="center">
  <img src="https://github.com/user-attachments/assets/5642a4c8-5080-45b5-81dd-ba8f98bdf5f9" alt="Arquivos compilados" />
</p>

Ao rodar o `Main`, uma pasta chamada `out` será gerada. O arquivo `.jar` pode ser executado diretamente, e os dados serão salvos na pasta `dados`.

Se preferir baixar só o executável, ele está na pasta out/artifacts/Projeto_Livros_MVC_jar, basta baixar e executar em java. Os dados serão salvos em uma pasta chamada `dados`. Ou clicando [aqui](https://drive.usercontent.google.com/u/1/uc?id=18etojLItY5qqdJ2QB3yQs6HS1Wqxt2h6&export=download)

### 🚨 Atenção

- Os livros são salvos globalmente, ou seja, qualquer usuário que fizer login na mesma máquina terá acesso aos livros cadastrados. Apenas o histórico é individual por usuário.
- O histórico é baseado no nome do usuário. O sistema não aceita nomes duplicados.
- Não coloquei as mensagens de erros, por exemplo(mensagem quando os campos de textos estão vazios, etc) para a documentação não ficar extensa.

## 🖼️ Telas do Programa

### 🔑 Tela de Login

<p align="center">
  <img src="https://github.com/user-attachments/assets/68f18c4d-5c5f-4983-923b-33ae6c5f7b25" alt="Tela de login" />
</p>

A primeira tela exibida ao iniciar o programa é a tela de login. Ela contém campos de texto para o nome de usuário e senha, que é ocultada com `JPasswordField`.

### 📝 Tela de Cadastro

<p align="center">
  <img src="https://github.com/user-attachments/assets/5fc530db-2141-4e6f-b09e-0946efdaba1f" alt="Tela de cadastro" />
</p>

Ao clicar no botão "Cadastrar", o usuário é redirecionado para a tela de cadastro, que possui funcionalidades semelhantes à de login.

### ✅ Tela de Login Bem-Sucedido

<p align="center">
  <img src="https://github.com/user-attachments/assets/8a8ce27f-9722-4df0-af94-9797178d73e8" alt="Tela de login bem-sucedido"/>
</p>

Quando o login é realizado com sucesso, uma mensagem de confirmação é exibida, e o usuário é levado para o menu principal.

### 📋 Tela de Menu

<p align="center">
  <img src="https://github.com/user-attachments/assets/471e5602-1871-4742-95c3-cc6121c02e56" alt="Tela de menu"/>
</p>

O menu principal contém seis opções: "Ver Livros", "Adicionar Livro", "Escolher Livro", "Ver Histórico", "Remover Livro" e "Sair".

### 📚 Tela de Ver Livros

<p align="center">
  <img src="https://github.com/user-attachments/assets/9f61b53c-ce3f-4fb2-92b3-ddb343312a3e" alt="Tela de Ver Livros"/>
</p>

Na tela "Ver Livros", é possível ver todos os livros cadastrados. Há um campo de busca para facilitar a localização de livros por nome, autor ou gênero.

### ➕ Tela de Adicionar Livro

<p align="center">
  <img src="https://github.com/user-attachments/assets/ccb0c003-c0e5-4661-b4c9-87b0bf790952" alt="Tela de Adicionar Livro" />
</p>

Na tela "Adicionar Livro", o usuário preenche quatro campos obrigatórios para adicionar um livro. É possível também adicionar uma imagem de capa e uma URL.

### 📖 Tela de Escolher Livro

<p align="center">
  <img src="https://github.com/user-attachments/assets/07826d34-916c-4b43-bced-22453d17f764" alt="Tela de Escolher Livro"/>
</p>

A tela "Escolher Livro" permite que os livros sejam selecionados. Ao clicar no livro, uma confirmação é exibida e, ao aceitar, o navegador abre a URL do livro.

<p align="center">
  <img src="https://github.com/user-attachments/assets/8458364b-693e-4a80-816e-96f2d2308d88" alt="Confirmação de escolha" />
  <img src="https://github.com/user-attachments/assets/55eaec66-7769-40e0-8c88-6da8df0473b6" alt="Tela do navegador" />
</p>

### 📜 Tela de Ver Histórico

<p align="center">
  <img src="https://github.com/user-attachments/assets/ed0ebb62-7213-485f-ba97-dc58845b382d" alt="Tela de Ver Histórico"/>
</p>

A tela "Ver Histórico" exibe o histórico de livros já acessados pelo usuário, com a opção de limpar o histórico completo ou um livro específico.

### ❌ Tela de Remover Livro

<p align="center">
  <img src="https://github.com/user-attachments/assets/46143abd-281a-4601-81ac-ecfc6de49e3f" alt="Tela de Remover Livro"/>
</p>

Semelhante à tela "Escolher Livro", mas com a funcionalidade de remover o livro selecionado. O sistema confirma antes de executar a ação.

<p align="center">
  <img src="https://github.com/user-attachments/assets/d848533d-07d6-414c-95d8-cc649d59ed38" alt="Confirmação de Remoção"/>
</p>

### 🚪 Tela de Sair

<p align="center">
  <img src="https://github.com/user-attachments/assets/cab58c52-f7bd-4d92-a77f-74481f80d04f" alt="Tela de Sair"/>
</p>

Na tela de "Sair", o sistema solicita confirmação. Se o usuário escolher "Yes", o programa será encerrado.
