Abaixo voce vai vercomo as telas do meu programa funciona, infelizmente arquivos .jar só funcionam em pcs, a versão do java que eu utilizei é a 21, então se tiver uma versão mais antiga o arquivo talvez não abra. 

Ao abrir o arquivo jar, vai aparecer para o usuário a tela de login:
<img src="https://github.com/user-attachments/assets/e3550e21-bb0b-4227-a7c4-74d4d99b4e5b" alt="Tela de Login">

Temos nessa tela 2 campos de texto para o usuário colocar o nome de usuário e um campo de senha utilizando o JPasswordField para deixar a senha codificada. 
Caso o usuário não preencha os campos/incoretamente e aperte no botão de entrar, aparecerá uma mensagem na tela:
<img src="https://github.com/user-attachments/assets/e1183fae-bf36-4c95-b7c9-c2f085150454" alt="Tela de erro no login">

Há tambem um botão para cadastro, ao aperta esse botão, abrira uma nova tela para o usuário se cadastrar, basicamente com as mesmas funçoes da tela de login:
<img src="https://github.com/user-attachments/assets/549d72b5-86f4-4620-bd15-9f30a877c968" alt="Tela cadastro">

Caso o usuário preencha com um nome já salvo no banco de dados/deixe um campo vazio aparecerá uma mensagem:
<img src="https://github.com/user-attachments/assets/75e50e7b-9e20-437d-9bfb-52d18df884a2" alt="Tela de erro no cadastro">

Ao se cadastrar, o usuário voltará para a tela de login, e ao preencher os campos, aparecerá uma mensagem que o login foi bem sucedido e abrirá a tela de menu:
<img src="https://github.com/user-attachments/assets/e8bf9d9b-7856-427f-9eea-cdd2f174bef7" alt="Tela de login certo">

A tela de menu contem 6 botões: "Ver Livros", "Adicionar Livro", "Escolher Livro", "Ver Histórico", "Remover Livro" e "Sair".
<img src="https://github.com/user-attachments/assets/99d67774-5933-41a9-a009-a27cf0b6745b" alt="Tela de menu">

A tela de ver livros mostra os livros disponiveis(tentar mudar para deixar os livros individual ou nao), há também um campo de pesquisa para procurar um livro, procurando tanto pelo nome/autor/gênero não precisando ser escrito corretamente. Ao lado do botão de voltar para o menu, a um botão para limpar o filtro de pesquisa. Ao apertar o botão de voltar, voltará para a tela de menu

<img src="https://github.com/user-attachments/assets/0bf939fd-2482-4063-9eed-466320ec7801" alt="Tela de ver livro">

Já na tela de adicionar livro, há 4 campos de texto para o usuário, dou atenção para o campo de URL, que é responsavel pelo link do Livro na internet. Tem um botão para adicionar uma imagem.

<img src="https://github.com/user-attachments/assets/cc89cb3c-0b10-4f2f-8ff3-e4fc25a45106" alt="Tela de adicionar livro">

Se a URL não for preenchida corretamente, aparecerá uma mensagem caso aperte o botão de adiconar:
<img src="https://github.com/user-attachments/assets/7892058d-7559-4458-98f6-82c7d6a20427" alt="Tela de erro adicionar livro">

Se colocar um caminho para a imagem errada, aparecerá um erro:
<img src="https://github.com/user-attachments/assets/15ec2d1b-cf7e-4795-b608-5bee52661696" alt="Tela de erro imagem">

Se não preencher todos os campos, aparecerá um erro:
<img src="https://github.com/user-attachments/assets/99755d06-c417-4df3-8f13-a8539b7c657d" alt="Tela de erro campos">

Ao adicionar um livro, aparecerá a mensagem que o livro foi adicionado com sucesso e voltará para a tela de menu:
<img src="https://github.com/user-attachments/assets/253f2935-784d-4bb0-adad-0677fd1ff8d4" alt="Tela de adicionar livro certo">


A tela de escolher livro é semelhante a tela de ver livros, á diferença é que os livros são clicáveis, e ao clicar em algum livro:
<img src="https://github.com/user-attachments/assets/48f33b5f-14f9-4296-b430-ab633af8e571" alt="Tela de escolher">

Vai abrir no navegador do usuário o link do livro que ele colocou (nesse exemplo foi o youtube kkkk, MAS O OBJETIVO É PARA SER UM LIVRO :) ):
<img src="https://github.com/user-attachments/assets/e7791122-a28e-471d-af7f-656f22535459" alt="Tela de menu">

Vai aparecer também a mensagem que o livro foi adicionado ao histórico:
<img src="https://github.com/user-attachments/assets/4245057a-8537-4a84-a1d8-59f75d2f7034" alt="Tela de adicionado ao historico">


A tela de ver histórico é semelhante com as telas já vistas, a diferença é um botão no lado direito para limpar o histórico:
<img src="https://github.com/user-attachments/assets/739edd49-928f-47f4-9037-cecb1d01fb12" alt="Tela de historico">

<img src="https://github.com/user-attachments/assets/0648e85b-995c-4acb-babd-cc9d3a31379c" alt="Tela de mensagem historico">

Já a tela de remover é semelhante a tela de escolher, com um botão para remover o livro:
<img src="https://github.com/user-attachments/assets/c719a7c8-261d-4749-bf38-498b884789a5" alt="Tela de remover">

<img src="https://github.com/user-attachments/assets/c6d16ddc-4573-41b3-ac5d-7129b0a4a4dd" alt="Tela de mensagem remover">

A ultima tela é a de sair:
<img src="https://github.com/user-attachments/assets/bf43f402-f4ce-4289-83a9-8ddb268a5c0a" alt="Tela de sair">

Ao apertar "yes" o app fecha e encerra o programa, caso o usuário aperte "no" a aplicação segue na tela de menu.
