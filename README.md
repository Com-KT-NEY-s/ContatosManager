# Projeto de Gerenciamento de Contatos
###### by <a href="https://github.com/Mathiack">Mathiack</a>, <a href="https://github.com/Monari14">Monari</a> & <a href="https://github.com/Wesw3s">Wesley</a> ft <a href="https://github.com/flarom">Flarom</a>
![GitHub repo size](https://img.shields.io/github/repo-size/Com-KT-NEY-s/ContatosManager?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/Com-KT-NEY-s/ContatosManager?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/Com-KT-NEY-s/ContatosManager?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/Com-KT-NEY-s/ContatosManager?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/Com-KT-NEY-s/ContatosManager?style=for-the-badge)

Este projeto é um aplicativo Java para gerenciar contatos, permitindo a adição, edição, exclusão, busca e filtragem de contatos por categoria, além de carregar e salvar contatos de arquivos de texto. Ele utiliza a biblioteca Swing para a interface gráfica.

## Funcionalidades

- **Adicionar Contato**: Permite adicionar um novo contato com as seguintes informações:
  - Nome
  - Telefone (formato: `(XX) XXXXX-XXXX`)
  - E-mail
  - Endereço
  - Categoria (Amigo, Trabalho, Família)
  
- **Editar Contato**: Permite a edição dos detalhes de um contato selecionado da tabela.
  
- **Excluir Contato**: Remove o contato selecionado da lista e do arquivo salvo.

- **Buscar Contatos**: Busca contatos por nome ou e-mail através de um campo de busca, retornando todos os resultados que coincidem.

- **Filtrar por Categoria**: Permite filtrar contatos de acordo com categorias predefinidas ("Todos", "Amigo", "Trabalho", "Família").

- **Carregar Contatos de Arquivo**: Importa contatos de um arquivo `.txt`. O arquivo deve seguir o formato:
  ```
  Nome: [nome]
  Telefone: [telefone]
  E-mail: [e-mail]
  Endereço: [endereço]
  Categoria: [categoria]
  
  Nome: [nome]
  Telefone: [telefone]
  E-mail: [e-mail]
  Endereço: [endereço]
  Categoria: [categoria]
  ```

- **Salvar Contatos em Arquivo**: Exporta os contatos da lista atual para um arquivo de texto formatado.

## Como Usar

1. Clone este repositório:
   ```bash
   git clone https://github.com/Com-KT-NEY-s/ContatosManager
   ```

2. Compile e execute o projeto no seu ambiente Java:
   - Use uma IDE como NetBeans ou IntelliJ IDEA.
   - Certifique-se de ter a JDK instalada.

3. Adicione, edite, exclua, filtre ou pesquise os contatos conforme necessário.

4. Salve a lista de contatos em um arquivo de texto ou carregue uma lista já existente.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Swing**: Para criar a interface gráfica.
- **JTable**: Para exibir e manipular a tabela de contatos.
- **JFileChooser**: Para selecionar arquivos ao carregar e salvar os contatos.
- **Regex**: Para validação de formatos de telefone e e-mail.

## Contribuição

Se você deseja contribuir com este projeto, sinta-se à vontade para enviar um pull request ou abrir uma issue.
