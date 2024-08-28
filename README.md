## Funcionamento das Telas do Programa

Abaixo você verá como as telas do meu programa funcionam. Infelizmente, arquivos `.jar` só funcionam em PCs. A versão do Java que eu utilizei é a 21, então se tiver uma versão mais antiga, o arquivo talvez não abra.

### Tela de Login

Ao abrir o arquivo `.jar`, a primeira tela que aparecerá para o usuário é a tela de login:

![Tela de Login](https://github.com/user-attachments/assets/e3550e21-bb0b-4227-a7c4-74d4d99b4e5b)

Nesta tela, há dois campos de texto para o usuário inserir o nome de usuário e um campo de senha utilizando o `JPasswordField` para deixar a senha codificada. 

Caso o usuário não preencha os campos corretamente e aperte no botão "Entrar", aparecerá uma mensagem de erro na tela:

![Tela de erro no login](https://github.com/user-attachments/assets/e1183fae-bf36-4c95-b7c9-c2f085150454)

### Tela de Cadastro

Há também um botão para cadastro. Ao apertar esse botão, abrirá uma nova tela para o usuário se cadastrar, basicamente com as mesmas funções da tela de login:

![Tela cadastro](https://github.com/user-attachments/assets/549d72b5-86f4-4620-bd15-9f30a877c968)

Caso o usuário preencha com um nome já salvo no banco de dados ou deixe um campo vazio, aparecerá uma mensagem de erro:

![Tela de erro no cadastro](https://github.com/user-attachments/assets/75e50e7b-9e20-437d-9bfb-52d18df884a2)

### Tela de Login Bem-sucedido

Ao se cadastrar, o usuário voltará para a tela de login. Ao preencher os campos corretamente, aparecerá uma mensagem indicando que o login foi bem-sucedido, e a tela de menu será aberta:

![Tela de login certo](https://github.com/user-attachments/assets/e8bf9d9b-7856-427f-9eea-cdd2f174bef7)

### Tela de Menu

A tela de menu contém 6 botões: "Ver Livros", "Adicionar Livro", "Escolher Livro", "Ver Histórico", "Remover Livro" e "Sair".

![Tela de menu](https://github.com/user-attachments/assets/99d67774-5933-41a9-a009-a27cf0b6745b)

### Tela de Ver Livros

A tela de "Ver Livros" mostra os livros disponíveis (é possível melhorar a visualização para deixar os livros individuais). Há também um campo de pesquisa para procurar um livro, seja pelo nome, autor ou gênero, sem necessidade de digitar corretamente. Ao lado do botão "Voltar" para o menu, há um botão para limpar o filtro de pesquisa. Ao apertar o botão "Voltar", a tela de menu será exibida.

![Tela de ver livro](https://github.com/user-attachments/assets/0bf939fd-2482-4063-9eed-466320ec7801)

### Tela de Adicionar Livro

Na tela de "Adicionar Livro", há 4 campos de texto para o usuário. Dou atenção especial ao campo de URL, que é responsável pelo link do livro na internet. Também há um botão para adicionar uma imagem.

![Tela de adicionar livro](https://github.com/user-attachments/assets/cc89cb3c-0b10-4f2f-8ff3-e4fc25a45106)

#### Erros na Tela de Adicionar Livro

- Se a URL não for preenchida corretamente, aparecerá uma mensagem de erro ao apertar o botão "Adicionar":

  ![Tela de erro adicionar livro](https://github.com/user-attachments/assets/7892058d-7559-4458-98f6-82c7d6a20427)

- Se for inserido um caminho para a imagem incorreto, aparecerá um erro:

  ![Tela de erro imagem](https://github.com/user-attachments/assets/15ec2d1b-cf7e-4795-b608-5bee52661696)

- Se não preencher todos os campos, aparecerá um erro:

  ![Tela de erro campos](https://github.com/user-attachments/assets/99755d06-c417-4df3-8f13-a8539b7c657d)

Ao adicionar um livro corretamente, aparecerá uma mensagem indicando que o livro foi adicionado com sucesso, e a tela de menu será exibida:

![Tela de adicionar livro certo](https://github.com/user-attachments/assets/253f2935-784d-4bb0-adad-0677fd1ff8d4)

### Tela de Escolher Livro

A tela de "Escolher Livro" é semelhante à tela de "Ver Livros", com a diferença de que os livros são clicáveis. Ao clicar em um livro:

![Tela de escolher](https://github.com/user-attachments/assets/48f33b5f-14f9-4296-b430-ab633af8e571)

O navegador do usuário abrirá o link do livro que ele colocou (nesse exemplo foi o YouTube kkkk, mas o objetivo é ser um livro 😅):

![Tela de navegador](https://github.com/user-attachments/assets/e7791122-a28e-471d-af7f-656f22535459)

Uma mensagem aparecerá indicando que o livro foi adicionado ao histórico:

![Tela de adicionado ao historico](https://github.com/user-attachments/assets/4245057a-8537-4a84-a1d8-59f75d2f7034)

### Tela de Ver Histórico

A tela de "Ver Histórico" é semelhante às telas já vistas, com a diferença de haver um botão no lado direito para limpar o histórico:

![Tela de historico](https://github.com/user-attachments/assets/739edd49-928f-47f4-9037-cecb1d01fb12)

![Tela de mensagem historico](https://github.com/user-attachments/assets/0648e85b-995c-4acb-babd-cc9d3a31379c)

### Tela de Remover Livro

A tela de "Remover Livro" é semelhante à tela de "Escolher Livro", com um botão para remover o livro:

![Tela de remover](https://github.com/user-attachments/assets/c719a7c8-261d-4749-bf38-498b884789a5)

![Tela de mensagem remover](https://github.com/user-attachments/assets/c6d16ddc-4573-41b3-ac5d-7129b0a4a4dd)

### Tela de Sair

A última tela é a de sair:

![Tela de sair](https://github.com/user-attachments/assets/bf43f402-f4ce-4289-83a9-8ddb268a5c0a)

Ao apertar "Yes", o aplicativo fecha e encerra o programa. Caso o usuário aperte "No", a aplicação continua na tela de menu.
