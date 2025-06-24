package view;
import java.util.InputMismatchException;
import java.util.Scanner;
import controller.ProdutosController;
import exception.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String nomeLoja = pedeTextoNaoVazio(scanner, "Bem vindo(a), para comecar insira o nome da loja");
    System.out.print("Quantos produtos deseja gerenciar? \n>> ");
    int quantidadeProdutos = scanner.nextInt();
    scanner.nextLine();

    ProdutosController produtosController = new ProdutosController(quantidadeProdutos);
    exibirBarra();

    System.out.println("Bem-vindo(a) ao sistema de cadastro e gerenciamento de produtos da loja " + nomeLoja + "\n");

    int opcaoEscolhida = 0;
    double preco = 0;

    while (true) {
      mostraMenu();
      try {
        opcaoEscolhida = verificaOpcao(scanner);
      } catch (OpcaoInvalidaException e) {
        exibirBarra();
        System.out.println("Erro: " + e.getMessage());
      } catch (InputMismatchException e) {
        exibirBarra();
        System.out.println("Erro: " + e.getMessage());
        scanner.nextLine();
      }


      exibirBarra();
      if (opcaoEscolhida == 1) {
        System.out.println(opcaoEscolhida + ". criar produto");

        String codigo = pedeTextoNaoVazio(scanner, "Insira o codigo do produto");
        scanner.nextLine();
        String nome = pedeTextoNaoVazio(scanner, "Insira o nome do produto");
        System.out.print("Insira o preco do produto \n >> ");
        try {
          preco = pedePreco(scanner);
          try {
            produtosController.criaProduto(codigo, nome, preco);
          } catch (LimiteProdutosException | ProdutoRepetidoException e) {
            System.out.println("Erro ao criar produto: " + e.getMessage());
          }

        } catch (Exception e) {
          System.out.println("Erro: " + e.getMessage());
        }
      } else if (opcaoEscolhida == 2) {
        System.out.println(opcaoEscolhida + ". buscar produto");

        String codigo = pedeTextoNaoVazio(scanner, "Insira o codigo do produto");

        exibirBarra();
        try {
          System.out.println("Informacoes do produto \n");
          System.out.println(produtosController.getProduto(codigo));
        } catch (ProdutoInexistenteException e) {
          System.out.println("Erro: " + e.getMessage());
        }
      } else if (opcaoEscolhida == 3) {
        System.out.println(opcaoEscolhida + ". atualizar produto");

        String codigo = pedeTextoNaoVazio(scanner, "Insira o codigo do produto");

        String novoNome = pedeTextoNaoVazio(scanner, "Insira o novo nome do produto");

        System.out.print("Insira o novo preco do produto \n >> ");
        try {
          double novoPreco = pedePreco(scanner);
          try {
            produtosController.atualizaProduto(codigo, novoNome, novoPreco);
            System.out.println("Produto atualizado com sucesso!");
          } catch (ProdutoInexistenteException e) {
            System.out.println("Erro: " + e.getMessage());;
          }
        } catch (InputMismatchException | PrecoInvalidoException e) {
          System.out.println("Erro: " + e.getMessage());
        }
        
      } else if (opcaoEscolhida == 4) {
        System.out.println(opcaoEscolhida + ". deletar produto");

        String codigo = pedeTextoNaoVazio(scanner, "Insira o codigo do produto");

        try {
          produtosController.deletaProduto(codigo);
          System.out.println("Produto de codigo '" + codigo + "' deletado com sucesso!");
        } catch (ProdutoInexistenteException e) {
          System.out.println("Erro: " + e.getMessage());
        }
      } else if (opcaoEscolhida == 5) {
        try {
          int quantidade = produtosController.getQuantidadeProdutos();
          System.out.printf("Produtos da loja %s (%d cadastrados)\n\n", nomeLoja, quantidade);
          String[] listaProdutos = produtosController.getListaProdutos();
          for (int i = 0; i < listaProdutos.length; i++) {
            System.out.printf("Produto %d:\n", i+1);
            System.out.println(" " + listaProdutos[i]);
            exibirBarra();
          }
        } catch (Exception e) {}
      } else if (opcaoEscolhida == 6) {
        System.out.println("Encerrando o sistema...");
        break;
      }
      exibirBarra();
    }

    scanner.close();
    
  }
  public static void mostraMenu () {
    String opcoes = " 1. criar produto\n 2. buscar produto\n 3. atualizar produto\n 4. deletar produto\n 5. listar produtos\n 6. sair\n ";
    System.out.println("Insira a opcao desejada:\n" + opcoes);
    System.out.print(">> ");
  }

  public static int verificaOpcao (Scanner scanner) throws OpcaoInvalidaException, InputMismatchException {
    try {
      int opcaoEscolhida = scanner.nextInt();
      if (opcaoEscolhida < 1 | opcaoEscolhida > 6) {
        throw new OpcaoInvalidaException("Opcao invalida, tente novamente!");
      }
      return opcaoEscolhida;
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Opcao invalida, escolha um numero inteiro.");
    }
  }

  public static void exibirBarra () {
    System.out.println("_".repeat(40));
  }

  public static double pedePreco (Scanner scanner) throws InputMismatchException, PrecoInvalidoException {
    try {
      double preco = scanner.nextDouble();
      if (preco <= 0) throw new PrecoInvalidoException("Insira um valor maior que zero."); 
      return preco;
    } catch (InputMismatchException e) {
      scanner.nextLine();
      throw new InputMismatchException("Insira um valor numerico para o preco.");
    }
  }

  public static String pedeTextoNaoVazio(Scanner scanner, String mensagem) {
    String entrada = "";
    while (entrada.isEmpty()) {
      System.out.print(mensagem + "\n>> ");
      entrada = scanner.nextLine().trim();
      if (entrada.isEmpty()) {
        System.out.println("Erro: o campo nao pode estar vazio.");
      }
    }
    return entrada;
  }

}